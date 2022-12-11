package ru.rsreu.exchangeofthings.util;

import ru.rsreu.exchangeofthings.command.FrontCommand;
import ru.rsreu.exchangeofthings.constant.Command;

import javax.servlet.http.HttpServletRequest;

public class CommandUtil {
    private CommandUtil() {
    }

    public static FrontCommand getCommand(HttpServletRequest request) {
        String url = request.getPathInfo();

        return Command.getCommand(url);
    }
}
