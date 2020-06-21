package ru.otus.hw02.dao;

import org.springframework.stereotype.Repository;
import ru.otus.hw02.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoRuntimeImpl implements UserDao {
    @Override
    public Optional<User> getById(int id) {
        throw new UnsupportedOperationException("This method is not supported");
    }

    @Override
    public List<User> getAll() {
        throw new UnsupportedOperationException("This method is not supported");
    }
}
