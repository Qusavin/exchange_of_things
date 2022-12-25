package ru.rsreu.exchangeofthings.persistent.entity;

import java.security.Principal;
import java.util.Date;

/**
 * User entity.
 */
public class User implements Principal {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Boolean isBlocked;
    private String role;

    /**
     * Public constructor
     * @param username
     * @param password
     * @param name
     * @param isBlocked
     * @param role
     */
    public User(String username, String password, String name, Boolean isBlocked, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.isBlocked = isBlocked;
        this.role = role;
    }

    /**
     * Public constructor
     * @param username
     * @param password
     * @param name
     * @param role
     */
    public User(String username, String password, String name, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    /**
     * Public constructor
     * @param id
     * @param username
     * @param password
     * @param name
     * @param isBlocked
     * @param role
     */
    public User(Integer id, String username, String password, String name, Boolean isBlocked, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.isBlocked = isBlocked;
        this.role = role;
    }

    /**
     * Public constructor
     * @param id
     * @param username
     * @param name
     * @param role
     */
    public User(Integer id, String username, String name, String role) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.role = role;
    }

    public boolean isOnline(Date expiredAt) {
        return expiredAt != null && expiredAt.after(new Date(System.currentTimeMillis()));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", isBlocked=" + isBlocked +
                ", role='" + role + '\'' +
                '}';
    }
}
