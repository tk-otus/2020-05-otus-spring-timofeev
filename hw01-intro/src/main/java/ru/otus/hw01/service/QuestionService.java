package ru.otus.hw01.service;

import ru.otus.hw01.domain.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    Optional<Question> getById(int id);

    List<Question> getAll();
}
