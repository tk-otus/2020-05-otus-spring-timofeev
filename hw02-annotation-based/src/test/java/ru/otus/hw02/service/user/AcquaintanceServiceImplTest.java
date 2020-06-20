package ru.otus.hw02.service.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw02.service.console.ConsolePrintService;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


@DisplayName("Сервис AcquaintanceServiceImplTest")
class AcquaintanceServiceImplTest {
    ConsolePrintService printService;
    AcquaintanceService acquaintanceService;

    @BeforeEach
    void setUp() {
        printService = mock(ConsolePrintService.class);
        acquaintanceService = new AcquaintanceServiceImpl(printService);
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
