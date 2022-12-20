package ru.rsreu.exchangeofthings.persistent.enums;

/**
 * User roles.
 */
public enum Role {
    ADMIN("Admin"),
    MODERATOR("Moderator"),
    USER("User");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
