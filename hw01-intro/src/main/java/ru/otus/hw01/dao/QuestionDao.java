package ru.otus.hw01.dao;

import ru.otus.hw01.domain.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionDao {

    Optional<Question> getById(int id);

    List<Question> getAll();
}
