package ru.rsreu.exchangeofthings.dto;

public class LoginResponseDTO {
    private String redirectUrl;

    public LoginResponseDTO(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
