package ru.otus.hw02.service.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw02.domain.Answer;
import ru.otus.hw02.domain.Question;
import ru.otus.hw02.service.console.ConsolePrintService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Сервис TestingServiceImplTest")
class TestingServiceImplTest {
    QuestionService questionService;
    TestingServiceImpl testingService;
    ConsolePrintService printService = mock(ConsolePrintService.class);

    List<Answer> answers = new ArrayList<>();
    List<Question> questions = new ArrayList<>();

    @BeforeEach
    void mockQuestionService() {
        Answer answer1 = mock(Answer.class);
        Answer answer2 = mock(Answer.class);
        answers.add(answer1);
        answers.add(answer2);

        Question question1 = mock(Question.class);
        when(question1.getAnswers()).thenReturn(answers);
        when(question1.checkAnswers(answers)).thenReturn(true);
        questions.add(question1);

        Question question2 = mock(Question.class);
        when(question2.getAnswers()).thenReturn(answers);
        when(question2.checkAnswers(answers)).thenReturn(true);
        questions.add(question2);

        questionService = mock(QuestionService.class);
        when(questionService.getAll()).thenReturn(questions);
    }

    @BeforeEach
    void mockPrintService() {
        printService = mock(ConsolePrintService.class);
    }

    @BeforeEach
    void setUp() {
        testingService = new TestingServiceImpl(questionService, printService);
    }

    @Test
    @DisplayName("Может вернуть пустой текущий вопрос при первом обращении")
    void testGetCurrentQuestionIsEmpty() {
        Optional<Question> question = testingService.getCurrentQuestion();
        assertTrue(question.isEmpty());
    }

    @Test
    @DisplayName("Может вернуть текущий вопрос при первом обращении")
    void testGetCurrentQuestionIsNotEmpty() {
        Optional<Question> nextQuestion = testingService.getNextQuestion();
        Optional<Question> currentQuestion = testingService.getCurrentQuestion();

        assertTrue(currentQuestion.isPresent());
        assertEquals(nextQuestion, currentQuestion);
    }

    @Test
    @DisplayName("Может возвращать следующие вопросы")
    void testGetNextQuestionWithoutEmpty() {
        for (int i = 0; i < questions.size(); i++) {
            Optional<Question> question = testingService.getNextQuestion();
            assertTrue(question.isPresent());
            assertTrue(questions.contains(question.get()));
        }
    }

    @Test
    @DisplayName("Может возвращать пустой Optional когда вопросы закончились")
    void testGetNextQuestionWithEmpty() {
        for (int i = 0; i < questions.size(); i++) {
            testingService.getNextQuestion();
        }
        assertTrue(testingService.getNextQuestion().isEmpty());
    }

    @Test
    @DisplayName("Может возвращать пустой список ответов, если текущего вопроса нет")
    void testGetAnswersWhenCurrentQuestionIsNotSet() {
        assertTrue(testingService.getAnswers().isEmpty());
    }

    @Test
    @DisplayName("Может возвращать список ответов из текущего вопроса")
    void testGetAnswersWhenCurrentQuestionIsSet() {
        for (Answer answer : testingService.getAnswers()) {
            assertTrue(answers.contains(answer));
        }
    }

    @Test
    @DisplayName("Может проверить правильные ответы пользователя")
    void testCheckCorrectAnswers() {
        testingService.getNextQuestion();
        String userInput = "1,2";
        assertTrue(testingService.checkCorrectAnswers(userInput));
    }

    @Test
    @DisplayName("Может проверить неправильные ответы пользователя")
    void testCheckIncorrectAnswers() {
        testingService.getNextQuestion();
        String userInput = "1";
        assertFalse(testingService.checkCorrectAnswers(userInput));
    }

    @Test
    @DisplayName("Может проверить несуществующий номер ответа пользователя")
    void testCheckNotExistAnswers() {
        testingService.getNextQuestion();
        String userInput = "1,5";
        assertFalse(testingService.checkCorrectAnswers(userInput));
    }

    @Test
    @DisplayName("Может провести тестирование пользователя")
    void run() throws IOException {
        given(printService.read()).willReturn("1,2").willReturn("1,2");
        testingService.run();

        for (Question question : testingService.getCorrectAnsweredQuestions()) {
            assertTrue(questions.contains(question));
        }
        assertTrue(testingService.getIncorrectAnsweredQuestions().isEmpty());
    }
}