package ru.otus.hw02.service.testing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.hw02.domain.*;
import ru.otus.hw02.service.console.PrintService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TestingServiceImpl implements TestingService {
    private static final Logger logger = LoggerFactory.getLogger(TestingServiceImpl.class);

    private final QuestionService questionService;
    private final PrintService printService;

    @Autowired
    TestingServiceImpl(QuestionService questionService, PrintService printService) {
        this.questionService = questionService;
        this.printService = printService;
    }

    @Override
    public void start() {
        Testing testing = new Testing(questionService.getAll());
        Optional<Question> questionObj = testing.getNextQuestion();
        while (questionObj.isPresent()) {
            Question question = questionObj.get();
            printService.print("Question: " + question.getQuestionText() + " (type: " + question.getType() + ")");
            int id = 1;
            for (Answer answer : testing.getCurrentQuestionAnswers()) {
                printService.print(id + ". " + answer.getAnswer() + " (is correct: " + answer.isCorrect() + ")");
                id++;
            }
            String userAnswers = printService.read();
            testing.answerCurrentQuestion(userInputToAnswers(userAnswers, testing));
            questionObj = testing.getNextQuestion();
        }
        printResults(testing);
    }

    private List<Answer> userInputToAnswers(String userInput, Testing testing) {
        List<Answer> result = new ArrayList<>();
        List<Answer> answers = testing.getCurrentQuestionAnswers();

        for (String userAnswer : userInput.split(",")) {
            userAnswer = userAnswer.trim();
            Answer answer = null;
            try {
                int id = Integer.parseInt(userAnswer) - 1;
                answer = answers.get(id);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                logger.warn("Can't find answer with id {}", userAnswer);
            }
            if (answer != null)
                result.add(answer);
        }
        return result;
    }

    private void printResults(Testing testing) {
        TestingResult testingResult = testing.getTestingResult();
        printService.print("Thank you for your answers!");
        printService.print("Results:");
        printService.print(String.format(
                "=============================\n" +
                        "Correct answers: %s\n" +
                        "Incorrect answers: %s\n" +
                        "Total questions: %s",
                testingResult.getCorrectAnsweredQuestions().size(),
                testingResult.getIncorrectAnsweredQuestions().size(),
                testingResult.getTotalQuestionsCount()
        ));
    }
}
