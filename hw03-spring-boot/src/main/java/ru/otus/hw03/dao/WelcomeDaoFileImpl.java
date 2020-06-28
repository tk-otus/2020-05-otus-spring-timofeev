package ru.otus.hw03.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;
import ru.otus.hw03.configs.GlobalProps;
import ru.otus.hw03.dao.exception.WelcomeMessageLoadingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Repository
public class WelcomeDaoFileImpl implements WelcomeDao {
    private static final Logger logger = LoggerFactory.getLogger(WelcomeDaoFileImpl.class);

    private final String welcomeMessage;

    @Autowired
    WelcomeDaoFileImpl(ResourceLoader loader, GlobalProps props) throws WelcomeMessageLoadingException {
        this.welcomeMessage = readWelcomeFromFile(loader.getResource("classpath:" + props.getWelcomeMessageFile()));
    }

    private String readWelcomeFromFile(Resource file) throws WelcomeMessageLoadingException {
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
