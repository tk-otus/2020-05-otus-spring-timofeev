package ru.otus.hw01.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.hw01.domain.Answer;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AnswerServiceImplTest {
    private static AnswerService answerService;

    @BeforeAll
    static void setUp() {
        final var context = new ClassPathXmlApplicationContext("/spring-context.xml");
        answerService = context.getBean(AnswerService.class);
    }

    @Test
    void testGetById() {
        Optional<Answer> answer = answerService.getById(1);

        assertTrue(answer.isPresent());
    }

    @Test
    void testGetByQuestionId() {
        List<Answer> answers = answerService.getByQuestionId(1);

        assertNotNull(answers);
        assertEquals(2, answers.size());
    }

    @Test
    void testGetAll() {
        List<Answer> answers = answerService.getAll();

        assertNotNull(answers);
        assertEquals(12, answers.size());
    }
}