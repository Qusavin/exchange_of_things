package ru.rsreu.exchangeofthings.support.util;

public class MessageUtil {
    private static final String message = "%s for %s exchange was canceled";

    public static String getDeclineMessage(String secItemName, String recItemName) {
        return String.format(message, secItemName, recItemName);
    }
}
