package ru.otus.hw03.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.hw03.service.console.ConsolePrintService;

import java.io.IOException;

@Service
public class AcquaintanceServiceImpl implements AcquaintanceService {
    private static final Logger logger = LoggerFactory.getLogger(AcquaintanceServiceImpl.class);

    private final ConsolePrintService printService;
    private String firstName;
    private String lastName;

    @Autowired
    public AcquaintanceServiceImpl(ConsolePrintService printService) {
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

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }
}
