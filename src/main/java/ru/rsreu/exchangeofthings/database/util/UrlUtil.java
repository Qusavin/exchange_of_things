package ru.rsreu.exchangeofthings.database.util;

import static ru.rsreu.exchangeofthings.constant.GlobalOptions.URL_PREFIX;

public class UrlUtil {
    private static final String PAGE_URL_FORMAT = "/pages/%s.jsp";
    private static final String URL_WITH_PREFIX_FORMAT = "/%s/%s";

    public static String getPageUrl(String page) {
        return String.format(PAGE_URL_FORMAT, page);
    }

    public static String getUrlWithPrefix(String target) {
        return String.format(URL_WITH_PREFIX_FORMAT, URL_PREFIX, target);
    }
}
