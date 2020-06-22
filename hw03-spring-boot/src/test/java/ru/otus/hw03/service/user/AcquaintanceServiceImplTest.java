package ru.otus.hw03.service.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import ru.otus.hw03.configs.GlobalProps;
import ru.otus.hw03.service.console.ConsolePrintService;
import ru.otus.hw03.service.user.AcquaintanceService;
import ru.otus.hw03.service.user.AcquaintanceServiceImpl;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


@DisplayName("Сервис AcquaintanceServiceImplTest")
class AcquaintanceServiceImplTest {
    ConsolePrintService printService;
    MessageSource messageSource;
    GlobalProps globalProps;
    AcquaintanceService acquaintanceService;

    @BeforeEach
    void setUp() {
        printService = mock(ConsolePrintService.class);
        messageSource = mock(MessageSource.class);
        globalProps = mock(GlobalProps.class);
        acquaintanceService = new AcquaintanceServiceImpl(printService, messageSource, globalProps);
    }

    @Test
    @DisplayName("Может считать имя и фамилию пользователя")
    void testCanReadUserData() throws IOException {
        String firstName = "Ivan";
        String lastName = "Ivanov";
        given(printService.read()).willReturn(firstName).willReturn(lastName);

        acquaintanceService.run();

        assertEquals(firstName, acquaintanceService.getFirstName());
        assertEquals(lastName, acquaintanceService.getLastName());
    }
}
