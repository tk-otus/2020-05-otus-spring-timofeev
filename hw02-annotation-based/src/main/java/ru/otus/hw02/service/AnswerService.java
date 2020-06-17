package ru.otus.hw02.service;

import ru.otus.hw02.domain.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerService {

    Optional<Answer> getById(int id);

    List<Answer> getByQuestionId(int id);

    List<Answer> getAll();
}
