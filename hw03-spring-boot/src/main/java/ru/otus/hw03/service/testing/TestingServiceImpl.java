package ru.otus.hw03.service.testing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.hw03.configs.GlobalProps;
import ru.otus.hw03.domain.Answer;
import ru.otus.hw03.domain.Question;
import ru.otus.hw03.service.console.ConsolePrintService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TestingServiceImpl implements TestingService {
    private static final Logger logger = LoggerFactory.getLogger(TestingServiceImpl.class);

    private final ConsolePrintService printService;
    private final MessageSource messageSource;
    private final GlobalProps props;
    private final List<Question> questions;
    private final List<Question> correctAnsweredQuestions = new ArrayList<>();
    private final List<Question> incorrectAnsweredQuestions = new ArrayList<>();
    private Question currentQuestion;
    private List<Answer> currentAnswers;
    private int currentQuestionIndex = -1;

    @Autowired
    TestingServiceImpl(QuestionService questionService, ConsolePrintService printService, MessageSource messageSource, GlobalProps props) {
        this.printService = printService;
        this.messageSource = messageSource;
        this.props = props;
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
        String[] messages = new String[]{
                Integer.toString(correctAnsweredQuestions.size()),
                Integer.toString(incorrectAnsweredQuestions.size()),
                Integer.toString(questions.size())
        };
        printService.print(
                messageSource.getMessage("testing.result.to.print", messages, props.getLocale())
        );
    }

    @Override
    public void run() {
        Optional<Question> questionObj = getNextQuestion();
        while (questionObj.isPresent()) {
            Question question = questionObj.get();
            try {
                printService.print(messageSource.getMessage("testing.print.question", new String[]{
                                question.getQuestionText(),
                                question.getType().toString()},
                        props.getLocale()));
                int id = 1;
                for (Answer answer : getAnswers()) {
                    printService.print(messageSource.getMessage("testing.print.answer", new String[]{
                                    Integer.toString(id),
                                    answer.getAnswer(),
                                    Boolean.toString(answer.isCorrect())},
                            props.getLocale()));
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

    @Override
    public boolean checkCorrectAnswers(String userInput) {
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
