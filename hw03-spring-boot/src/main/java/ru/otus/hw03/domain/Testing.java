package ru.otus.hw03.domain;

import java.util.List;
import java.util.Optional;

public interface Testing {

    List<Answer> getCurrentQuestionAnswers();

    TestingResult getTestingResult();

    Optional<Question> getNextQuestion();

    void answerCurrentQuestion(List<Answer> answers);
}
