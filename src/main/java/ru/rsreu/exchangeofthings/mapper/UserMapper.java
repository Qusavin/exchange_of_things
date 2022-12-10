package ru.rsreu.exchangeofthings.mapper;

import ru.rsreu.exchangeofthings.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {
    private UserMapper() {
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
}
