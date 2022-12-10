package ru.rsreu.exchangeofthings.service.impl;

import ru.rsreu.exchangeofthings.dao.DAOFactory;
import ru.rsreu.exchangeofthings.dao.SessionDAO;
import ru.rsreu.exchangeofthings.data.UserWithSession;
import ru.rsreu.exchangeofthings.mapper.UserMapper;
import ru.rsreu.exchangeofthings.service.SessionService;

import java.util.List;

public class SessionServiceImpl implements SessionService {
    private static volatile SessionServiceImpl instance;
    private SessionDAO sessionDAO;

    public SessionServiceImpl(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }

    @Override
    public List<UserWithSession> findAll() {
        return UserMapper.mapSessionToUserWithSessionList(sessionDAO.findAll());
    }

    public static SessionServiceImpl getInstance() {
        synchronized (SessionServiceImpl.class) {
            if (instance == null) {
                SessionDAO sessionDAO = DAOFactory.getSessionDAO();
                instance = new SessionServiceImpl(sessionDAO);
            }
        }

        return instance;
    }
}
