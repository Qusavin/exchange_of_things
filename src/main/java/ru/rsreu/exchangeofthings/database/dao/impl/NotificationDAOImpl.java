package ru.rsreu.exchangeofthings.database.dao.impl;

import ru.rsreu.exchangeofthings.database.dao.AbstractDAO;
import ru.rsreu.exchangeofthings.database.dao.NotificationDAO;
import ru.rsreu.exchangeofthings.persistent.entity.Notification;
import ru.rsreu.exchangeofthings.support.mapper.DAOMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAOImpl extends AbstractDAO implements NotificationDAO {
    private static volatile NotificationDAOImpl instance;

    @Override
    public void save(Notification notification) {
        String query = resourcer.getString("notification.query.save");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, notification.getReceiverId());
            st.setInt(2, notification.getSenderId());
            st.setString(3, notification.getMessage());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        String query = resourcer.getString("notification.query.delete.by.id");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Notification> findByReceiverAndSenderId(int userId) {
        String query = resourcer.getString("notification.query.find.by.user.id");
        List<Notification> notifications = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, userId);
            st.setInt(2, userId);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                notifications.add(DAOMapper.mapToNotification(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notifications;
    }

    public static NotificationDAOImpl getInstance() {
        synchronized (NotificationDAOImpl.class) {
            if (instance == null) {
                instance = new NotificationDAOImpl();
            }
        }

        return instance;
    }
}
