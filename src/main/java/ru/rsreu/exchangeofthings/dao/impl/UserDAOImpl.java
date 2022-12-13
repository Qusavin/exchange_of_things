package ru.rsreu.exchangeofthings.dao.impl;

import ru.rsreu.exchangeofthings.dao.AbstractDAO;
import ru.rsreu.exchangeofthings.dao.UserDAO;
import ru.rsreu.exchangeofthings.database.entity.User;
import ru.rsreu.exchangeofthings.mapper.DAOMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl extends AbstractDAO implements UserDAO {
    private static volatile UserDAOImpl instance;

    @Override
    public List<User> findAll() {
        String query = resourcer.getString("query.user.find.all");
        List<User> users = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(query)) {
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                users.add(DAOMapper.mapUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public Optional<User> findById(int id) {
        String query = resourcer.getString("query.user.find.by.id");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, id);

            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                return Optional.of(DAOMapper.mapUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();    }

    @Override
    public Optional<User> findByUsername(String username) {
        String query = resourcer.getString("query.user.find.by.username");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, username);

            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                return Optional.of(DAOMapper.mapUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> save(User user) {
        String query = resourcer.getString("query.user.save");
        String[] returnId = {"id"};

        try (PreparedStatement st = connection.prepareStatement(query, returnId)) {
            st.setString(1, user.getName());
            st.setString(2, user.getUsername());
            st.setString(3, user.getPassword());
            st.setString(4, user.getRole());

            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                return Optional.empty();
            }

            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);

                    user.setId(id);

                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> update(User user) {
        String query = resourcer.getString("query.user.update");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, user.getName());
            st.setString(2, user.getUsername());
            st.setString(3, user.getPassword());
            st.setInt(4, user.getId());

            st.executeUpdate();

            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(int userId) {
        String query = resourcer.getString("query.user.delete");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, userId);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateIsBlocked(int userId) {
        User user = findById(userId)
                .orElseThrow(RuntimeException::new);
        String query = resourcer.getString("query.user.update.is.blocked");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setBoolean(1, !user.getBlocked());
            st.setInt(2, userId);

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static UserDAOImpl getInstance() {
        synchronized (UserDAOImpl.class) {
            if (instance == null) {
                instance = new UserDAOImpl();
            }
        }

        return instance;
    }
}
