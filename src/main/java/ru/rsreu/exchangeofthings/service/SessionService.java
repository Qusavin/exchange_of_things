package ru.rsreu.exchangeofthings.service;

import ru.rsreu.exchangeofthings.persistent.response.UserWithSession;
import ru.rsreu.exchangeofthings.persistent.entity.Session;
import ru.rsreu.exchangeofthings.persistent.entity.User;

import java.util.List;
import java.util.Optional;

public interface SessionService {
    List<UserWithSession> findAll();

    User createSession(String username, String password);

    void deleteSession(User user);

    Optional<Session> getSession(int userId);
}
