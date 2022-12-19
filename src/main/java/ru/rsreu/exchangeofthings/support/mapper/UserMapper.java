package ru.rsreu.exchangeofthings.support.mapper;

import ru.rsreu.exchangeofthings.persistent.response.UserWithSession;
import ru.rsreu.exchangeofthings.persistent.entity.Session;
import ru.rsreu.exchangeofthings.persistent.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    private UserMapper() {
    }

    public static UserWithSession mapSessionToUserWithSession(Session session) {
        User user = session.getUser();

        return new UserWithSession(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBlocked(),
                user.isOnline(session.getExpiredAt()),
                user.getRole()
        );
    }

    public static List<UserWithSession> mapSessionToUserWithSessionList(List<Session> sessions) {
        return sessions.stream()
                .map(UserMapper::mapSessionToUserWithSession)
                .collect(Collectors.toList());
    }
}
