package ru.otus.hw03.service.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.hw03.dao.QuestionDao;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Сервис QuestionServiceImplTest")
@SpringBootTest(classes = QuestionServiceImpl.class)
class QuestionServiceImplTest {

    @MockBean
    private QuestionDao dao;

    @Autowired
    private QuestionService questionService;

    @Test
    @DisplayName("Должен вызывать метод dao.getById()")
    void testGetById() {
        questionService.getById(1);
        verify(dao, times(1)).getById(1);
    }

    @Test
    @DisplayName("Должен вызывать метод dao.getAll()")
    void getAll() {
        questionService.getAll();
        verify(dao, times(1)).getAll();
    }
}
