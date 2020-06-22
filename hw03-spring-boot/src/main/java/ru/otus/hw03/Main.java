package ru.otus.hw03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.hw03.configs.GlobalProps;
import ru.otus.hw03.service.console.ConsolePrintService;
import ru.otus.hw03.service.testing.TestingService;
import ru.otus.hw03.service.user.AcquaintanceService;
import ru.otus.hw03.service.user.WelcomeService;

@SpringBootApplication
@EnableConfigurationProperties(GlobalProps.class)
public class Main {
    public static void main(String[] args) {
        var context = SpringApplication.run(Main.class, args);
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
