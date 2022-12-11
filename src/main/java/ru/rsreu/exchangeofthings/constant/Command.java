package ru.rsreu.exchangeofthings.constant;

import ru.rsreu.exchangeofthings.command.*;

import java.util.*;

public class Command {
    private static final String ADMIN_PANEL_URL = "/admin-panel";
    private static final String LOGIN_URL = "/login";
    private static final String MODERATOR_PANEL_URL = "/moderator-panel";
    private static final String USER_PANEL_THING = "/user-panel/thing";
    private static final String USER_PANEL_EXCHANGE = "/user-panel/exchange";
    private static final String USER_PANEL_URL = "/user-panel";

    private static final Map<String, FrontCommand> commands = Map.ofEntries(
            Map.entry(ADMIN_PANEL_URL, new AdminPanelCommand()),
            Map.entry(LOGIN_URL, new LoginCommand()),
            Map.entry(MODERATOR_PANEL_URL, new ModeratorPanelCommand()),
            Map.entry(USER_PANEL_URL, new UserPanelCommand()),
            Map.entry(USER_PANEL_THING, new UserPanelThingCommand()),
            Map.entry(USER_PANEL_EXCHANGE, new UserPanelExchangeCommand())
    );
    private static final List<String> commandUrls = new ArrayList<>(
            Arrays.asList(
                    ADMIN_PANEL_URL,

                    LOGIN_URL,

                    MODERATOR_PANEL_URL,

                    USER_PANEL_EXCHANGE,
                    USER_PANEL_THING,
                    USER_PANEL_URL
            )
    );

    public static FrontCommand getCommand(String url) {
        for (String commandUrl : commandUrls) {
            if (url.contains(commandUrl)) {
                return commands.get(commandUrl);
            }
        }

        return new NotFoundCommand();
    }
}
