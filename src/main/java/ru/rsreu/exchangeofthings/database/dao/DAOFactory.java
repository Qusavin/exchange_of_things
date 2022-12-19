package ru.rsreu.exchangeofthings.database.dao;

import ru.rsreu.exchangeofthings.database.dao.impl.ExchangeRequestDAOImpl;
import ru.rsreu.exchangeofthings.database.dao.impl.ItemDAOImpl;
import ru.rsreu.exchangeofthings.database.dao.impl.SessionDAOImpl;
import ru.rsreu.exchangeofthings.database.dao.impl.UserDAOImpl;

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

    public static ExchangeRequestDAO getExchangeRequestDAO() {
        return ExchangeRequestDAOImpl.getInstance();
    }
}

