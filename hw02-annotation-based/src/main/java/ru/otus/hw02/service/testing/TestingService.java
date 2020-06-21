package ru.otus.hw02.service.testing;

import ru.otus.hw02.domain.Answer;
import ru.otus.hw02.domain.Question;
import ru.otus.hw02.domain.TestingResult;

import java.util.List;
import java.util.Optional;

public interface TestingService extends Runnable {

    Optional<Question> getCurrentQuestion();

    Optional<Question> getNextQuestion();

    List<Answer> getAnswers();

    void printResults();

    Optional<TestingResult> getResult();
}
