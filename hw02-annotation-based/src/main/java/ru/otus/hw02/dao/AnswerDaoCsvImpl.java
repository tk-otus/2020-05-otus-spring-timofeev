package ru.otus.hw02.dao;

import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import ru.otus.hw02.dao.exception.AnswerLoadingException;
import ru.otus.hw02.domain.Answer;
import ru.otus.hw02.util.SimpleCsvReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AnswerDaoCsvImpl implements AnswerDao {
    private static final Logger logger = LoggerFactory.getLogger(AnswerDaoCsvImpl.class);
    private final Resource file;
    private final SimpleCsvReader reader;
    private final List<Answer> answers;

    public AnswerDaoCsvImpl(@Value("classpath:${global.answers.csv.file}") Resource file, SimpleCsvReader reader) throws AnswerLoadingException {
        this.file = file;
        this.reader = reader;
        answers = readAnswersFromFile();
    }

    @Override
    public List<Answer> getByQuestionId(int id) {
        return answers.stream().filter(it -> it.getQuestionId() == id).collect(Collectors.toList());
    }

    @Override
    public Optional<Answer> getById(int id) {
        return answers.stream().filter(it -> it.getId() == id).findFirst();
    }

    @Override
    public List<Answer> getAll() {
        return answers;
    }

    private List<Answer> readAnswersFromFile() throws AnswerLoadingException {
        var result = new ArrayList<Answer>();
        try {
            List<String[]> stringsFromFile = reader.getResults(file);
            for (String[] value : stringsFromFile) {
                int id = Integer.parseInt(value[0]);
                int questionId = Integer.parseInt(value[1]);
                String answerText = value[2];
                boolean isCorrect = Boolean.parseBoolean(value[3]);
                result.add(new Answer(id, questionId, answerText, isCorrect));
            }
        } catch (FileNotFoundException e) {
            throw new AnswerLoadingException("Answers file (" + file.getFilename() + ") not found", e);
        } catch (IOException e) {
            throw new AnswerLoadingException("Failed to read answers file (" + file.getFilename() + ")", e);
        } catch (CsvValidationException e) {
            throw new AnswerLoadingException("An error occurred while reading the answers file (" + file.getFilename() + ")", e);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            throw new AnswerLoadingException("Answers file contains invalid data (" + file.getFilename() + ")", e);
        }
        return result;
    }
}
