package ru.rsreu.exchangeofthings.filter;

import ru.rsreu.exchangeofthings.config.AuthConfig;
import ru.rsreu.exchangeofthings.database.entity.User;
import ru.rsreu.exchangeofthings.enums.Role;
import ru.rsreu.exchangeofthings.enums.Route;
import ru.rsreu.exchangeofthings.util.PermissionUtil;
import ru.rsreu.exchangeofthings.util.UserUtil;

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
        String path = request.getPathInfo().substring(1);

        Optional<User> user = UserUtil.getFromRequest(request);

        if (!user.isPresent()) {
            if (path.contains(Route.LOGIN.getPath())) {
                filterChain.doFilter(request, response);
                return;
            }

            response.sendRedirect(Route.LOGIN.getPath());
            return;
        }

        Role role = Role.valueOf(user.get().getRole().toUpperCase());

        if (PermissionUtil.hasPermission(path, user.get())) {
            filterChain.doFilter(request, response);
            return;
        }

        String redirectPath = AuthConfig.getStartPage(role).getPath();

        response.sendRedirect(redirectPath);
    }
}
