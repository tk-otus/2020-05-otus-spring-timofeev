package ru.otus.hw02.service;

import ru.otus.hw02.domain.Answer;
import ru.otus.hw02.domain.Question;

import java.util.List;

public interface TestingService extends Runnable {

    public Question getCurrentQuestion();

    public Question getNextQuestion();

    public List<Answer> getAnswers();

    public String getTextResult();
}
