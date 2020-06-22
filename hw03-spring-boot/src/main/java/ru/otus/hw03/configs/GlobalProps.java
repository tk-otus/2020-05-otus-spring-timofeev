package ru.otus.hw03.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "global")
public class GlobalProps {
    private String answersCsvfile;
    private String questionsCsvfile;
    private String welcomeMessageFile;

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
