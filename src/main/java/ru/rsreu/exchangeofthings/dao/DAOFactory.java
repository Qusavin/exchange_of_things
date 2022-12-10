package ru.rsreu.exchangeofthings.dao;

import ru.rsreu.exchangeofthings.dao.impl.ItemDAOImpl;
import ru.rsreu.exchangeofthings.dao.impl.SessionDAOImpl;
import ru.rsreu.exchangeofthings.dao.impl.UserDAOImpl;

public class DAOFactory {
    private DAOFactory() {
    }

    public static UserDAO getUserDAO() {
        return UserDAOImpl.getInstance();
    }

    public static SessionDAO getSessionDAO() {
        return SessionDAOImpl.getInstance();
    }

    public static ItemDAO getItemDAO() {
        return ItemDAOImpl.getInstance();
    }
}

