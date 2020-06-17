package ru.otus.hw02.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestionTest {
    private final Answer correct1 = mock(Answer.class);
    private final Answer correct2 = mock(Answer.class);
    private final Answer incorrect1 = mock(Answer.class);
    private final Answer incorrect2 = mock(Answer.class);
    private Question oneTypeQuestion;
    private Question multiTypeQuestion;

    @BeforeEach
    void initMockAnswers() {
        when(correct1.getId()).thenReturn(1);
        when(correct1.getAnswer()).thenReturn("First correct");
        when(correct1.isCorrect()).thenReturn(true);
        when(correct2.getId()).thenReturn(2);
        when(correct2.getAnswer()).thenReturn("Second correct");
        when(correct2.isCorrect()).thenReturn(true);
        when(incorrect1.getId()).thenReturn(3);
        when(incorrect1.getAnswer()).thenReturn("First incorrect");
        when(incorrect1.isCorrect()).thenReturn(false);
        when(incorrect2.getId()).thenReturn(4);
        when(incorrect2.getAnswer()).thenReturn("Second incorrect");
        when(incorrect2.isCorrect()).thenReturn(false);
    }

    @BeforeEach
    void initQuestions() {
        oneTypeQuestion = new Question(1, QAType.ONE, "One Type Question");
        multiTypeQuestion = new Question(2, QAType.MULTI, "Multi Type Question");
    }

    @Test
    void testCheckAnswersSingleton() {
        oneTypeQuestion.addAnswer(correct1);
        oneTypeQuestion.addAnswer(incorrect1);

        assertTrue(oneTypeQuestion.checkAnswers(correct1));
        assertFalse(oneTypeQuestion.checkAnswers(incorrect1));
    }

    @Test
    void testCheckAnswersList() {
        multiTypeQuestion.addAnswer(correct1);
        multiTypeQuestion.addAnswer(incorrect1);

        assertTrue(multiTypeQuestion.checkAnswers(Collections.singletonList(correct1)));
        assertFalse(multiTypeQuestion.checkAnswers(Collections.singletonList(incorrect1)));
    }

    @Test
    void testAddAnswerMoreThanOneCorrectAnswerInOneTypeQuestion() {
        oneTypeQuestion.addAnswer(correct1);
        assertThrows(RuntimeException.class, () -> {
            oneTypeQuestion.addAnswer(correct1);
        });
    }

    @Test
    void testAddAnswerMoreThanOneCorrectAnswerInMultiTypeQuestion() {
        multiTypeQuestion.addAnswer(correct1);
        multiTypeQuestion.addAnswer(correct2);
        multiTypeQuestion.addAnswer(incorrect1);

        assertEquals(multiTypeQuestion.getAnswers(), Arrays.asList(correct1, correct2, incorrect1));
        assertEquals(multiTypeQuestion.getCorrectAnswers(), Arrays.asList(correct1, correct2));
    }

    @Test
    void testCanAddAnswerMoreThanOneCorrectAnswers() {
        oneTypeQuestion.addAnswer(correct1);
        multiTypeQuestion.addAnswer(correct1);

        assertFalse(oneTypeQuestion.canAddAnswer(correct2));
        assertTrue(multiTypeQuestion.canAddAnswer(correct2));
    }

    @Test
    void testCanAddAnswerIfExist() {
        oneTypeQuestion.addAnswer(correct1);
        multiTypeQuestion.addAnswer(correct1);

        assertFalse(oneTypeQuestion.canAddAnswer(correct1));
        assertFalse(multiTypeQuestion.canAddAnswer(correct1));
    }
}