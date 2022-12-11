package ru.rsreu.exchangeofthings.enums;

public enum Jsp {
    LOGIN("/pages/login.jsp"),

    ADMIN_PANEL("/pages/admin_panel.jsp"),

    USER_PANEL("/pages/user_panel.jsp"),
    USER_PANEL_THING("/pages/user-panel/thing.jsp"),
    USER_PANEL_EXCHANGE("/pages/user-panel/exchange.jsp"),
    USER_PANEL_ITEMS("/pages/user-panel/my_things_table.jsp"),
    USER_PANEL_EXCHANGE_ITEMS("/pages/user-panel/exchange_things_table.jsp"),
    USER_PANEL_MY_EXCHANGE_ITEMS("/pages/user-panel/my_exchange_things_table.jsp"),

    MODERATOR_PANEL("/pages/moderator_panel.jsp"),

    NOT_FOUND("/pages/not_found.jsp");

    private final String route;

    Jsp(String route) {
        this.route = route;
    }

    public String getRoute() {
        return this.route;
    }
}
