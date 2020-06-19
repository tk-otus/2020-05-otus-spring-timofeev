package ru.otus.hw02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.hw02.dao.WelcomeDao;

@Service
public class WelcomeServiceImpl implements WelcomeService {
    private final WelcomeDao dao;

    @Autowired
    WelcomeServiceImpl(WelcomeDao dao) {
        this.dao = dao;
    }

    @Override
    public String getWelcomeMessage() {
        return dao.getWelcomeMessage();
    }
}
