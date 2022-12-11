package ru.rsreu.exchangeofthings.dao.impl;

import ru.rsreu.exchangeofthings.dao.AbstractDAO;
import ru.rsreu.exchangeofthings.dao.ExchangeRequestDAO;
import ru.rsreu.exchangeofthings.database.entity.ExchangeRequest;
import ru.rsreu.exchangeofthings.mapper.DAOMapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExchangeRequestDAOImpl extends AbstractDAO implements ExchangeRequestDAO {
    private static volatile ExchangeRequestDAOImpl instance;

    @Override
    public List<ExchangeRequest> findByReceiverIdAndStatus(int receiverId, String status) {
        String query = resourcer.getString("query.exchange.find.by.receiver.id");
        List<ExchangeRequest> exchangeRequests = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, receiverId);
            st.setString(2, status);

            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                exchangeRequests.add(DAOMapper.mapExchangeRequest(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exchangeRequests;
    }

    @Override
    public void save(ExchangeRequest exchangeRequest) {
        String query = resourcer.getString("query.exchange.save");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, exchangeRequest.getReceiverItem().getId());
            st.setInt(2, exchangeRequest.getSenderItem().getId());
            st.setString(3, exchangeRequest.getStatus());
            st.setDate(4, new Date(System.currentTimeMillis()));

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        String query = resourcer.getString("query.exchange.delete.by.id");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStatus(int id, String status) {
        String query = resourcer.getString("query.exchange.update");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, status);
            st.setInt(2, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ExchangeRequestDAOImpl getInstance() {
        synchronized (ExchangeRequestDAOImpl.class) {
            if (instance == null) {
                instance = new ExchangeRequestDAOImpl();
            }
        }

        return instance;
    }
}
