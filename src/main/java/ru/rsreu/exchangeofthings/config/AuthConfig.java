package ru.rsreu.exchangeofthings.config;

import ru.rsreu.exchangeofthings.enums.Role;
import ru.rsreu.exchangeofthings.enums.Route;

import java.util.*;

public class AuthConfig {
    private static final Map<Role, List<Route>> roleRoutes = Map.ofEntries(
        Map.entry(Role.USER, List.of(
            Route.USER_PANEL_THING,
            Route.USER_PANEL_EXCHANGE,
            Route.USER_PANEL
        )),
        Map.entry(Role.ADMIN, List.of(Route.ADMIN_PANEL)),
        Map.entry(Role.MODERATOR, List.of(Route.MODERATOR_PANEL))
    );

    private static final Map<Role, Route> roleStartPage = Map.ofEntries(
        Map.entry(Role.USER, Route.USER_PANEL),
        Map.entry(Role.MODERATOR, Route.MODERATOR_PANEL),
        Map.entry(Role.ADMIN, Route.ADMIN_PANEL)
    );

    public static List<Route> getRoutes(Role role) {
        return roleRoutes.get(role);
    }

    public static Route getStartPage(Role role) {
        return roleStartPage.get(role);
    }
}
