package ru.rsreu.exchangeofthings.handler;

import javax.servlet.ServletException;
import java.io.IOException;

public class ExceptionHandler extends Router {
    public void handleException(Exception exception) throws ServletException, IOException {
        throw new RuntimeException(exception);
    }
}
