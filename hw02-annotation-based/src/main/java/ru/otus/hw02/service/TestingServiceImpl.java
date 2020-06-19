package ru.otus.hw02.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.hw02.domain.Answer;
import ru.otus.hw02.domain.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TestingServiceImpl implements TestingService {
    private static final Logger logger = LoggerFactory.getLogger(TestingServiceImpl.class);

    private final QuestionService questionService;
    private final ConsolePrintService printService;
    private final List<Question> questions;
    private List<Question> correctAnsweredQuestions = new ArrayList<>();
    private List<Question> incorrectAnsweredQuestions = new ArrayList<>();
    private Question currentQuestion;
    private List<Answer> currentAnswers;
    private int currentQuestionIndex = 0;

    @Autowired
    TestingServiceImpl(QuestionService questionService, ConsolePrintService printService) {
        this.questionService = questionService;
        this.printService = printService;
        questions = questionService.getAll();
        Collections.shuffle(questions);
    }

    @Override
    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    @Override
    public Question getNextQuestion() {
        currentQuestionIndex += 1;
        if (currentQuestionIndex <= questions.size()) {
            currentQuestion = questions.get(currentQuestionIndex - 1);
            return currentQuestion;
        }
        return null;
    }

    @Override
    public List<Answer> getAnswers() {
        if (currentQuestion == null)
            return null;
        currentAnswers = currentQuestion.getAnswers();
        Collections.shuffle(currentAnswers);
        return currentAnswers;
    }

    @Override
    public String getTextResult() {
        return String.format(
                "=============================\n" +
                        "Correct answers: %s\n" +
                        "Incorrect answers: %s\n" +
                        "Total questions: %s",
                correctAnsweredQuestions.size(),
                incorrectAnsweredQuestions.size(),
                questions.size()
        );
    }

    @Override
    public void run() {
        Question question = getNextQuestion();
        while (question != null) {
            try {
                printService.print("Question: " + question.getQuestionText() + " (type: " + question.getType() + ")");
                int id = 1;
                for (Answer answer : getAnswers()) {
                    printService.print(id + ". " + answer.getAnswer() + " (is correct: " + answer.isCorrect() + ")");
                    id++;
                }
                String userAnswers = printService.read();
                if (checkCorrectAnswers(userAnswers)) {
                    correctAnsweredQuestions.add(question);
                } else {
                    incorrectAnsweredQuestions.add(question);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            question = getNextQuestion();
        }

        printService.print("Thank you for your answers!");
        printService.print("Results:");
        printService.print(getTextResult());
    }

    private boolean checkCorrectAnswers(String userAnswers) {
        List<Answer> answers = new ArrayList<>();
        for (String id : userAnswers.split(",")) {
            Answer answer = currentAnswers.get(Integer.parseInt(id) - 1);
            if (answer == null)
                return false;
            answers.add(answer);
        }
        return currentQuestion.checkAnswers(answers);
    }
}
