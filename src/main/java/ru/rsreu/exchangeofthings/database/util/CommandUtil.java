package ru.rsreu.exchangeofthings.database.util;

import ru.rsreu.exchangeofthings.command.FrontCommand;
import ru.rsreu.exchangeofthings.config.CommandConfig;

import javax.servlet.http.HttpServletRequest;

public class CommandUtil {
    private CommandUtil() {
    }

    public static FrontCommand getCommand(HttpServletRequest request) {
        String url = request.getPathInfo();

        return CommandConfig.getCommand(url);
    }
}
