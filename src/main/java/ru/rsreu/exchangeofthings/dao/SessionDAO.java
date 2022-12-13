package ru.rsreu.exchangeofthings.dao;

import ru.rsreu.exchangeofthings.database.entity.Session;
import ru.rsreu.exchangeofthings.database.entity.User;

import java.util.List;
import java.util.Optional;

public interface SessionDAO {
    List<Session> findAll();

    void save(Session session);

    void deleteSession(int userId);

    Optional<Session> getSession(int userId);
}
