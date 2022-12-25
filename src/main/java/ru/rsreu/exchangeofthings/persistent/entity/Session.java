package ru.rsreu.exchangeofthings.persistent.entity;

import java.util.Date;

/**
 * Session entity.
 */
public class Session {
    private Integer id;
    private Date expiredAt;
    private User user;

    /**
     * Public constructor
     * @param expiredAt
     * @param user
     */
    public Session(Date expiredAt, User user) {
        this.expiredAt = expiredAt;
        this.user = user;
    }

    /**
     * Public constructor
     * @param id
     * @param expiredAt
     * @param user
     */
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
