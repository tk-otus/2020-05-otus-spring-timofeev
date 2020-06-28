package ru.otus.hw02.service.testing;

import ru.otus.hw02.domain.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    Optional<Question> getById(int id);

    List<Question> getAll();
}
