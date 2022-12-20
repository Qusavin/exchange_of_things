package ru.rsreu.exchangeofthings.service.impl;

import ru.rsreu.exchangeofthings.database.dao.DAOFactory;
import ru.rsreu.exchangeofthings.database.dao.SessionDAO;
import ru.rsreu.exchangeofthings.database.dao.UserDAO;
import ru.rsreu.exchangeofthings.persistent.entity.UserWithSession;
import ru.rsreu.exchangeofthings.persistent.entity.Session;
import ru.rsreu.exchangeofthings.persistent.entity.User;
import ru.rsreu.exchangeofthings.persistent.exception.AuthException;
import ru.rsreu.exchangeofthings.support.mapper.UserMapper;
import ru.rsreu.exchangeofthings.service.SessionService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static ru.rsreu.exchangeofthings.constant.GlobalOptions.SESSION_TIME_LIVE;

public class SessionServiceImpl implements SessionService {
    private static volatile SessionServiceImpl instance;
    private SessionDAO sessionDAO;
    private UserDAO userDAO;

    public SessionServiceImpl(SessionDAO sessionDAO, UserDAO userDAO) {
        this.sessionDAO = sessionDAO;
        this.userDAO = userDAO;
    }

    @Override
    public List<UserWithSession> findAll() {
        return UserMapper.mapSessionToUserWithSessionList(sessionDAO.findAll());
    }

    @Override
    public User createSession(String username, String password) {
        User user = this.userDAO.findByUsername(username).orElseThrow(AuthException::new);

        if (user.getBlocked() || !user.getPassword().equals(password)) {
            throw new AuthException();
        }

        Date expiredDate = new Date(System.currentTimeMillis() + SESSION_TIME_LIVE);
        Session session = new Session(expiredDate, user);

        sessionDAO.save(session);

        return user;
    }

    @Override
    public void deleteSession(User user) {
        sessionDAO.deleteSession(user.getId());
    }

    @Override
    public Optional<Session> getSession(int userId) {
        return sessionDAO.getSession(userId);
    }

    public static SessionServiceImpl getInstance() {
        synchronized (SessionServiceImpl.class) {
            if (instance == null) {
                SessionDAO sessionDAO = DAOFactory.getSessionDAO();
                UserDAO userDAO = DAOFactory.getUserDAO();

                instance = new SessionServiceImpl(sessionDAO, userDAO);
            }
        }

        return instance;
    }
}
