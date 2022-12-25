package ru.rsreu.exchangeofthings.api.command;

import ru.rsreu.exchangeofthings.persistent.entity.User;
import ru.rsreu.exchangeofthings.service.NotificationService;
import ru.rsreu.exchangeofthings.service.ServiceFactory;
import ru.rsreu.exchangeofthings.support.util.UserUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ru.rsreu.exchangeofthings.constant.RequestParam.NOTIFICATION_ID;

public class NotificationCommand extends FrontCommand {
    private NotificationService notificationService;
    private User user;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        user = UserUtil.getFromRequest(request).orElseThrow(RuntimeException::new);
        notificationService = ServiceFactory.getNotificationService();
    }

    @Override
    public void send() {
        Integer notificationId = Integer.parseInt(request.getParameter(NOTIFICATION_ID));

        notificationService.deleteById(notificationId);
    }
}
