package ru.rsreu.exchangeofthings.enums;

public enum Route {
    LOGIN("login"),
    ADMIN_PANEL("admin_panel"),
    USER_PANEL("user_panel"),
    MODERATOR_PANEL("moderator_panel"),
    NOT_FOUND("not_found");

    private final String route;

    Route(String route) {
        this.route = route;
    }

    public String getRoute() {
        return this.route;
    }
}
