package ru.otus.hw03.domain;

import org.springframework.lang.NonNull;
import ru.otus.hw03.domain.exception.TooManyCorrectAnswersException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {
    private final int id;
    private final QAType type;
    private final String questionText;
    private final List<Answer> answers = new ArrayList<>();
    private final List<Answer> correctAnswers = new ArrayList<>();

    public Question(@NonNull int id, @NonNull QAType type, @NonNull String questionText) {
        this.id = id;
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
            throw new TooManyCorrectAnswersException("You cannot add more than one correct answer");
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

    public int getId() {
        return id;
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
                "id=" + id +
                ", type=" + type +
                ", questionText='" + questionText + '\'' +
                ", answers=" + answers +
                ", correctAnswers=" + correctAnswers +
                '}';
    }
}
