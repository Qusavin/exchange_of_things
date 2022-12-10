package ru.rsreu.exchangeofthings.util;

import ru.rsreu.exchangeofthings.command.FrontCommand;
import ru.rsreu.exchangeofthings.command.NotFoundCommand;
import ru.rsreu.exchangeofthings.enums.Command;

import javax.servlet.http.HttpServletRequest;

public class CommandUtil {
    private CommandUtil() {}

    public static FrontCommand getCommand(HttpServletRequest request) {
        try {
            String command = request.getPathInfo().substring(1).toUpperCase();

            return Command.valueOf(command).getCommand();
        } catch (Exception e) {
            return new NotFoundCommand();
        }
    }
}
