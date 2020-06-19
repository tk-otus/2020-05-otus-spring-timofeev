package ru.otus.hw02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.hw02.service.AcquaintanceService;
import ru.otus.hw02.service.ConsolePrintService;
import ru.otus.hw02.service.QuestionService;
import ru.otus.hw02.service.WelcomeService;

@ComponentScan
public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Main.class);
        var questionService = context.getBean(QuestionService.class);
        var printService = context.getBean(ConsolePrintService.class);
        var welcomeService = context.getBean(WelcomeService.class);
        var acquaintanceService = context.getBean(AcquaintanceService.class);

        printService.print(welcomeService.getWelcomeMessage());
        acquaintanceService.run();
        printService.print(questionService.getAll());
        context.close();
    }
}
