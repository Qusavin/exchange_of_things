package ru.rsreu.exchangeofthings.service;

import ru.rsreu.exchangeofthings.service.impl.ItemServiceImpl;
import ru.rsreu.exchangeofthings.service.impl.SessionServiceImpl;
import ru.rsreu.exchangeofthings.service.impl.UserServiceImpl;

public class ServiceFactory {
    private ServiceFactory() {
    }

    public UserService getUserService() {
        return UserServiceImpl.getInstance();
    }

    public SessionService getSessionService() {
        return SessionServiceImpl.getInstance();
    }

    public ItemService getItemService() {
        return ItemServiceImpl.getInstance();
    }
}
