package ru.rsreu.exchangeofthings.mapper;

import ru.rsreu.exchangeofthings.database.entity.ExchangeRequest;
import ru.rsreu.exchangeofthings.database.entity.Item;
import ru.rsreu.exchangeofthings.database.entity.Session;
import ru.rsreu.exchangeofthings.database.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOMapper {
    private DAOMapper() {
    }

    public static User mapUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt("id"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("name"),
                resultSet.getBoolean("is_blocked"),
                resultSet.getString("role")
        );

    }

    public static Session mapSession(ResultSet resultSet) throws SQLException {
        return new Session(
                resultSet.getTimestamp("expired_at"),
                mapUser(resultSet));
    }

    public static User mapUserForItem(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt("owner_id"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("name"),
                resultSet.getBoolean("is_blocked"),
                resultSet.getString("role")
        );

    }

    public static Item mapItemWithUser(ResultSet resultSet) throws SQLException {
        return new Item(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getString("image"),
                resultSet.getInt("views_number"),
                resultSet.getString("category"),
                resultSet.getBoolean("is_available"),
                mapUserForItem(resultSet)
        );
    }

    public static Item mapItem(ResultSet resultSet) throws SQLException {
        return new Item(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getString("image"),
                resultSet.getInt("views_number"),
                resultSet.getString("category"),
                resultSet.getBoolean("is_available"),
                null
        );
    }

    public static ExchangeRequest mapExchangeRequest(ResultSet resultSet) throws SQLException {
        return new ExchangeRequest(
                resultSet.getInt("id"),
                new Item(
                        resultSet.getInt("id_1"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("image"),
                        resultSet.getInt("views_number"),
                        resultSet.getString("category"),
                        resultSet.getBoolean("is_available"),
                        new User(
                                resultSet.getInt("id_3"),
                                resultSet.getString("username"),
                                resultSet.getString("password"),
                                resultSet.getString("name"),
                                resultSet.getBoolean("is_blocked"),
                                resultSet.getString("role")
                        )
                ),
                new Item(
                        resultSet.getInt("id_2"),
                        resultSet.getString("title_1"),
                        resultSet.getString("description_1"),
                        resultSet.getString("image_1"),
                        resultSet.getInt("views_number_1"),
                        resultSet.getString("category_1"),
                        resultSet.getBoolean("is_available_1"),
                        new User(
                                resultSet.getInt("id_4"),
                                resultSet.getString("username_1"),
                                resultSet.getString("password_1"),
                                resultSet.getString("name_1"),
                                resultSet.getBoolean("is_blocked_1"),
                                resultSet.getString("role_1")
                        )
                ),
                resultSet.getString("status"),
                resultSet.getDate("exchange_date")
        );
    }
}
