package ru.otus.hw03.domain;

import java.util.ArrayList;
import java.util.List;

public class TestingResultImpl implements TestingResult {
    private final List<Question> correctAnsweredQuestions = new ArrayList<>();
    private final List<Question> incorrectAnsweredQuestions = new ArrayList<>();

    @Override
    public List<Question> getCorrectAnsweredQuestions() {
        return correctAnsweredQuestions;
    }

    @Override
    public List<Question> getIncorrectAnsweredQuestions() {
        return incorrectAnsweredQuestions;
    }

    @Override
    public void addCorrectAnsweredQuestion(Question question) {
        correctAnsweredQuestions.add(question);
    }

    @Override
    public void addIncorrectAnsweredQuestion(Question question) {
        incorrectAnsweredQuestions.add(question);
    }

    @Override
    public int getTotalQuestionsCount() {
        return correctAnsweredQuestions.size() + incorrectAnsweredQuestions.size();
    }
}
