package ru.otus.hw02.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import ru.otus.hw02.dao.exception.WelcomeMessageLoadingException;

import java.io.*;

@Repository
public class WelcomeDaoFileImpl implements WelcomeDao {
    private static final Logger logger = LoggerFactory.getLogger(WelcomeDaoFileImpl.class);

    private final Resource file;
    private final String welcomeMessage;

    WelcomeDaoFileImpl(@Value("classpath:${global.welcome.message.file}") Resource file) throws WelcomeMessageLoadingException {
        this.file = file;
        this.welcomeMessage = readWelcomeFromFile();
    }

    private String readWelcomeFromFile() throws WelcomeMessageLoadingException {
        final var result = new StringBuilder();
        try (var fileReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line = null;
            while ((line = fileReader.readLine()) != null) {
                result.append(line);
                result.append(System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            throw new WelcomeMessageLoadingException("Welcome file (" + file.getFilename() + ") not found", e);
        }
        return result.toString();
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }
}
