package ru.otus.hw03.service.testing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.hw03.configs.GlobalProps;
import ru.otus.hw03.domain.Answer;
import ru.otus.hw03.domain.Question;
import ru.otus.hw03.domain.Testing;
import ru.otus.hw03.domain.TestingResult;
import ru.otus.hw03.service.console.PrintService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TestingServiceImpl implements TestingService {
    private static final Logger logger = LoggerFactory.getLogger(TestingServiceImpl.class);

    private final QuestionService questionService;
    private final PrintService printService;
    private final MessageSource messageSource;
    private final GlobalProps props;

    @Autowired
    TestingServiceImpl(QuestionService questionService, PrintService printService, MessageSource messageSource, GlobalProps props) {
        this.questionService = questionService;
        this.printService = printService;
        this.messageSource = messageSource;
        this.props = props;
    }

    @Override
    public void start() {
        Testing testing = new Testing(questionService.getAll());
        Optional<Question> questionObj = testing.getNextQuestion();
        while (questionObj.isPresent()) {
            Question question = questionObj.get();
            printService.print(messageSource.getMessage("testing.print.question", new String[]{
                            question.getQuestionText(),
                            question.getType().toString()},
                    props.getLocale()));
            int id = 1;
            for (Answer answer : testing.getCurrentQuestionAnswers()) {
                printService.print(messageSource.getMessage("testing.print.answer", new String[]{
                                Integer.toString(id),
                                answer.getAnswer(),
                                Boolean.toString(answer.isCorrect())},
                        props.getLocale()));
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
        String[] messages = new String[]{
                Integer.toString(testingResult.getCorrectAnsweredQuestions().size()),
                Integer.toString(testingResult.getIncorrectAnsweredQuestions().size()),
                Integer.toString(testingResult.getTotalQuestionsCount())
        };
        printService.print(
                messageSource.getMessage("testing.result.to.print", messages, props.getLocale())
        );
    }
}
