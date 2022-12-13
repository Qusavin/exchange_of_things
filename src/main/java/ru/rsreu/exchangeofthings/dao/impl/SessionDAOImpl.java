package ru.rsreu.exchangeofthings.dao.impl;

import ru.rsreu.exchangeofthings.dao.AbstractDAO;
import ru.rsreu.exchangeofthings.dao.SessionDAO;
import ru.rsreu.exchangeofthings.database.entity.Session;
import ru.rsreu.exchangeofthings.mapper.DAOMapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void save(Session session) {
        String query = resourcer.getString("query.session.save");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, session.getUser().getId());
            statement.setDate(2, new Date(session.getExpiredAt().getTime()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteSession(int userId) {
        String query = resourcer.getString("query.session.delete");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Session> getSession(int userId) {
        String query = resourcer.getString("query.session.find.by.id");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, userId);

            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                return Optional.of(DAOMapper.mapSession(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
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
