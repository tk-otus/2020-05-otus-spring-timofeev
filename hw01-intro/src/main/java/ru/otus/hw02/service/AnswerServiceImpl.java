package ru.otus.hw01.service;

import ru.otus.hw01.dao.AnswerDao;
import ru.otus.hw01.domain.Answer;

import java.util.List;
import java.util.Optional;

public class AnswerServiceImpl implements AnswerService {
    private final AnswerDao dao;

    AnswerServiceImpl(AnswerDao dao) {
        this.dao = dao;
    }

    @Override
    public Optional<Answer> getById(int id) {
        return dao.getById(id);
    }

    @Override
    public List<Answer> getByQuestionId(int id) {
        return dao.getByQuestionId(id);
    }

    @Override
    public List<Answer> getAll() {
        return dao.getAll();
    }
}
