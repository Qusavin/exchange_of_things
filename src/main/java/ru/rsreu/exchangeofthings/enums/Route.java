package ru.rsreu.exchangeofthings.enums;

public enum Route {
    LOGIN("login"),
    NOT_FOUND("not_found");

    private final String route;

    Route(String route) {
        this.route = route;
    }

    public String getRoute() {
        return this.route;
    }
}
