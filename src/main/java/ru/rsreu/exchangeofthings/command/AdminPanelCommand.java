package ru.rsreu.exchangeofthings.command;

import ru.rsreu.exchangeofthings.data.UserWithSession;
import ru.rsreu.exchangeofthings.database.entity.User;
import ru.rsreu.exchangeofthings.enums.AdminPanelMode;
import ru.rsreu.exchangeofthings.enums.Jsp;
import ru.rsreu.exchangeofthings.enums.Role;
import ru.rsreu.exchangeofthings.service.ServiceFactory;
import ru.rsreu.exchangeofthings.service.SessionService;
import ru.rsreu.exchangeofthings.service.UserService;
import ru.rsreu.exchangeofthings.util.UserUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ru.rsreu.exchangeofthings.constant.RequestAttribute.USER;
import static ru.rsreu.exchangeofthings.constant.RequestAttribute.USERS;
import static ru.rsreu.exchangeofthings.constant.RequestParam.*;

public class AdminPanelCommand extends FrontCommand {
    private SessionService sessionService;
    private UserService userService;
    private User user;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        user = UserUtil.getFromRequest(request).orElseThrow(RuntimeException::new);
        sessionService = ServiceFactory.getSessionService();
        userService = ServiceFactory.getUserService();
    }

    @Override
    public void process() throws ServletException, IOException {
        List<UserWithSession> users = sessionService.findAll();

        request.setAttribute(USERS, users);
        request.setAttribute(USER, user);

        forward(Jsp.ADMIN_PANEL);
    }

    @Override
    public void send() {
        AdminPanelMode mode = AdminPanelMode.valueOf(
                request.getParameter(MODE).toUpperCase()
        );

        switch (mode) {
            case ADD_USER:
                addUser();
                break;
            case EDIT_USER:
                editUser();
                break;
            case DELETE_USER:
                deleteUser();
                break;
        }
    }

    private void addUser() {
        String name = request.getParameter(NAME);
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        Role role = Role.valueOf(request.getParameter(ROLE).toUpperCase());

        userService.add(name, username, password, role);
    }

    private void editUser() {
        int id = Integer.parseInt(request.getParameter(ID));
        String name = request.getParameter(NAME);
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        boolean isBlocked = Boolean.parseBoolean(request.getParameter(IS_BLOCKED));
        Role role = Role.valueOf(request.getParameter(ROLE).toUpperCase());

        userService.update(
                id,
                name,
                username,
                password,
                isBlocked,
                role
        );
    }

    private void deleteUser() {
        int id = Integer.parseInt(request.getParameter(ID));

        userService.deleteById(id);
    }
}
