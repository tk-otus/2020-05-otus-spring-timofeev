package ru.otus.hw02.service.testing;

import ru.otus.hw02.domain.Answer;
import ru.otus.hw02.domain.Question;

import java.util.List;
import java.util.Optional;

public interface TestingService extends Runnable {

    public Optional<Question> getCurrentQuestion();

    public Optional<Question> getNextQuestion();

    public List<Answer> getAnswers();

    public void printResults();

    public boolean checkCorrectAnswers(String userInput);
}
