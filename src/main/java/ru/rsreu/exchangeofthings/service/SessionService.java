package ru.rsreu.exchangeofthings.service;

import ru.rsreu.exchangeofthings.data.UserWithSession;

import java.util.List;

public interface SessionService {
    List<UserWithSession> findAll();
}
