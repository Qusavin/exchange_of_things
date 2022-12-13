package ru.rsreu.exchangeofthings.service.impl;

import ru.rsreu.exchangeofthings.dao.DAOFactory;
import ru.rsreu.exchangeofthings.dao.UserDAO;
import ru.rsreu.exchangeofthings.database.entity.User;
import ru.rsreu.exchangeofthings.enums.Role;
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
        return userDAO.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username).orElseThrow(RuntimeException::new);
    }

    @Override
    public User add(
            String name,
            String username,
            String password,
            Role role
    ) {
        User user = new User(
                username,
                password,
                name,
                role.getName()
        );

        return userDAO.save(user).orElseThrow(RuntimeException::new);
    }

    @Override
    public User update(
            int id,
            String name,
            String username,
            String password,
            boolean isBLocked,
            Role role
    ) {
        User user = new User(
                id,
                username,
                password,
                name,
                isBLocked,
                role.getName()
        );

        return userDAO.update(user).orElseThrow(RuntimeException::new);
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
