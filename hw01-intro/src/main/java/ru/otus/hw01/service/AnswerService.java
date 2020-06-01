package ru.otus.hw01.service;

import ru.otus.hw01.domain.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerService {

    Optional<Answer> getById(int id);

    List<Answer> getByQuestionId(int id);

    List<Answer> getAll();
}
