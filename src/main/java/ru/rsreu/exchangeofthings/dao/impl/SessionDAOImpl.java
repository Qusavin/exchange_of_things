package ru.rsreu.exchangeofthings.dao.impl;

import ru.rsreu.exchangeofthings.dao.AbstractDAO;
import ru.rsreu.exchangeofthings.dao.SessionDAO;
import ru.rsreu.exchangeofthings.database.entity.Session;
import ru.rsreu.exchangeofthings.mapper.DAOMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessionDAOImpl extends AbstractDAO implements SessionDAO {
    private static volatile SessionDAOImpl instance;

    @Override
    public List<Session> findAll() {
        String query = resourcer.getString("query.session.find.all");
        List<Session> sessions = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(query)) {
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                sessions.add(DAOMapper.mapSession(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sessions;
    }

    public static SessionDAOImpl getInstance() {
        synchronized (SessionDAOImpl.class) {
            if (instance == null) {
                instance = new SessionDAOImpl();
            }
        }

        return instance;
    }
}