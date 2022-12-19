package ru.rsreu.exchangeofthings.support.util;

import ru.rsreu.exchangeofthings.persistent.entity.Session;

import java.util.Date;

public class SessionUtil {
    private SessionUtil() {
    }

    public static boolean checkValid(Session session) {
        Date currentDate = new Date();
        Date sessionExpiredDate = session.getExpiredAt();

        return sessionExpiredDate.after(currentDate);
    }
}
