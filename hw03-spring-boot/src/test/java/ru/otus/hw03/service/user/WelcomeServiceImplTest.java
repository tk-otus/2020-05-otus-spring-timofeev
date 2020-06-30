package ru.otus.hw03.service.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.hw03.dao.WelcomeDao;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Сервис WelcomeServiceImpl")
@SpringBootTest(classes = WelcomeServiceImpl.class)
class WelcomeServiceImplTest {

    @MockBean
    private WelcomeDao dao;

    @Autowired
    private WelcomeService welcomeService;

    @Test
    @DisplayName("Вызывает метод dao.getWelcomeMessage()")
    void testGetWelcomeMessage() {
        welcomeService.getWelcomeMessage();
        verify(dao, times(1)).getWelcomeMessage();
    }
}
