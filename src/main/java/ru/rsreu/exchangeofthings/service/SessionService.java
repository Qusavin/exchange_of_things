package ru.rsreu.exchangeofthings.service;

import ru.rsreu.exchangeofthings.data.UserWithSession;
import ru.rsreu.exchangeofthings.database.entity.Session;
import ru.rsreu.exchangeofthings.database.entity.User;

import java.util.List;
import java.util.Optional;

public interface SessionService {
    List<UserWithSession> findAll();

    User createSession(String username, String password);

    void deleteSession(User user);

    Optional<Session> getSession(int userId);
}
