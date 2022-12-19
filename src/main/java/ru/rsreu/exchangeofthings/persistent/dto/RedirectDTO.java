package ru.rsreu.exchangeofthings.persistent.dto;

public class RedirectDTO {
    private String url;

    public RedirectDTO(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
