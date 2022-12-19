package ru.rsreu.exchangeofthings.filter;

import ru.rsreu.exchangeofthings.database.entity.User;
import ru.rsreu.exchangeofthings.enums.Route;
import ru.rsreu.exchangeofthings.database.util.PermissionUtil;
import ru.rsreu.exchangeofthings.database.util.UserUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class PermissionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getPathInfo();

        if (path.equals(Route.NOT_FOUND.getRelative())) {
            filterChain.doFilter(request, response);
            return;
        }

        Optional<User> user = UserUtil.getFromRequest(request);

        if (!user.isPresent()) {
            if (path.equals(Route.LOGIN.getRelative())) {
                filterChain.doFilter(request, response);
                return;
            }

            response.sendRedirect(Route.LOGIN.getAbsolute());
            return;
        }

        if (PermissionUtil.hasPermission(path, user.get())) {
            filterChain.doFilter(request, response);
            return;
        }

        response.sendRedirect(Route.NOT_FOUND.getAbsolute());
    }
}
