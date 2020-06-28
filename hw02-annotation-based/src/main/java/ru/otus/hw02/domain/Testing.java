package ru.otus.hw02.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hw02.service.testing.TestingServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Testing {
    private static final Logger logger = LoggerFactory.getLogger(TestingServiceImpl.class);

    private final List<Question> questions;
    private Question currentQuestion;
    private List<Answer> currentAnswers = new ArrayList<>();
    private int currentQuestionIndex = -1;
    private final TestingResult testingResult = new TestingResultImpl();

    public Testing(List<Question> questions) {
        Collections.shuffle(questions);
        this.questions = questions;
    }

    public List<Answer> getCurrentQuestionAnswers() {
        return currentAnswers;
    }

    public TestingResult getTestingResult() {
        return testingResult;
    }

    public Optional<Question> getNextQuestion() {
        currentQuestionIndex += 1;
        if (currentQuestionIndex < questions.size()) {
            currentQuestion = questions.get(currentQuestionIndex);

            currentAnswers = currentQuestion.getAnswers();
            Collections.shuffle(currentAnswers);

            return Optional.of(currentQuestion);
        }
        return Optional.empty();
    }

    public void answerCurrentQuestion(List<Answer> answers) {
        if (currentQuestion.checkAnswers(answers)) {
            testingResult.addCorrectAnsweredQuestion(currentQuestion);
        } else {
            testingResult.addIncorrectAnsweredQuestion(currentQuestion);
        }
    }
}
