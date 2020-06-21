package ru.otus.hw03.service.testing;

import ru.otus.hw03.domain.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerService {

    Optional<Answer> getById(int id);

    List<Answer> getByQuestionId(int id);

    List<Answer> getAll();
}
