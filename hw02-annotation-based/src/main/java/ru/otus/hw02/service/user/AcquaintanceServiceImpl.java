package ru.otus.hw02.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.hw02.domain.User;
import ru.otus.hw02.service.console.PrintService;

@Service
public class AcquaintanceServiceImpl implements AcquaintanceService {
    private static final Logger logger = LoggerFactory.getLogger(AcquaintanceServiceImpl.class);

    private final PrintService printService;
    private User user;

    @Autowired
    public AcquaintanceServiceImpl(PrintService printService) {
        this.printService = printService;
    }

    @Override
    public User makeAcquaintance() {
        printService.print("Enter your first name:");
        String firstName = printService.read();
        printService.print("Enter your last name:");
        String lastName = printService.read();
        printService.print(String.format("Hello, %s %s!", firstName, lastName));
        user = new User(firstName, lastName);
        return user;
    }

    public User getUser() {
        return user;
    }
}
