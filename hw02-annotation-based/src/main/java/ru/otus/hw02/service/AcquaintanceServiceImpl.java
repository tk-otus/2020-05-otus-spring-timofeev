package ru.otus.hw02.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AcquaintanceServiceImpl implements AcquaintanceService {
    private static final Logger logger = LoggerFactory.getLogger(AcquaintanceServiceImpl.class);

    private final ConsolePrintService printService;
    private String firstName;
    private String lastName;

    @Autowired
    AcquaintanceServiceImpl(ConsolePrintService printService) {
        this.printService = printService;
    }

    @Override
    public void run() {
        try {
            printService.print("Enter your first name:");
            firstName = printService.read();
            printService.print("Enter your last name:");
            lastName = printService.read();
            printService.print(String.format("Hello, %s %s!", firstName, lastName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
