package ru.rsreu.exchangeofthings.enums;

public enum Route {
    LOGIN("login"),
    ADMIN_PANEL("admin-panel"),
    USER_PANEL_THING("user-panel/thing"),
    USER_PANEL_EXCHANGE("user-panel/exchange"),
    USER_PANEL("user-panel"),
    MODERATOR_PANEL("moderator-panel"),
    NOT_FOUND("not-found");

    private final String route;

    Route(String route) {
        this.route = route;
    }

    public String getPath() {
        return this.route;
    }
}
