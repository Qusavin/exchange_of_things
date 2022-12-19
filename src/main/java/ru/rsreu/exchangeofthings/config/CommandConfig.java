package ru.rsreu.exchangeofthings.config;

import ru.rsreu.exchangeofthings.api.command.*;
import ru.rsreu.exchangeofthings.persistent.enums.Route;

import java.util.*;

public class CommandConfig {
    private static final Map<Route, FrontCommand> commands = Map.ofEntries(
            Map.entry(Route.ADMIN_PANEL, new AdminPanelCommand()),
            Map.entry(Route.LOGIN, new LoginCommand()),
            Map.entry(Route.LOGOUT, new LogoutCommand()),
            Map.entry(Route.MODERATOR_PANEL, new ModeratorPanelCommand()),
            Map.entry(Route.USER_PANEL, new UserPanelCommand()),
            Map.entry(Route.USER_PANEL_THING, new UserPanelThingCommand()),
            Map.entry(Route.USER_PANEL_EXCHANGE, new UserPanelExchangeCommand()),
            Map.entry(Route.NOT_FOUND, new NotFoundCommand())
    );

    private static final List<Route> commandRoutes = Arrays.asList(
            Route.ADMIN_PANEL,

            Route.LOGIN,
            Route.LOGOUT,

            Route.MODERATOR_PANEL,

            Route.USER_PANEL_EXCHANGE,
            Route.USER_PANEL_THING,
            Route.USER_PANEL
    );

    public static FrontCommand getCommand(String path) {
        for (Route route : commandRoutes) {
            if (route.getRelative().equalsIgnoreCase(path)) {
                return commands.get(route);
            }
        }

        return new NotFoundCommand();
    }
}
