package ru.rsreu.exchangeofthings.enums;

import ru.rsreu.exchangeofthings.command.FrontCommand;
import ru.rsreu.exchangeofthings.command.LoginCommand;

public enum Command {
    LOGIN {
        @Override
        public FrontCommand getCommand() {
            return new LoginCommand();
        }
    };

    public abstract FrontCommand getCommand();
}
