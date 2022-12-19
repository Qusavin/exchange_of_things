package ru.rsreu.exchangeofthings.database.dao;

import ru.rsreu.exchangeofthings.persistent.entity.Session;

import java.util.List;
import java.util.Optional;

public interface SessionDAO {
    List<Session> findAll();

    void save(Session session);

    void deleteSession(int userId);

    Optional<Session> getSession(int userId);
}
