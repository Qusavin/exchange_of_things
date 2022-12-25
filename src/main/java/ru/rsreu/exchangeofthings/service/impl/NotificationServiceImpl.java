package ru.rsreu.exchangeofthings.service.impl;

import ru.rsreu.exchangeofthings.database.dao.DAOFactory;
import ru.rsreu.exchangeofthings.database.dao.NotificationDAO;
import ru.rsreu.exchangeofthings.persistent.entity.Notification;
import ru.rsreu.exchangeofthings.service.NotificationService;

import java.util.List;

public class NotificationServiceImpl implements NotificationService {
    private static volatile NotificationServiceImpl instance;

    private NotificationDAO notificationDAO;

    public NotificationServiceImpl(NotificationDAO notificationDAO) {
        this.notificationDAO = notificationDAO;
    }

    @Override
    public void deleteById(int id) {
        notificationDAO.deleteById(id);
    }

    @Override
    public List<Notification> findByUserId(int userId) {
        return notificationDAO.findByReceiverAndSenderId(userId);
    }

    public static NotificationServiceImpl getInstance() {
        synchronized (NotificationServiceImpl.class) {
            if (instance == null) {
                NotificationDAO notificationDAO = DAOFactory.getNotificationDAO();


                instance = new NotificationServiceImpl(notificationDAO);
            }
        }

        return instance;
    }
}
