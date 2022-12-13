package ru.rsreu.exchangeofthings.service;

import ru.rsreu.exchangeofthings.database.entity.User;
import ru.rsreu.exchangeofthings.enums.Role;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(int id);

    User findByUsername(String username);

    User add(
            String name,
            String username,
            String password,
            Role role
    );

    User update(
            int id,
            String name,
            String username,
            String password,
            boolean isBLocked,
            Role role
    );

    void deleteById(int userId);

    void updateIsBlocked(int userId);
}
