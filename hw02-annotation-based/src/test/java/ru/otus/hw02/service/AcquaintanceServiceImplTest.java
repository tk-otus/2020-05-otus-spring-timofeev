package ru.otus.hw02.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.hw02.service.console.ConsolePrintService;
import ru.otus.hw02.service.user.AcquaintanceService;
import ru.otus.hw02.service.user.AcquaintanceServiceImpl;

import java.io.IOException;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = AcquaintanceServiceImpl.class)
class AcquaintanceServiceImplTest {
    AcquaintanceService acquaintanceService;

    @MockBean
    ConsolePrintService printService;

    @BeforeEach
    void setUp() {
        acquaintanceService = new AcquaintanceServiceImpl(printService);
    }

    @Test
    void testWhatUser() throws IOException {
        String firstName = "Ivan";
        String lastName = "Ivanov";
        given(printService.read()).willReturn(firstName).willReturn(lastName);

        acquaintanceService.run();

        assertThat(acquaintanceService.getFirstName()).isEqualTo(firstName);
        assertThat(acquaintanceService.getLastName()).isEqualTo(lastName);
    }
}
