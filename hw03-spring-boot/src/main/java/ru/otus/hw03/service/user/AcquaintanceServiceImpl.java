package ru.otus.hw03.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.hw03.configs.GlobalProps;
import ru.otus.hw03.service.console.ConsolePrintService;

import java.io.IOException;

@Service
public class AcquaintanceServiceImpl implements AcquaintanceService {
    private static final Logger logger = LoggerFactory.getLogger(AcquaintanceServiceImpl.class);

    private final ConsolePrintService printService;
    private final MessageSource messageSource;
    private final GlobalProps props;
    private String firstName;
    private String lastName;

    @Autowired
    public AcquaintanceServiceImpl(ConsolePrintService printService, MessageSource messageSource, GlobalProps props) {
        this.printService = printService;
        this.messageSource = messageSource;
        this.props = props;
    }

    @Override
    public void run() {
        try {
            printService.print(messageSource.getMessage("acquaintance.get.first.name", null, props.getLocale()));
            firstName = printService.read();
            printService.print(messageSource.getMessage("acquaintance.get.last.name", null, props.getLocale()));
            lastName = printService.read();
            printService.print(messageSource.getMessage("acquaintance.hello.user", new String[]{firstName, lastName}, props.getLocale()));
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
