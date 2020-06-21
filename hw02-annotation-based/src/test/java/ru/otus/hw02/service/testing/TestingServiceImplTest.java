package ru.otus.hw02.service.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw02.domain.Answer;
import ru.otus.hw02.domain.Question;
import ru.otus.hw02.domain.TestingResult;
import ru.otus.hw02.service.console.PrintService;

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
    PrintService printService = mock(PrintService.class);

    List<Answer> answers = new ArrayList<>();
    List<Question> questions = new ArrayList<>();
    Question correctQuestion;
    Question incorrectQuestion;

    @BeforeEach
    void mockQuestionService() {
        Answer answer1 = mock(Answer.class);
        Answer answer2 = mock(Answer.class);
        answers.add(answer1);
        answers.add(answer2);

        correctQuestion = mock(Question.class);
        when(correctQuestion.getAnswers()).thenReturn(answers);
        when(correctQuestion.checkAnswers(answers)).thenReturn(true);
        questions.add(correctQuestion);

        incorrectQuestion = mock(Question.class);
        when(incorrectQuestion.getAnswers()).thenReturn(answers);
        when(incorrectQuestion.checkAnswers(answers)).thenReturn(false);
        questions.add(incorrectQuestion);

        questionService = mock(QuestionService.class);
        when(questionService.getAll()).thenReturn(questions);
    }

    @BeforeEach
    void mockPrintService() {
        printService = mock(PrintService.class);
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
        for (int i = 0; i < questions.size(); i++) {
            Optional<Question> question = testingService.getNextQuestion();
            if (question.isPresent() && question.get().equals(correctQuestion)) {
                String userInput = "1,2";
                assertTrue(testingService.checkCorrectAnswers(userInput));
                break;
            }
        }
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
    @DisplayName("Результаты тестирования возвращают вопросы с правильными ответами")
    void testTestingResultContainsCorrectAnswers() {
        given(printService.read()).willReturn("1,2").willReturn("1,2");
        testingService.run();
        TestingResult result = testingService.getResult().get();
        List<Question> correctAnsweredQuestions = result.getCorrectAnsweredQuestions();

        assertEquals(1, correctAnsweredQuestions.size());
        for (Question question : correctAnsweredQuestions) {
            assertTrue(questions.contains(question));
        }
    }

    @Test
    @DisplayName("Результаты тестирования возвращают вопросы с неправильными ответами")
    void testTestingResultContainsIncorrectAnswers() {
        given(printService.read()).willReturn("1,2").willReturn("1,2");
        testingService.run();
        TestingResult result = testingService.getResult().get();
        List<Question> incorrectAnsweredQuestions = result.getIncorrectAnsweredQuestions();

        assertEquals(1, incorrectAnsweredQuestions.size());
        for (Question question : incorrectAnsweredQuestions) {
            assertTrue(questions.contains(question));
        }
    }

    @Test
    @DisplayName("Если тестирование еще не завершено - нельзя получить результаты")
    void testGetResultWhenTestingNotComplete() {
        Optional<TestingResult> result = testingService.getResult();
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Возвращает результаты когда тестирование пройдено")
    void testGetResult() {
        given(printService.read()).willReturn("1,2").willReturn("1,2");
        testingService.run();
        assertTrue(testingService.getResult().isPresent());
    }
}
