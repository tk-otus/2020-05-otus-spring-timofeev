package ru.otus.hw03;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.hw03.configs.GlobalProps;
import ru.otus.hw03.service.TestingApplication;

@SpringBootApplication
@EnableConfigurationProperties(GlobalProps.class)
public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Main.class);
        var application = context.getBean(TestingApplication.class);
        application.run();
        context.close();
    }
}
