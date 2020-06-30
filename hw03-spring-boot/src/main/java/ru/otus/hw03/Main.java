package ru.otus.hw03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.hw03.configs.GlobalProps;
import ru.otus.hw03.service.TestingApplication;

@SpringBootApplication
@EnableConfigurationProperties(GlobalProps.class)
public class Main {
    public static void main(String[] args) {
        var context = SpringApplication.run(Main.class, args);
        var application = context.getBean(TestingApplication.class);
        application.run();
        context.close();
    }
}
