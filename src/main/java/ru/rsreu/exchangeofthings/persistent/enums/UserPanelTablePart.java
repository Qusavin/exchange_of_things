package ru.rsreu.exchangeofthings.persistent.enums;

/**
 * User panel table parts.
 */
public enum UserPanelTablePart {
    MY_ITEMS("my_items"),
    EXCHANGE_ITEMS("exchange_items"),
    MY_EXCHANGE_ITEMS("my_exchange_items");

    private final String value;

    UserPanelTablePart(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
