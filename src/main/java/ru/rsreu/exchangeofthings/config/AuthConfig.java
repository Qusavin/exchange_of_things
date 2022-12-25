package ru.rsreu.exchangeofthings.config;

import ru.rsreu.exchangeofthings.persistent.enums.Role;
import ru.rsreu.exchangeofthings.persistent.enums.Route;

import java.util.*;

/**
 * Config of routes for specific role.
 */
public class AuthConfig {
    private static final Map<Role, List<Route>> roleRoutes = Map.ofEntries(
            Map.entry(Role.USER, List.of(
                    Route.NOTIFICATION,
                    Route.USER_PANEL_THING,
                    Route.USER_PANEL_EXCHANGE,
                    Route.USER_PANEL,
                    Route.LOGOUT
            )),
            Map.entry(Role.ADMIN, List.of(Route.ADMIN_PANEL, Route.LOGOUT)),
            Map.entry(Role.MODERATOR, List.of(Route.MODERATOR_PANEL, Route.LOGOUT))
    );

    private static final Map<Role, Route> roleStartPage = Map.ofEntries(
            Map.entry(Role.USER, Route.USER_PANEL),
            Map.entry(Role.MODERATOR, Route.MODERATOR_PANEL),
            Map.entry(Role.ADMIN, Route.ADMIN_PANEL)
    );

    /**
     * Get role's routes.
     *
     * @param role Role
     * @return list of role's routes
     */
    public static List<Route> getRoutes(Role role) {
        return roleRoutes.get(role);
    }

    /**
     * Get role start route.
     *
     * @param role Role
     * @return role start route
     */
    public static Route getStartPage(Role role) {
        return roleStartPage.get(role);
    }
}
