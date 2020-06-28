package ru.otus.hw03.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.hw03.configs.GlobalProps;
import ru.otus.hw03.domain.User;
import ru.otus.hw03.service.console.PrintService;

@Service
public class AcquaintanceServiceImpl implements AcquaintanceService {
    private static final Logger logger = LoggerFactory.getLogger(AcquaintanceServiceImpl.class);

    private final PrintService printService;
    private final MessageSource messageSource;
    private final GlobalProps props;

    @Autowired
    public AcquaintanceServiceImpl(PrintService printService, MessageSource messageSource, GlobalProps props) {
        this.printService = printService;
        this.messageSource = messageSource;
        this.props = props;
    }

    @Override
    public User makeAcquaintance() {
        printService.print(messageSource.getMessage("acquaintance.get.first.name", null, props.getLocale()));
        String firstName = printService.read();
        printService.print(messageSource.getMessage("acquaintance.get.last.name", null, props.getLocale()));
        String lastName = printService.read();
        printService.print(messageSource.getMessage("acquaintance.hello.user", new String[]{firstName, lastName}, props.getLocale()));
        return new User(firstName, lastName);
    }
}
