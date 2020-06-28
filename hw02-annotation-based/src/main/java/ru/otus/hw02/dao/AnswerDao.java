package ru.otus.hw02.dao;

import ru.otus.hw02.domain.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerDao {

    List<Answer> getByQuestionId(int id);

    Optional<Answer> getById(int id);

    List<Answer> getAll();
}
