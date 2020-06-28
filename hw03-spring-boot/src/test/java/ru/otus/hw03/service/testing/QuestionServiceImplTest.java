package ru.otus.hw03.service.testing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw03.dao.QuestionDao;
import ru.otus.hw03.dao.QuestionDaoCsvImpl;
import ru.otus.hw03.domain.Question;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Сервис QuestionServiceImplTest")
class QuestionServiceImplTest {
    private static QuestionService questionService;
    private static Question correctQuestion;

    @BeforeAll
    static void setUp() {
        correctQuestion = mock(Question.class);
        QuestionDao dao = mock(QuestionDaoCsvImpl.class);
        when(dao.getById(1)).thenReturn(Optional.ofNullable(correctQuestion));
        when(dao.getAll()).thenReturn(Collections.singletonList(correctQuestion));
        questionService = new QuestionServiceImpl(dao);
    }

    @Test
    @DisplayName("Может получить вопрос из dao по Id")
    void testGetById() {
        Optional<Question> question = questionService.getById(1);

        assertTrue(question.isPresent());
        assertEquals(correctQuestion, question.get());
    }

    @Test
    @DisplayName("Может получить все вопросы из dao")
    void getAll() {
        List<Question> questions = questionService.getAll();

        assertNotNull(questions);
        assertEquals(correctQuestion, questions.get(0));
    }
}