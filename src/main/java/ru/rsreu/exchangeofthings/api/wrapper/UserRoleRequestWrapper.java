package ru.rsreu.exchangeofthings.api.wrapper;

import ru.rsreu.exchangeofthings.persistent.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.security.Principal;

/**
 * Wrapper around http servlet request which hold user entity.
 */
public class UserRoleRequestWrapper extends HttpServletRequestWrapper {
    private final User user;

    /**
     * @param request Http servlet request object
     * @param user User entity
     */
    public UserRoleRequestWrapper(HttpServletRequest request, User user) {
        super(request);

        this.user = user;
    }

    @Override
    public boolean isUserInRole(String roleAsString) {
        return user.getRole().equalsIgnoreCase(roleAsString);
    }

    @Override
    public Principal getUserPrincipal() {
        return this.user;
    }
}
