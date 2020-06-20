package ru.otus.hw02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.hw02.service.console.ConsolePrintService;
import ru.otus.hw02.service.testing.QuestionService;
import ru.otus.hw02.service.testing.TestingService;
import ru.otus.hw02.service.user.AcquaintanceService;
import ru.otus.hw02.service.user.WelcomeService;

@ComponentScan
public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Main.class);
        var questionService = context.getBean(QuestionService.class);
        var printService = context.getBean(ConsolePrintService.class);
        var welcomeService = context.getBean(WelcomeService.class);
        var acquaintanceService = context.getBean(AcquaintanceService.class);
        var testingService = context.getBean(TestingService.class);

        printService.print(welcomeService.getWelcomeMessage());
        acquaintanceService.run();
        testingService.run();
        context.close();
    }
}
