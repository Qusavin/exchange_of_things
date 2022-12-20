package ru.rsreu.exchangeofthings.api.command;

import ru.rsreu.exchangeofthings.persistent.entity.ExchangeRequest;
import ru.rsreu.exchangeofthings.persistent.entity.Item;
import ru.rsreu.exchangeofthings.persistent.entity.User;
import ru.rsreu.exchangeofthings.persistent.enums.Jsp;
import ru.rsreu.exchangeofthings.persistent.enums.Role;
import ru.rsreu.exchangeofthings.persistent.enums.Status;
import ru.rsreu.exchangeofthings.service.ExchangeRequestService;
import ru.rsreu.exchangeofthings.service.ItemService;
import ru.rsreu.exchangeofthings.service.ServiceFactory;
import ru.rsreu.exchangeofthings.service.UserService;
import ru.rsreu.exchangeofthings.support.util.UserUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.rsreu.exchangeofthings.constant.RequestAttribute.*;
import static ru.rsreu.exchangeofthings.constant.RequestParam.*;

public class ModeratorPanelCommand extends FrontCommand {
    private UserService userService;
    private ItemService itemService;
    private ExchangeRequestService exchangeRequestService;
    private User user;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        user = UserUtil.getFromRequest(request).orElseThrow(RuntimeException::new);
        userService = ServiceFactory.getUserService();
        itemService = ServiceFactory.getItemService();
        exchangeRequestService = ServiceFactory.getExchangeRequestService();
    }

    @Override
    public void process() throws ServletException, IOException {
        List<User> users = userService.findAll().stream()
                .filter(user -> !user.getRole().equalsIgnoreCase(Role.ADMIN.getName()))
                .collect(Collectors.toList());
        List<Item> items = itemService.findAll();
        List<ExchangeRequest> exchangeRequests = exchangeRequestService.findByStatus(Status.IN_PROCESS);

        request.setAttribute(EXCHANGE_REQUESTS, exchangeRequests);
        request.setAttribute(ITEMS, items);
        request.setAttribute(USERS, users);
        request.setAttribute(USER, user);

        forward(Jsp.MODERATOR_PANEL);
    }

    @Override
    public void send() throws ServletException, IOException {
        String[] ids = request.getParameterValues(ID);
        List<Integer> userIds = ids != null
                ? Arrays.stream(ids).map(Integer::parseInt).collect(Collectors.toList())
                : List.of();

        userIds.forEach(id -> userService.updateIsBlocked(id));

        declineRequest();
        removeItem();
    }

    private void declineRequest() throws ServletException, IOException {
        String senItemIdAsString = request.getParameter(SEN_ITEM_ID);
        String recItemIdAsString = request.getParameter(REC_ITEM_ID);

        if (senItemIdAsString != null && recItemIdAsString != null) {
            int senItemId = Integer.parseInt(senItemIdAsString);
            int recItemId = Integer.parseInt(recItemIdAsString);

            exchangeRequestService.updateStatus(senItemId, recItemId, Status.REJECTED);

            List<ExchangeRequest> exchangeRequests = exchangeRequestService.findByStatus(Status.IN_PROCESS);

            request.setAttribute(EXCHANGE_REQUESTS, exchangeRequests);

            forward(Jsp.MODERATOR_PANEL_EXCHANGE_REQUESTS_TABLE);
        }
    }

    private void removeItem() throws ServletException, IOException {
        String removeItemId = request.getParameter(REMOVE_ITEM_ID);

        if (removeItemId != null) {
            itemService.deleteById(Integer.parseInt(removeItemId));

            List<Item> items = itemService.findAll();

            request.setAttribute(ITEMS, items);

            forward(Jsp.MODERATOR_PANEL_ITEMS_TABLE);
        }
    }
}
