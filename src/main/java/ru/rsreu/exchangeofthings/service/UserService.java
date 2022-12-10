package ru.rsreu.exchangeofthings.service;

import ru.rsreu.exchangeofthings.database.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(int id);

    User findByUsername(String username);

    User save(User user);

    User update(User user);

    void deleteById(int userId);

    void updateIsBlocked(int userId);
}
