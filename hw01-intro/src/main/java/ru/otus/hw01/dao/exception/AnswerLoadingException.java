package ru.otus.hw01.dao.exception;

import java.io.IOException;

public class AnswerLoadingException extends IOException {
    public AnswerLoadingException(String message) {
        super(message);
    }

    public AnswerLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
