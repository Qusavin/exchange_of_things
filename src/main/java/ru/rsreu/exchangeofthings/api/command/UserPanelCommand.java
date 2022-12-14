package ru.rsreu.exchangeofthings.api.command;

import ru.rsreu.exchangeofthings.persistent.entity.ExchangeRequest;
import ru.rsreu.exchangeofthings.persistent.entity.Item;
import ru.rsreu.exchangeofthings.persistent.entity.Notification;
import ru.rsreu.exchangeofthings.persistent.entity.User;
import ru.rsreu.exchangeofthings.persistent.enums.Jsp;
import ru.rsreu.exchangeofthings.persistent.enums.Status;
import ru.rsreu.exchangeofthings.persistent.enums.UserPanelTablePart;
import ru.rsreu.exchangeofthings.service.*;
import ru.rsreu.exchangeofthings.support.util.UserUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static ru.rsreu.exchangeofthings.constant.RequestAttribute.*;
import static ru.rsreu.exchangeofthings.constant.RequestParam.*;

public class UserPanelCommand extends FrontCommand {
    private ItemService itemService;
    private UserService userService;
    private ExchangeRequestService exchangeRequestService;
    private NotificationService notificationService;
    private User user;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        user = UserUtil.getFromRequest(request).orElseThrow(RuntimeException::new);
        itemService = ServiceFactory.getItemService();
        userService = ServiceFactory.getUserService();
        exchangeRequestService = ServiceFactory.getExchangeRequestService();
        notificationService = ServiceFactory.getNotificationService();
    }

    @Override
    public void process() throws ServletException, IOException {
        String userPanelTableTypeAsString = request.getParameter(USER_PANEL_TABLE_PART);

        if (userPanelTableTypeAsString == null) {
            renderPage();
            return;
        }

        UserPanelTablePart tablePart = UserPanelTablePart.valueOf(
                userPanelTableTypeAsString.toUpperCase()
        );

        renderTablePart(tablePart);
    }

    @Override
    public void send() throws ServletException, IOException {
        UserPanelTablePart tablePart = UserPanelTablePart.valueOf(
                request.getParameter(USER_PANEL_TABLE_PART).toUpperCase()
        );

        addItem(tablePart);
        removeItem(tablePart);
        toggleItemExchange(tablePart);

        renderTablePart(tablePart);
    }

    private void addItem(UserPanelTablePart tablePart) {
        if (tablePart == UserPanelTablePart.MY_ITEMS && request.getParameter(USER_PANEL_ADD_ITEM) != null) {
            String title = request.getParameter(TITLE);
            String imageUrl = request.getParameter(IMAGE_URL);
            String category = request.getParameter(CATEGORY);
            String description = request.getParameter(DESCRIPTION);

            Item item = new Item(
                    title,
                    imageUrl,
                    category,
                    description,
                    userService.findById(user.getId())
            );

            itemService.save(item);
        }
    }

    private void removeItem(UserPanelTablePart tablePart) {
        String removeItemId = request.getParameter(REMOVE_ITEM_ID);

        if (
                removeItemId != null && (
                        tablePart == UserPanelTablePart.MY_ITEMS ||
                                tablePart == UserPanelTablePart.MY_EXCHANGE_ITEMS
                )
        ) {
            itemService.deleteById(Integer.parseInt(removeItemId));
        }
    }

    private void toggleItemExchange(UserPanelTablePart tablePart) {
        String exchangeItemId = request.getParameter(USER_PANEL_EXCHANGE_ITEM_ID);

        if (
                exchangeItemId != null && (
                        tablePart == UserPanelTablePart.MY_ITEMS ||
                                tablePart == UserPanelTablePart.MY_EXCHANGE_ITEMS
                )
        ) {
            itemService.updateIsAvailable(Integer.parseInt(exchangeItemId));
        }
    }

    private void renderTablePart(UserPanelTablePart tablePart) throws ServletException, IOException {
        switch (tablePart) {
            case MY_ITEMS:
                myItems();
                break;
            case MY_EXCHANGE_ITEMS:
                myExchangeItems();
                break;
            case EXCHANGE_ITEMS:
                exchangeItems();
                break;
        }
    }

    private void renderPage() throws ServletException, IOException {
        List<Item> items = itemService.findByOwnerId(user.getId());
        List<ExchangeRequest> exchangeRequests = exchangeRequestService.findByReceiverIdAndStatus(
                user.getId(), Status.IN_PROCESS.getValue()
        );
        List<Notification> notifications = notificationService.findByUserId(user.getId());

        request.setAttribute(ITEMS, items);
        request.setAttribute(NOTIFICATIONS, notifications);
        request.setAttribute(EXCHANGE_REQUESTS, exchangeRequests);

        forward(Jsp.USER_PANEL);
    }

    private void exchangeItems() throws ServletException, IOException {
        List<Item> items = itemService.findAvailableItems().stream()
                .filter(item -> !item.getOwner().getId().equals(user.getId()) && !item.getOwner().getBlocked())
                .collect(Collectors.toList());

        request.setAttribute(ITEMS, items);

        forward(Jsp.USER_PANEL_EXCHANGE_ITEMS);
    }

    private void myItems() throws ServletException, IOException {
        List<Item> items = itemService.findByOwnerId(user.getId());

        request.setAttribute(ITEMS, items);

        forward(Jsp.USER_PANEL_ITEMS);
    }

    private void myExchangeItems() throws ServletException, IOException {
        List<Item> items = itemService.findByOwnerId(user.getId()).stream()
                .filter(Item::getAvailable).collect(Collectors.toList());

        request.setAttribute(ITEMS, items);

        forward(Jsp.USER_PANEL_MY_EXCHANGE_ITEMS);
    }
}
