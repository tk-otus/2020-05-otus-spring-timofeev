package ru.otus.hw01;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.hw01.service.QuestionConsolePrintService;
import ru.otus.hw01.service.QuestionService;

public class Main {
    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("/spring-context.xml");
        var questionService = context.getBean(QuestionService.class);
        var printService = context.getBean(QuestionConsolePrintService.class);

        printService.print(questionService.getAll());

        context.close();
    }
}
