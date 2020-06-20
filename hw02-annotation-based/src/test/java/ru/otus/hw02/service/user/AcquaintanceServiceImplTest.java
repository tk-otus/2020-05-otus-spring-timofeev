package ru.otus.hw02.service.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.hw02.service.console.ConsolePrintService;
import ru.otus.hw02.service.user.AcquaintanceService;
import ru.otus.hw02.service.user.AcquaintanceServiceImpl;

import java.io.IOException;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Тест AcquaintanceServiceImplTest")
@SpringBootTest(classes = AcquaintanceServiceImpl.class)
class AcquaintanceServiceImplTest {

    @MockBean
    ConsolePrintService printService;

    @Autowired
    AcquaintanceService acquaintanceService;

    @Test
    @DisplayName("Сервис может считать имя и фамилию пользователя")
    void testCanReadUserData() throws IOException {
        String firstName = "Ivan";
        String lastName = "Ivanov";
        given(printService.read()).willReturn(firstName).willReturn(lastName);

        acquaintanceService.run();

        assertThat(acquaintanceService.getFirstName()).isEqualTo(firstName);
        assertThat(acquaintanceService.getLastName()).isEqualTo(lastName);
    }
}
