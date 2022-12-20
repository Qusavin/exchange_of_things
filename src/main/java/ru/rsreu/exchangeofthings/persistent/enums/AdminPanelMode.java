package ru.rsreu.exchangeofthings.persistent.enums;

/**
 * Admin panel modes.
 */
public enum AdminPanelMode {
    ADD_USER("add_user"),
    EDIT_USER("edit_user"),
    DELETE_USER("delete_user");

    private final String value;

    AdminPanelMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
