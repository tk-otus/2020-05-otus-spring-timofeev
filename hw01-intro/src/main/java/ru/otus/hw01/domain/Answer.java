package ru.otus.hw01.domain;

import org.springframework.lang.NonNull;

import java.util.Objects;

public class Answer {
    private final String answer;
    private final boolean isCorrect;

    public Answer(@NonNull String answer, @NonNull boolean isCorrect) {
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "text='" + answer + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return this.answer.equals(answer.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer);
    }
}
