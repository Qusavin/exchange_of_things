package ru.rsreu.exchangeofthings.persistent.enums;

public enum Route {
    LOGIN("/login"),
    LOGOUT("/logout"),
    ADMIN_PANEL("/admin-panel"),
    USER_PANEL_THING("/user-panel/thing"),
    USER_PANEL_EXCHANGE("/user-panel/exchange"),
    USER_PANEL("/user-panel"),
    MODERATOR_PANEL("/moderator-panel"),
    NOT_FOUND("/404");

    private final String route;

    Route(String route) {
        this.route = route;
    }

    public String getRelative() {
        return this.route;
    }

    public String getAbsolute() {
        return this.route.substring(1);
    }
}
