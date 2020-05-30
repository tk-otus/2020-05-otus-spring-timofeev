package ru.otus.hw01.dao;

import ru.otus.hw01.domain.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerDao {

    List<Answer> getByQuestionId(int id);

    Optional<Answer> getById(int id);

    List<Answer> getAll();
}
