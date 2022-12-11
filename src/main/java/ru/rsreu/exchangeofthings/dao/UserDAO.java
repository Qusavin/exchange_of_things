package ru.rsreu.exchangeofthings.dao;

import ru.rsreu.exchangeofthings.database.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> findAll();

    Optional<User> findById(int id);

    Optional<User> findByUsername(String username);

    Optional<User> save(User user);

    Optional<User> update(User user);

    void deleteById(int userId);

    void updateIsBlocked(int userId);
}