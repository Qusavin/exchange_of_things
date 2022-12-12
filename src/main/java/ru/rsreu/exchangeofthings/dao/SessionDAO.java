package ru.rsreu.exchangeofthings.dao;

import ru.rsreu.exchangeofthings.database.entity.Session;

import java.util.List;

public interface SessionDAO {
    List<Session> findAll();

    void save(Session session);
}
