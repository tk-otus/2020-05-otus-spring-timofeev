package ru.otus.hw02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.hw02.service.TestingApplication;

@ComponentScan
@PropertySource("classpath:app.properties")
public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Main.class);
        var application = context.getBean(TestingApplication.class);
        application.run();
        context.close();
    }
}
