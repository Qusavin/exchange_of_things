package ru.rsreu.exchangeofthings.database.entity;

public class Item {
    private Integer id;
    private String title;
    private String description;
    private String image;
    private Integer viewsNumber;
    private String category;
    private Boolean isAvailable;
    private User owner;

    public Item(String title, String description, String image, Integer viewsNumber, String category, Boolean isAvailable, User owner) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.viewsNumber = viewsNumber;
        this.category = category;
        this.isAvailable = isAvailable;
        this.owner = owner;
    }

    public Item(Integer id, String title, String description, String image, Integer viewsNumber, String category, Boolean isAvailable, User owner) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.viewsNumber = viewsNumber;
        this.category = category;
        this.isAvailable = isAvailable;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getViewsNumber() {
        return viewsNumber;
    }

    public void setViewsNumber(Integer viewsNumber) {
        this.viewsNumber = viewsNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
