package ru.otus.hw02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.hw02.service.console.PrintService;
import ru.otus.hw02.service.testing.TestingService;
import ru.otus.hw02.service.user.AcquaintanceService;
import ru.otus.hw02.service.user.WelcomeService;

@Service
public class TestingApplication implements Runnable {
    private final PrintService printService;
    private final WelcomeService welcomeService;
    private final AcquaintanceService acquaintanceService;
    private final TestingService testingService;

    @Autowired
    public TestingApplication(PrintService printService, WelcomeService welcomeService, AcquaintanceService acquaintanceService, TestingService testingService) {
        this.printService = printService;
        this.welcomeService = welcomeService;
        this.acquaintanceService = acquaintanceService;
        this.testingService = testingService;
    }

    @Override
    public void run() {
        printService.print(welcomeService.getWelcomeMessage());
        acquaintanceService.makeAcquaintance();
        testingService.run();
    }
}
