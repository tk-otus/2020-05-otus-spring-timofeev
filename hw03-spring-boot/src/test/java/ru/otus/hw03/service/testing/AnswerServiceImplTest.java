package ru.otus.hw03.service.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.hw03.dao.AnswerDao;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Сервис AnswerServiceImplTest")
@SpringBootTest(classes = AnswerServiceImpl.class)
class AnswerServiceImplTest {

    @MockBean
    private AnswerDao dao;

    @Autowired
    private AnswerService answerService;

    @Test
    @DisplayName("Должен вызывать метод dao.getById()")
    void testGetById() {
        answerService.getById(1);
        verify(dao, times(1)).getById(1);
    }

    @Test
    @DisplayName("Должен вызывать метод dao.getByQuestionId()")
    void testGetByQuestionId() {
        answerService.getByQuestionId(1);
        verify(dao, times(1)).getByQuestionId(1);
    }

    @Test
    @DisplayName("Должен вызывать метод dao.getByQuestionId()")
    void testGetAll() {
        answerService.getAll();
        verify(dao, times(1)).getAll();
    }
}
