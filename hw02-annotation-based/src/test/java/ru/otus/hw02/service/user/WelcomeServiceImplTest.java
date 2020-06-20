package ru.otus.hw02.service.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw02.dao.WelcomeDao;
import ru.otus.hw02.dao.WelcomeDaoFileImpl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест WelcomeServiceImplTest")
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
        assertThat(welcomeService.getWelcomeMessage()).isEqualTo(WELCOME_LOGO);
    }
}
