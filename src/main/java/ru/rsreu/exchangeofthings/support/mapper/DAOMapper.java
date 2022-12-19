package ru.rsreu.exchangeofthings.support.mapper;

import ru.rsreu.exchangeofthings.persistent.request.ExchangeRequest;
import ru.rsreu.exchangeofthings.persistent.entity.Item;
import ru.rsreu.exchangeofthings.persistent.entity.Session;
import ru.rsreu.exchangeofthings.persistent.entity.User;

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
                resultSet.getInt("e_id"),
                new Item(
                        resultSet.getInt("rec_i_id"),
                        resultSet.getString("rec_i_title"),
                        resultSet.getString("rec_i_description"),
                        resultSet.getString("rec_i_image"),
                        resultSet.getInt("rec_i_views_number"),
                        resultSet.getString("rec_i_category"),
                        resultSet.getBoolean("rec_i_is_available"),
                        new User(
                                resultSet.getInt("rec_u_id"),
                                resultSet.getString("rec_u_username"),
                                resultSet.getString("rec_u_name"),
                                resultSet.getString("rec_u_role")
                        )
                ),
                new Item(
                        resultSet.getInt("sen_i_id"),
                        resultSet.getString("sen_i_title"),
                        resultSet.getString("sen_i_description"),
                        resultSet.getString("sen_i_image"),
                        resultSet.getInt("sen_i_views_number"),
                        resultSet.getString("sen_i_category"),
                        resultSet.getBoolean("sen_i_is_available"),
                        new User(
                                resultSet.getInt("sen_u_id"),
                                resultSet.getString("sen_u_username"),
                                resultSet.getString("sen_u_name"),
                                resultSet.getString("sen_u_role")
                        )
                ),
                resultSet.getString("e_status"),
                resultSet.getDate("e_exchange_date")
        );
    }
}
