package ru.rsreu.exchangeofthings.persistent.entity;

/**
 * User with session entity.
 */
public class UserWithSession {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Boolean isBlocked;
    private Boolean isOnline;
    private String role;

    public UserWithSession(
            Integer id,
            String username,
            String password,
            String name,
            Boolean isBlocked,
            Boolean isOnline,
            String role
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.isBlocked = isBlocked;
        this.isOnline = isOnline;
        this.role = role;
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

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }
}
