package ru.otus.hw01.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.otus.hw01.dao.AnswerDao;
import ru.otus.hw01.dao.AnswerDaoCsvImpl;
import ru.otus.hw01.domain.Answer;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AnswerServiceImplTest {
    private static AnswerService answerService;
    private static Answer correctAnswer;

    @BeforeAll
    static void setUp() {
        correctAnswer = mock(Answer.class);
        AnswerDao dao = mock(AnswerDaoCsvImpl.class);
        when(dao.getById(1)).thenReturn(Optional.ofNullable(correctAnswer));
        when(dao.getByQuestionId(1)).thenReturn(Collections.singletonList(correctAnswer));
        when(dao.getAll()).thenReturn(Collections.singletonList(correctAnswer));

        answerService = new AnswerServiceImpl(dao);
    }

    @Test
    void testGetById() {
        Optional<Answer> answer = answerService.getById(1);

        assertTrue(answer.isPresent());
        assertEquals(correctAnswer, answer.get());
    }

    @Test
    void testGetByQuestionId() {
        List<Answer> answers = answerService.getByQuestionId(1);

        assertNotNull(answers);
        assertEquals(correctAnswer, answers.get(0));
    }

    @Test
    void testGetAll() {
        List<Answer> answers = answerService.getAll();

        assertNotNull(answers);
        assertEquals(correctAnswer, answers.get(0));
    }
}
