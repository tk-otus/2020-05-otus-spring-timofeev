package ru.otus.hw02.domain.exception;

public class TooManyCorrectAnswersException extends RuntimeException {
    public TooManyCorrectAnswersException(String message) {
        super(message);
    }

    public TooManyCorrectAnswersException(String message, Throwable cause) {
        super(message, cause);
    }
}
