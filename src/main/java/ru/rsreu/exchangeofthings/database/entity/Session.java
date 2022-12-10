package ru.rsreu.exchangeofthings.database.entity;

import java.util.Date;

public class Session {
    private Integer id;
    private Date expiredAt;
    private User user;

    public Session(Date expiredAt, User user) {
        this.expiredAt = expiredAt;
        this.user = user;
    }

    public Session(Integer id, Date expiredAt, User user) {
        this.id = id;
        this.expiredAt = expiredAt;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
