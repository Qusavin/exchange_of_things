package ru.rsreu.exchangeofthings.api.command;

import ru.rsreu.exchangeofthings.persistent.entity.User;
import ru.rsreu.exchangeofthings.persistent.enums.Route;
import ru.rsreu.exchangeofthings.service.ServiceFactory;
import ru.rsreu.exchangeofthings.service.SessionService;
import ru.rsreu.exchangeofthings.support.util.UserUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand extends FrontCommand {
    private SessionService sessionService;
    private User user;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        user = UserUtil.getFromRequest(request).orElseThrow(RuntimeException::new);
        sessionService = ServiceFactory.getSessionService();
    }

    @Override
    public void process() throws ServletException, IOException {
        sessionService.deleteSession(user);
        response.sendRedirect(Route.LOGIN.getAbsolute());
    }
}
