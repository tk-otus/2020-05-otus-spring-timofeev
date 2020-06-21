package ru.otus.hw03.dao.exception;

import java.io.IOException;

public class WelcomeMessageLoadingException extends IOException {
    public WelcomeMessageLoadingException(String message) {
        super(message);
    }

    public WelcomeMessageLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
