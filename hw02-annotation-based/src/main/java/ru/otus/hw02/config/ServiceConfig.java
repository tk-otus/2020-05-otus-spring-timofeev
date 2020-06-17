package ru.otus.hw02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.hw02.dao.AnswerDao;
import ru.otus.hw02.dao.QuestionDao;
import ru.otus.hw02.service.*;

@Configuration
public class ServiceConfig {
    @Bean
    AnswerService answerService(AnswerDao dao) {
        return new AnswerServiceImpl(dao);
    }

    @Bean
    QuestionService questionService(QuestionDao dao) {
        return new QuestionServiceImpl(dao);
    }

    @Bean
    QuestionConsolePrintService questionPrintService() {
        return new QuestionConsolePrintServiceImpl(System.out);
    }
}
