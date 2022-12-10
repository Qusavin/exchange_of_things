package ru.rsreu.exchangeofthings.service.impl;

import ru.rsreu.exchangeofthings.dao.DAOFactory;
import ru.rsreu.exchangeofthings.dao.UserDAO;
import ru.rsreu.exchangeofthings.database.entity.User;
import ru.rsreu.exchangeofthings.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static volatile UserServiceImpl instance;
    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findById(int id) {
        return userDAO.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public User save(User user) {
        return userDAO.save(user).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public User update(User user) {
        return userDAO.update(user).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public void deleteById(int userId) {
        userDAO.deleteById(userId);
    }

    @Override
    public void updateIsBlocked(int userId) {
        userDAO.updateIsBlocked(userId);
    }

    public static UserServiceImpl getInstance() {
        synchronized (UserServiceImpl.class) {
            if (instance == null) {
                UserDAO userDAO = DAOFactory.getUserDAO();
                instance = new UserServiceImpl(userDAO);
            }
        }

        return instance;
    }
}
