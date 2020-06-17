package ru.otus.hw02.service;

import ru.otus.hw02.dao.QuestionDao;
import ru.otus.hw02.domain.Question;

import java.util.List;
import java.util.Optional;

public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public Optional<Question> getById(int id) {
        return dao.getById(id);
    }

    @Override
    public List<Question> getAll() {
        return dao.getAll();
    }
}
