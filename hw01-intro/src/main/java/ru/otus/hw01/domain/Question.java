package ru.otus.hw01.domain;

import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {
    private final QAType type;
    private final String questionText;
    private final List<Answer> answers = new ArrayList<>();
    private final List<Answer> correctAnswers = new ArrayList<>();

    public Question(@NonNull QAType type, @NonNull String questionText) {
        this.type = type;
        this.questionText = questionText;
    }

    public boolean checkAnswers(Answer answer) {
        return checkAnswers(Collections.singletonList(answer));
    }

    public boolean checkAnswers(List<Answer> answers) {
        return answers.containsAll(correctAnswers);
    }

    public void addAnswer(Answer answer) {
        if (type == QAType.ONE && answer.isCorrect() && correctAnswers.size() == 1) {
            throw new RuntimeException("Нельзя добавить больше одного правильного ответа");
        }
        if (canAddAnswer(answer)) {
        answers.add(answer);
            if (answer.isCorrect()) {
                correctAnswers.add(answer);
            }
        }
    }

    public boolean canAddAnswer(Answer answer) {
        return !(answers.contains(answer)
                || type == QAType.ONE
                && answer.isCorrect()
                && correctAnswers.size() == 1);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<Answer> getCorrectAnswers() {
        return correctAnswers;
    }

    public QAType getType() {
        return type;
    }

    public String getQuestionText() {
        return questionText;
    }

    @Override
    public String toString() {
        return "Question{" +
                "type=" + type +
                ", questionText='" + questionText + '\'' +
                ", answers=" + answers +
                '}';
    }
}
