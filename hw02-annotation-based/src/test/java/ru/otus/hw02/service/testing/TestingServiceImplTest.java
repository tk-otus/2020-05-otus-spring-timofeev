package ru.otus.hw02.service.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.hw02.domain.Question;
import ru.otus.hw02.service.console.ConsolePrintService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@SpringBootConfiguration
//@EnableConfigurationProperties
//@ComponentScan({"ru.otus.hw02"})
@DisplayName("Тест TestingServiceImplTest")
@SpringBootTest(classes = TestingServiceImpl.class)
class TestingServiceImplTest {

    @MockBean
    QuestionService questionService;

    @MockBean
    ConsolePrintService printService;

    @Autowired
    TestingService testingService;

    @BeforeEach
    void setUp() {
        System.out.println("testingService: " + testingService);
//        Question question1 = mock(Question.class);
//        Question question2 = mock(Question.class);
//        List<Question> questions = new ArrayList<>();
//        questions.add(question1);
//        questions.add(question2);
//
//        when(questionService.getAll()).thenReturn(questions);
    }

    @Test
    @DisplayName("Может вернуть пустой текущий вопрос при первом обращении")
    void getCurrentQuestionIsEmpty() {
        Optional<Question> question = testingService.getCurrentQuestion();
        assertTrue(question.isEmpty());
    }

    @Test
    @DisplayName("Может вернуть текущий вопрос при первом обращении")
    void getCurrentQuestionIsNotEmpty() {
        Optional<Question> nextQuestion = testingService.getNextQuestion();
        Optional<Question> currentQuestion = testingService.getCurrentQuestion();
        System.out.println("nextQuestion = " + nextQuestion);
        System.out.println("currentQuestion = " + currentQuestion);

        assertTrue(currentQuestion.isPresent());
        assertEquals(nextQuestion, currentQuestion);
    }

    @Test
    void getNextQuestion() {
    }

    @Test
    void getAnswers() {
    }

    @Test
    void printResults() {
    }

    @Test
    void run() {
    }

    @Test
    void checkCorrectAnswers() {
    }
}