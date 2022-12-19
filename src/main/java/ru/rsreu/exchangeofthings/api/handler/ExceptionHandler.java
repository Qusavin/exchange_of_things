package ru.rsreu.exchangeofthings.api.handler;

import ru.rsreu.exchangeofthings.persistent.dto.ErrorDTO;
import ru.rsreu.exchangeofthings.persistent.exception.AuthException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandler extends Router {
    public void handleException(Exception exception) throws ServletException, IOException {
        if (exception instanceof AuthException) {
            send(new ErrorDTO("Invalid username or password"), HttpServletResponse.SC_BAD_REQUEST);

            return;
        }

        throw new RuntimeException(exception);
    }
}
