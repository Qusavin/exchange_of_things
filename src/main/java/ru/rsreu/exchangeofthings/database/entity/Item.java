package ru.rsreu.exchangeofthings.database.entity;

public class Item {
    private Integer id;
    private String title;
    private String description;
    private String image;
    private String viewsNumber;
    private String category;
    private User owner;

    public Item(String title, String description, String image, String viewsNumber, String category, User owner) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.viewsNumber = viewsNumber;
        this.category = category;
        this.owner = owner;
    }

    public Item(Integer id, String title, String description, String image, String viewsNumber, String category, User owner) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.viewsNumber = viewsNumber;
        this.category = category;
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

    public String getViewsNumber() {
        return viewsNumber;
    }

    public void setViewsNumber(String viewsNumber) {
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
}
