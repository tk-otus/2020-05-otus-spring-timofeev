package ru.otus.hw02.dao;

import ru.otus.hw02.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<User> getById(int id);

    List<User> getAll();
}
