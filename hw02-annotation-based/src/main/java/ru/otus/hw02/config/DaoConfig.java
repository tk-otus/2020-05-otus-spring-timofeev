package ru.otus.hw02.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import ru.otus.hw02.dao.AnswerDao;
import ru.otus.hw02.dao.AnswerDaoCsvImpl;
import ru.otus.hw02.dao.QuestionDao;
import ru.otus.hw02.dao.QuestionDaoCsvImpl;
import ru.otus.hw02.dao.exception.AnswerLoadingException;
import ru.otus.hw02.dao.exception.QuestionLoadingException;

@Configuration
public class DaoConfig {

    @Bean
    AnswerDao answerDao(@Value("classpath:answers.csv") Resource file) throws AnswerLoadingException {
        return new AnswerDaoCsvImpl(file);
    }

    @Bean
    QuestionDao questionDao(@Value("classpath:questions.csv") Resource file, AnswerDao answerDao) throws QuestionLoadingException {
        return new QuestionDaoCsvImpl(file, answerDao);
    }

}
