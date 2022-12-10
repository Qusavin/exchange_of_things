package ru.rsreu.exchangeofthings.enums;

import ru.rsreu.exchangeofthings.command.*;

public enum Command {
    LOGIN {
        @Override
        public FrontCommand getCommand() {
            return new LoginCommand();
        }
    },
    MODERATOR_PANEL {
        @Override
        public FrontCommand getCommand() {
            return new ModeratorPanelCommand();
        }
    },
    USER_PANEL {
        @Override
        public FrontCommand getCommand() {
            return new UserPanelCommand();
        }
    },
    ADMIN_PANEL {
        @Override
        public FrontCommand getCommand() {
            return new AdminPanelCommand();
        }
    };

    public abstract FrontCommand getCommand();
}
