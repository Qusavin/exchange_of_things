package ru.rsreu.exchangeofthings.util;

import ru.rsreu.exchangeofthings.config.AuthConfig;
import ru.rsreu.exchangeofthings.database.entity.User;
import ru.rsreu.exchangeofthings.enums.Role;
import ru.rsreu.exchangeofthings.enums.Route;

import java.util.List;

public class PermissionUtil {
    public static boolean hasPermission(String path, User user) {
        List<Route> routes = AuthConfig.getRoutes(Role.valueOf(user.getRole().toUpperCase()));

        for (Route route : routes) {
            if (path.contains(route.getPath())) {
                return true;
            }
        }

        return false;
    }
}