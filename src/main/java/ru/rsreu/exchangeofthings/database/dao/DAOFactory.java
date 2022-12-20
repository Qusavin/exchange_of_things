package ru.rsreu.exchangeofthings.database.dao;

import ru.rsreu.exchangeofthings.database.dao.impl.ExchangeRequestDAOImpl;
import ru.rsreu.exchangeofthings.database.dao.impl.ItemDAOImpl;
import ru.rsreu.exchangeofthings.database.dao.impl.SessionDAOImpl;
import ru.rsreu.exchangeofthings.database.dao.impl.UserDAOImpl;

/**
 * Factory of dao.
 */
public class DAOFactory {
    /**
     * Private constructor.
     */
    private DAOFactory() {
    }

    /**
     * Get user dao.
     *
     * @return User dao
     */
    public static UserDAO getUserDAO() {
        return UserDAOImpl.getInstance();
    }

    /**
     * Get session dao.
     *
     * @return Session dao
     */
    public static SessionDAO getSessionDAO() {
        return SessionDAOImpl.getInstance();
    }

    /**
     * Get item dao.
     *
     * @return Item dao
     */
    public static ItemDAO getItemDAO() {
        return ItemDAOImpl.getInstance();
    }

    /**
     * Get exchange request dao.
     *
     * @return Exchange request dao
     */
    public static ExchangeRequestDAO getExchangeRequestDAO() {
        return ExchangeRequestDAOImpl.getInstance();
    }
}

