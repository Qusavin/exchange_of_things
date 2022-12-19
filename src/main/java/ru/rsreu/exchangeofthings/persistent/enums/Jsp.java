package ru.rsreu.exchangeofthings.persistent.enums;

public enum Jsp {
    LOGIN("/pages/login.jsp"),

    ADMIN_PANEL("/pages/admin_panel.jsp"),
    ADMIN_PANEL_TABLE("/pages/admin-panel/table.jsp"),

    USER_PANEL("/pages/user_panel.jsp"),
    USER_PANEL_THING("/pages/user-panel/thing.jsp"),
    USER_PANEL_EXCHANGE("/pages/user-panel/exchange.jsp"),
    USER_PANEL_ITEMS("/pages/user-panel/my_things_table.jsp"),
    USER_PANEL_EXCHANGE_ITEMS("/pages/user-panel/exchange_things_table.jsp"),
    USER_PANEL_MY_EXCHANGE_ITEMS("/pages/user-panel/my_exchange_things_table.jsp"),

    MODERATOR_PANEL_ITEMS_TABLE("/pages/moderator-panel/things-table.jsp"),
    MODERATOR_PANEL_EXCHANGE_REQUESTS_TABLE("/pages/moderator-panel/exchange-requests-table.jsp"),
    MODERATOR_PANEL("/pages/moderator_panel.jsp"),

    NOT_FOUND("/pages/404.jsp");

    private final String route;

    Jsp(String route) {
        this.route = route;
    }

    public String getRoute() {
        return this.route;
    }
}
