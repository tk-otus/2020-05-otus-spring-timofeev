package ru.otus.hw02.service.user;

import ru.otus.hw02.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getById(int id);

    List<User> getAll();
}
