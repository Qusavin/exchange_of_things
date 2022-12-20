package ru.rsreu.exchangeofthings.api.handler;

import com.google.gson.Gson;
import ru.rsreu.exchangeofthings.constant.ContentType;
import ru.rsreu.exchangeofthings.persistent.enums.Jsp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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

    /**
     * Forward to specific jsp.
     *
     * @param page Path to jsp
     * @throws ServletException
     * @throws IOException
     */
    protected void forward(Jsp page) throws ServletException, IOException {
        RequestDispatcher dispatcher = context.getRequestDispatcher(page.getRoute());

        dispatcher.forward(request, response);
    }

    protected void json(Object object) throws IOException {
        json(object, HttpServletResponse.SC_OK);
    }

    protected void json(Object object, int status) throws IOException {
        PrintWriter out = response.getWriter();
        String objectAsString = new Gson().toJson(object);

        response.setContentType(ContentType.JSON);
        response.setStatus(status);
        response.setCharacterEncoding("UTF-8");

        out.print(objectAsString);
        out.flush();
    }
}
