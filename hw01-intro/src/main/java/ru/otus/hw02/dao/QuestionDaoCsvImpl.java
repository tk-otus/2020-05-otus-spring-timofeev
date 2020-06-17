package ru.otus.hw01.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import ru.otus.hw01.dao.exception.QuestionLoadingException;
import ru.otus.hw01.domain.Answer;
import ru.otus.hw01.domain.QAType;
import ru.otus.hw01.domain.Question;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuestionDaoCsvImpl implements QuestionDao {
    private static final Logger logger = LoggerFactory.getLogger(QuestionDaoCsvImpl.class);
    private final List<Question> questions = new ArrayList<>();

    QuestionDaoCsvImpl(Resource file, AnswerDao answerDao) throws QuestionLoadingException {
        try (var csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            csvReader.readNext(); // Пропускаем строку с заголовками
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                int id = Integer.parseInt(values[0]);
                QAType type = QAType.valueOf(values[1]);
                String questionText = values[2];
                questions.add(new Question(id, type, questionText));
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

        for (Question question : questions) {
            for (Answer answer : answerDao.getByQuestionId(question.getId())) {
                question.addAnswer(answer);
            }
        }
    }

    @Override
    public Optional<Question> getById(int id) {
        return questions.stream().filter(it -> it.getId() == id).findFirst();
    }

    @Override
    public List<Question> getAll() {
        return questions;
    }
}
