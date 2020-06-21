package ru.otus.hw03.dao.exception;

import java.io.IOException;

public class QuestionLoadingException extends IOException {
    public QuestionLoadingException(String message) {
        super(message);
    }

    public QuestionLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
