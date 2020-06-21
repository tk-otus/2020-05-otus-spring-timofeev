package ru.otus.hw02.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.hw02.dao.UserDao;
import ru.otus.hw02.domain.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao dao;

    @Autowired
    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public Optional<User> getById(int id) {
        return dao.getById(id);
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }
}
