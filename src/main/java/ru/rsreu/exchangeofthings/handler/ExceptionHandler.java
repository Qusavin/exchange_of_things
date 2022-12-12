package ru.rsreu.exchangeofthings.handler;

import ru.rsreu.exchangeofthings.dto.ErrorDTO;
import ru.rsreu.exchangeofthings.exception.AuthException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandler extends Router {
    public void handleException(Exception exception) throws ServletException, IOException {
        if (exception instanceof AuthException) {
            json(new ErrorDTO("Invalid username or password"), HttpServletResponse.SC_BAD_REQUEST);

            return;
        }

        throw new RuntimeException(exception);
    }
}
