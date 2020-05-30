package ru.otus.hw01.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import ru.otus.hw01.domain.Answer;
import ru.otus.hw01.domain.QAType;
import ru.otus.hw01.domain.Question;
import ru.otus.hw01.service.AnswerService;
import ru.otus.hw01.service.QuestionServiceImpl;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuestionDaoCsvImpl implements QuestionDao {
    private static final Logger logger = LoggerFactory.getLogger(QuestionDaoCsvImpl.class);
    private final List<Question> questions = new ArrayList<>();

    QuestionDaoCsvImpl(Resource file, AnswerService answerService) throws IOException, CsvValidationException {
        try (var csvReader = new CSVReader(new FileReader(file.getFile()))) {
            csvReader.readNext(); // Пропускаем строку с заголовками

            String[] values;
            while ((values = csvReader.readNext()) != null) {
                int id = Integer.parseInt(values[0]);
                QAType type = QAType.valueOf(values[1]);
                String questionText = values[2];

                Question question = new Question(id, type, questionText);
                for (Answer answer : answerService.getByQuestionId(id)) {
                    question.addAnswer(answer);
                }
                questions.add(question);
            }
        }
        logger.info("QUESTIONS {}", questions);
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
