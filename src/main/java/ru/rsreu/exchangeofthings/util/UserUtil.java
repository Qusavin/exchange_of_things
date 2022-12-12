package ru.rsreu.exchangeofthings.util;

import ru.rsreu.exchangeofthings.database.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class UserUtil {
    private static final String USER = "user";

    private UserUtil() {
    }

    public static Optional<User> getFromRequest(HttpServletRequest request) {
        User user = (User)request.getUserPrincipal();

        return Optional.ofNullable(user);
    }

    public static Optional<User> getFromSession(HttpSession session) {
        User user = (User)session.getAttribute(USER);

        return Optional.ofNullable(user);
    }

    public static void setToSession(HttpSession session, User user) {
        session.setAttribute(USER, user);
    }
}
