package ru.rsreu.exchangeofthings.service;

import ru.rsreu.exchangeofthings.persistent.entity.Notification;

import java.util.List;

public interface NotificationService {
    void deleteById(int id);

    List<Notification> findByUserId(int userId);
}
