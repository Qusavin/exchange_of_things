package ru.rsreu.exchangeofthings.dao.impl;

import ru.rsreu.exchangeofthings.dao.AbstractDAO;
import ru.rsreu.exchangeofthings.dao.ItemDAO;
import ru.rsreu.exchangeofthings.database.entity.Item;
import ru.rsreu.exchangeofthings.mapper.DAOMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemDAOImpl extends AbstractDAO implements ItemDAO {
    private static volatile ItemDAOImpl instance;

    @Override
    public Optional<Item> save(Item item) {
        String query = resourcer.getString("query.item.save");
        String[] returnId = {"id"};

        try (PreparedStatement st = connection.prepareStatement(query, returnId)) {
            st.setString(1, item.getTitle());
            st.setString(2, item.getDescription());
            st.setString(3, item.getImage());
            st.setString(4, item.getCategory());
            st.setInt(5, item.getOwner().getId());

            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                return Optional.empty();
            }

            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);

                    item.setId(id);

                    return Optional.of(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Item> findById(int id) {
        String query = resourcer.getString("query.item.find.by.id");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, id);

            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                return Optional.of(DAOMapper.mapItemWithUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Item> findByOwnerId(int ownerId) {
        String query = resourcer.getString("query.item.find.by.owner.id");
        List<Item> items = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, ownerId);

            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                items.add(DAOMapper.mapItem(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public List<Item> findAvailableItems() {
        String query = resourcer.getString("query.item.find.by.is.available");
        List<Item> items = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(query)) {
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                items.add(DAOMapper.mapItemWithUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public void deleteById(int id) {
        String query = resourcer.getString("query.item.delete.by.id");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOwner(int itemId, int ownerId) {
        String query = resourcer.getString("query.item.update.owner");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, ownerId);
            st.setInt(2, itemId);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateIsAvailable(int id) {
        Item item = findById(id)
                .orElseThrow(() -> new RuntimeException());
        String query = resourcer.getString("query.item.update.is.available");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setBoolean(1, !item.getAvailable());
            st.setInt(2, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ItemDAOImpl getInstance() {
        synchronized (ItemDAOImpl.class) {
            if (instance == null) {
                instance = new ItemDAOImpl();
            }
        }

        return instance;
    }
}
