package ru.otus.hw01.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.hw01.domain.Question;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class QuestionServiceImplTest {
    private static QuestionService questionService;

    @BeforeAll
    static void setUp() {
        final var context = new ClassPathXmlApplicationContext("/spring-context.xml");
        questionService = context.getBean(QuestionService.class);
    }

    @Test
    void testGetById() {
        Optional<Question> question = questionService.getById(1);

        assertTrue(question.isPresent());
    }

    @Test
    void getAll() {
        List<Question> questions = questionService.getAll();

        assertNotNull(questions);
        assertEquals(5, questions.size());
    }
}