package ru.rsreu.exchangeofthings.persistent.entity;

/**
 * Notification entity
 */
public class Notification {
    private int id;
    private int userId;
    private String message;

    public Notification(int userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public Notification(int id, int userId, String message) {
        this.id = id;
        this.userId = userId;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

