package ru.otus.hw01.domain;

import org.springframework.lang.NonNull;

public class Answer {
    private final int id;
    private final int questionId;
    private final String answer;
    private final boolean isCorrect;

    public Answer(@NonNull int id, @NonNull int questionId, @NonNull String answer, @NonNull boolean isCorrect) {
        this.id = id;
        this.questionId = questionId;
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    public int getId() {
        return id;
    }

    public int getQuestionId() {
        return questionId;
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
                "id=" + id +
                ", questionId=" + questionId +
                ", answer='" + answer + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        return id == answer.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
