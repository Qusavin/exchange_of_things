package ru.rsreu.exchangeofthings.persistent.enums;

/**
 * User panel exchange modes.
 */
public enum UserPanelExchangeMode {
    MAKE_REQUEST("make_request"),
    COMPLETE_REQUEST("complete_request");

    private String value;

    UserPanelExchangeMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
