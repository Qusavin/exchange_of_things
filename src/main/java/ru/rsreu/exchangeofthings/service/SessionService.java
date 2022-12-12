package ru.rsreu.exchangeofthings.service;

import ru.rsreu.exchangeofthings.data.UserWithSession;
import ru.rsreu.exchangeofthings.database.entity.User;

import java.util.List;

public interface SessionService {
    List<UserWithSession> findAll();

    User createSession(String username, String password);
}
