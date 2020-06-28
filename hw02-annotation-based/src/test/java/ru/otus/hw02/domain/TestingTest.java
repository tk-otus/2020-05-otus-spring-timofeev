package ru.otus.hw02.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Класс Testing")
class TestingTest {

    List<Answer> answers = new ArrayList<>();
    List<Question> questions = new ArrayList<>();
    Question correctQuestion;
    Question incorrectQuestion;

    Testing testing;

    @BeforeEach
    void setUp() {
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

        testing = new Testing(questions);
    }

    @Test
    @DisplayName("Может возвращать следующие вопросы")
    void testGetNextQuestionWithoutEmpty() {
        for (int i = 0; i < questions.size(); i++) {
            Optional<Question> question = testing.getNextQuestion();
            assertTrue(question.isPresent());
            assertTrue(questions.contains(question.get()));
        }
    }

    @Test
    @DisplayName("Может возвращать пустой Optional когда вопросы закончились")
    void testGetNextQuestionWithEmpty() {
        for (int i = 0; i < questions.size(); i++) {
            testing.getNextQuestion();
        }
        assertTrue(testing.getNextQuestion().isEmpty());
    }

    @Test
    @DisplayName("Может возвращать пустой список ответов, если текущего вопроса нет")
    void testGetAnswersWhenCurrentQuestionIsNotSet() {
        assertTrue(testing.getCurrentQuestionAnswers().isEmpty());
    }

    @Test
    @DisplayName("Может возвращать список ответов из текущего вопроса")
    void testGetAnswersWhenCurrentQuestionIsSet() {
        for (Answer answer : testing.getCurrentQuestionAnswers()) {
            assertTrue(answers.contains(answer));
        }
    }
}