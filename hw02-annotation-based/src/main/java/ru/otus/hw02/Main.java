package ru.otus.hw02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.hw02.service.QuestionConsolePrintService;
import ru.otus.hw02.service.QuestionService;

@ComponentScan
public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Main.class);
        var questionService = context.getBean(QuestionService.class);
        var printService = context.getBean(QuestionConsolePrintService.class);
        printService.print(questionService.getAll());
        context.close();
    }
}
