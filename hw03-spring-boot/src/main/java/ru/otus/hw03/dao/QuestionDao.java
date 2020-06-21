package ru.otus.hw03.dao;

import ru.otus.hw03.domain.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionDao {

    Optional<Question> getById(int id);

    List<Question> getAll();
}
