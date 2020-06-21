package ru.otus.hw02.service.testing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.hw02.domain.Answer;
import ru.otus.hw02.domain.Question;
import ru.otus.hw02.service.console.PrintService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TestingServiceImpl implements TestingService {
    private static final Logger logger = LoggerFactory.getLogger(TestingServiceImpl.class);

    private final PrintService printService;
    private final List<Question> questions;
    private final List<Question> correctAnsweredQuestions = new ArrayList<>();
    private final List<Question> incorrectAnsweredQuestions = new ArrayList<>();
    private Question currentQuestion;
    private List<Answer> currentAnswers;
    private int currentQuestionIndex = -1;

    @Autowired
    TestingServiceImpl(QuestionService questionService, PrintService printService) {
        this.printService = printService;
        questions = questionService.getAll();
        Collections.shuffle(questions);
    }

    @Override
    public Optional<Question> getCurrentQuestion() {
        return currentQuestion != null ? Optional.of(currentQuestion) : Optional.empty();
    }

    @Override
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

    @Override
    public List<Answer> getAnswers() {
        return currentQuestion != null ? currentAnswers : new ArrayList<>();
    }

    @Override
    public void printResults() {
        printService.print("Thank you for your answers!");
        printService.print("Results:");
        printService.print(String.format(
                "=============================\n" +
                        "Correct answers: %s\n" +
                        "Incorrect answers: %s\n" +
                        "Total questions: %s",
                correctAnsweredQuestions.size(),
                incorrectAnsweredQuestions.size(),
                questions.size()
        ));
    }

    @Override
    public void run() {
        Optional<Question> questionObj = getNextQuestion();
        while (questionObj.isPresent()) {
            Question question = questionObj.get();
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
            questionObj = getNextQuestion();
        }
        printResults();
    }

    public List<Question> getCorrectAnsweredQuestions() {
        return correctAnsweredQuestions;
    }

    public List<Question> getIncorrectAnsweredQuestions() {
        return incorrectAnsweredQuestions;
    }

    protected boolean checkCorrectAnswers(String userInput) {
        List<Answer> answers = new ArrayList<>();
        for (String userAnswer : userInput.split(",")) {
            userAnswer = userAnswer.trim();
            Answer answer;
            try {
                int id = Integer.parseInt(userAnswer) - 1;
                answer = currentAnswers.get(id);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                logger.warn("Can't find answer with id {}", userAnswer);
                return false;
            }
            if (answer == null)
                return false;
            answers.add(answer);
        }
        return currentQuestion.checkAnswers(answers);
    }
}
