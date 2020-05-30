package ru.otus.hw01.service;

import ru.otus.hw01.dao.QuestionDao;

public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }
}
