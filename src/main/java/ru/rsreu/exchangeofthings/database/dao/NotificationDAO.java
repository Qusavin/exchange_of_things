package ru.rsreu.exchangeofthings.database.dao;


import ru.rsreu.exchangeofthings.persistent.entity.Notification;

import java.util.List;

/**
 * Interface for Notification dao.
 */
public interface NotificationDAO {

    /**
     * Saves Notification Entity
     *
     * @param notification Notification Entity to save
     */
    void save(Notification notification);

    /**
     * Deletes Notification Entity from DB by ID
     *
     * @param id ID of Notification Entity
     */
    void deleteById(int id);


    /**
     * Finds all notification for User
     *
     * @param userId ID of User
     * @return list of Notification objects
     */
    List<Notification> findByReceiverAndSenderId(int userId);
}
