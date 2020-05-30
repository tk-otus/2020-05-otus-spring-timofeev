package ru.otus.hw01.service;

import ru.otus.hw01.dao.AnswerDao;

public class AnswerServiceImpl implements AnswerService {
    private AnswerDao dao;

    AnswerServiceImpl(AnswerDao dao) {
        this.dao = dao;
    }
}
