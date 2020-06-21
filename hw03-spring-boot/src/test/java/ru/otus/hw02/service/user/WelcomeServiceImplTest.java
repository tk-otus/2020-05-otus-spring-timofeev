package ru.otus.hw02.service.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw03.dao.WelcomeDao;
import ru.otus.hw03.dao.WelcomeDaoFileImpl;
import ru.otus.hw03.service.user.WelcomeService;
import ru.otus.hw03.service.user.WelcomeServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Сервис WelcomeServiceImplTest")
class WelcomeServiceImplTest {
    private static final String WELCOME_LOGO = "WELCOME LOGO";
    private static WelcomeService welcomeService;

    @BeforeAll
    static void setUp() {
        WelcomeDao dao = mock(WelcomeDaoFileImpl.class);
        when(dao.getWelcomeMessage()).thenReturn(WELCOME_LOGO);
        welcomeService = new WelcomeServiceImpl(dao);
    }

    @Test
    @DisplayName("Может вернуть лого из файла")
    void testGetWelcomeMessage() {
        assertEquals(WELCOME_LOGO, welcomeService.getWelcomeMessage());
    }
}
