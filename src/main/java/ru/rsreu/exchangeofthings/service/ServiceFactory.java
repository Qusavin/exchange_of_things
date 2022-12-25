package ru.rsreu.exchangeofthings.service;

import ru.rsreu.exchangeofthings.service.impl.*;

public class ServiceFactory {
    private ServiceFactory() {
    }

    public static UserService getUserService() {
        return UserServiceImpl.getInstance();
    }

    public static SessionService getSessionService() {
        return SessionServiceImpl.getInstance();
    }

    public static ItemService getItemService() {
        return ItemServiceImpl.getInstance();
    }

    public static ExchangeRequestService getExchangeRequestService() {
        return ExchangeRequestServiceImpl.getInstance();
    }

    public static NotificationService getNotificationService() {
        return NotificationServiceImpl.getInstance();
    }
}
