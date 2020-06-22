package ru.otus.hw03.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "global")
public class GlobalProps {
    private Locale locale;
    private String answersCsvfile;
    private String questionsCsvfile;
    private String welcomeMessageFile;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getAnswersCsvfile() {
        return answersCsvfile;
    }

    public void setAnswersCsvfile(String answersCsvfile) {
        this.answersCsvfile = answersCsvfile;
    }

    public String getQuestionsCsvfile() {
        return questionsCsvfile;
    }

    public void setQuestionsCsvfile(String questionsCsvfile) {
        this.questionsCsvfile = questionsCsvfile;
    }

    public String getWelcomeMessageFile() {
        return welcomeMessageFile;
    }

    public void setWelcomeMessageFile(String welcomeMessageFile) {
        this.welcomeMessageFile = welcomeMessageFile;
    }
}
