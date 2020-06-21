package ru.otus.hw02.domain;

import java.util.List;

public interface TestingResult {

    List<Question> getCorrectAnsweredQuestions();

    List<Question> getIncorrectAnsweredQuestions();

    void addCorrectAnsweredQuestion(Question question);

    void addIncorrectAnsweredQuestion(Question question);
}
