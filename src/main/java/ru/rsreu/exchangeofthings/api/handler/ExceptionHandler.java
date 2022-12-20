package ru.rsreu.exchangeofthings.api.handler;

import ru.rsreu.exchangeofthings.persistent.dto.ErrorDTO;
import ru.rsreu.exchangeofthings.persistent.exception.AuthException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandler extends Router {
    /**
     * Handle exceptions got from controller
     *
     * @param exception Exception
     * @throws ServletException
     * @throws IOException
     */
    public void handleException(Exception exception) throws ServletException, IOException {
        if (exception instanceof AuthException) {
            json(new ErrorDTO("Invalid username or password"), HttpServletResponse.SC_BAD_REQUEST);

            return;
        }

        throw new RuntimeException(exception);
    }
}
