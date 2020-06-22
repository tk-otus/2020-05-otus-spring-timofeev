package ru.otus.hw03.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;
import ru.otus.hw03.configs.GlobalProps;
import ru.otus.hw03.dao.exception.QuestionLoadingException;
import ru.otus.hw03.domain.Answer;
import ru.otus.hw03.domain.QAType;
import ru.otus.hw03.domain.Question;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class QuestionDaoCsvImpl implements QuestionDao {
    private static final Logger logger = LoggerFactory.getLogger(QuestionDaoCsvImpl.class);

    private final AnswerDao dao;
    private final GlobalProps props;
    private final List<Question> questions;

    @Autowired
    public QuestionDaoCsvImpl(AnswerDao dao, ResourceLoader loader, GlobalProps props) throws QuestionLoadingException {
        this.dao = dao;
        this.props = props;
        questions = readQuestionFromFile(loader.getResource("classpath:" + props.getQuestionsCsvfile()));
    }

    @Override
    public Optional<Question> getById(int id) {
        return questions.stream().filter(it -> it.getId() == id).findFirst();
    }

    @Override
    public List<Question> getAll() {
        return questions;
    }

    private List<Question> readQuestionFromFile(Resource file) throws QuestionLoadingException {
        List<Question> result = new ArrayList<>();
        try (var csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            csvReader.readNext(); // Пропускаем строку с заголовками
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                int id = Integer.parseInt(values[0]);
                QAType type = QAType.valueOf(values[1]);
                String questionText = values[2];
                String locale = values[3];
                if (locale.equals(props.getLocale().toString()))
                    result.add(new Question(id, type, questionText));
            }
        } catch (FileNotFoundException e) {
            throw new QuestionLoadingException("Questions file (" + file.getFilename() + ") not found", e);
        } catch (IOException e) {
            throw new QuestionLoadingException("Failed to read questions file (" + file.getFilename() + ")", e);
        } catch (CsvValidationException e) {
            throw new QuestionLoadingException("An error occurred while reading the questions file (" + file.getFilename() + ")", e);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            throw new QuestionLoadingException("Questions file contains invalid data (" + file.getFilename() + ")", e);
        }
        return readAnswersFromDao(result);
    }

    private List<Question> readAnswersFromDao (List<Question> questions) throws QuestionLoadingException {
        for (Question question : questions) {
            for (Answer answer : dao.getByQuestionId(question.getId())) {
                question.addAnswer(answer);
            }
        }
        return questions;
    }
}
