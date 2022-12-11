package ru.rsreu.exchangeofthings.handler;

import ru.rsreu.exchangeofthings.enums.Jsp;
import ru.rsreu.exchangeofthings.enums.Route;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Router {
    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public void init(
            ServletContext servletContext,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) {
        this.context = servletContext;
        this.request = servletRequest;
        this.response = servletResponse;
    }

    protected void forward(Jsp page) throws ServletException, IOException {
        RequestDispatcher dispatcher = context.getRequestDispatcher(page.getRoute());

        dispatcher.forward(request, response);
    }

    protected void redirect(Route route) throws IOException {
        response.sendRedirect(route.getPath());
    }
}
