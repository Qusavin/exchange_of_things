package ru.rsreu.exchangeofthings.persistent.enums;

/**
 * Exchange request statuses.
 */
public enum Status {
    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    BLOCKED("Blocked"),
    IN_PROCESS("In process");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
