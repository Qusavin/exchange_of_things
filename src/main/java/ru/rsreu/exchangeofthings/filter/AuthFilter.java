package ru.rsreu.exchangeofthings.filter;

import ru.rsreu.exchangeofthings.database.entity.User;
import ru.rsreu.exchangeofthings.enums.Route;
import ru.rsreu.exchangeofthings.util.UserUtil;
import ru.rsreu.exchangeofthings.wrapper.UserRoleRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthFilter implements Filter {
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
        Optional<User> user = UserUtil.getFromSession(request.getSession());

        if (!user.isPresent()) {
            String path = request.getPathInfo().substring(1);

            if (path.contains(Route.LOGIN.getPath())) {
                filterChain.doFilter(request, response);
                return;
            }

            response.sendRedirect(Route.LOGIN.getPath());
            return;
        }

        HttpServletRequest wrapRequest = new UserRoleRequestWrapper(request, user.get());

        filterChain.doFilter(wrapRequest, response);
    }
}
