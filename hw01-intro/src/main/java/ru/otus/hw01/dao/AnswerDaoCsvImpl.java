package ru.otus.hw01.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import ru.otus.hw01.domain.Answer;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnswerDaoCsvImpl implements AnswerDao {
    private static final Logger logger = LoggerFactory.getLogger(AnswerDaoCsvImpl.class);
    private final List<Answer> answers = new ArrayList<>();

    AnswerDaoCsvImpl(Resource file) throws IOException, CsvValidationException {
        try (var csvReader = new CSVReader(new FileReader(file.getFile()))) {
            csvReader.readNext(); // Пропускаем строку с заголовками

            String[] values;
            while ((values = csvReader.readNext()) != null) {
                int id = Integer.parseInt(values[0]);
                int questionId = Integer.parseInt(values[1]);
                String answerText = values[2];
                boolean isCorrect = Boolean.parseBoolean(values[3]);
                answers.add(new Answer(id, questionId, answerText, isCorrect));
            }

        }
        logger.info("ANSWERS {}", answers);
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
}
