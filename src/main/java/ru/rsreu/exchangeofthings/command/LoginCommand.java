package ru.rsreu.exchangeofthings.command;

import ru.rsreu.exchangeofthings.config.AuthConfig;
import ru.rsreu.exchangeofthings.constant.RequestAttribute;
import ru.rsreu.exchangeofthings.database.entity.User;
import ru.rsreu.exchangeofthings.dto.LoginResponseDTO;
import ru.rsreu.exchangeofthings.enums.Jsp;
import ru.rsreu.exchangeofthings.enums.Role;
import ru.rsreu.exchangeofthings.enums.Route;
import ru.rsreu.exchangeofthings.service.ServiceFactory;
import ru.rsreu.exchangeofthings.service.SessionService;
import ru.rsreu.exchangeofthings.service.UserService;
import ru.rsreu.exchangeofthings.util.UserUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.rsreu.exchangeofthings.constant.RequestParam.PASSWORD;
import static ru.rsreu.exchangeofthings.constant.RequestParam.USERNAME;

public class LoginCommand extends FrontCommand {
    public SessionService sessionService;
    public UserService userService;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        sessionService = ServiceFactory.getSessionService();
        userService = ServiceFactory.getUserService();
    }

    @Override
    public void process() throws ServletException, IOException {
        forward(Jsp.LOGIN);
    }

    @Override
    public void send() throws IOException {
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);

        User user = sessionService.createSession(username, password);
        Role role = Role.valueOf(user.getRole().toUpperCase());
        Route startRoute = AuthConfig.getStartPage(role);

        UserUtil.setToSession(request.getSession(), user);

        json(new LoginResponseDTO(startRoute.getPath()));
    }
}
