package ru.rsreu.exchangeofthings.support.util;

import ru.rsreu.exchangeofthings.config.AuthConfig;
import ru.rsreu.exchangeofthings.persistent.entity.User;
import ru.rsreu.exchangeofthings.persistent.enums.Role;
import ru.rsreu.exchangeofthings.persistent.enums.Route;

import java.util.List;

public class PermissionUtil {
    public static boolean hasPermission(String path, User user) {
        List<Route> routes = AuthConfig.getRoutes(Role.valueOf(user.getRole().toUpperCase()));

        for (Route route : routes) {
            if (path.contains(route.getRelative())) {
                return true;
            }
        }

        return false;
    }
}