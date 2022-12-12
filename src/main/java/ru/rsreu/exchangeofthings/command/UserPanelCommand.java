package ru.rsreu.exchangeofthings.command;

import ru.rsreu.exchangeofthings.constant.FormParam;
import ru.rsreu.exchangeofthings.database.entity.ExchangeRequest;
import ru.rsreu.exchangeofthings.database.entity.Item;
import ru.rsreu.exchangeofthings.enums.Jsp;
import ru.rsreu.exchangeofthings.enums.Status;
import ru.rsreu.exchangeofthings.enums.UserPanelTablePart;
import ru.rsreu.exchangeofthings.service.ExchangeRequestService;
import ru.rsreu.exchangeofthings.service.ItemService;
import ru.rsreu.exchangeofthings.service.ServiceFactory;
import ru.rsreu.exchangeofthings.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static ru.rsreu.exchangeofthings.constant.FormParam.*;
import static ru.rsreu.exchangeofthings.constant.RequestAttribute.EXCHANGE_REQUESTS;
import static ru.rsreu.exchangeofthings.constant.RequestAttribute.ITEMS;
import static ru.rsreu.exchangeofthings.constant.RequestParam.*;

public class UserPanelCommand extends FrontCommand {
    private ItemService itemService;
    private UserService userService;
    private ExchangeRequestService exchangeRequestService;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        itemService = ServiceFactory.getItemService();
        userService = ServiceFactory.getUserService();
        exchangeRequestService = ServiceFactory.getExchangeRequestService();
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
            String description = request.getParameter(CATEGORY);

            Item item = new Item(
                    title,
                    imageUrl,
                    category,
                    description,
                    userService.findById(2)
            );

            itemService.save(item);
        }
    }

    private void removeItem(UserPanelTablePart tablePart) {
        String removeItemId = request.getParameter(USER_PANEL_REMOVE_ITEM_ID);

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
        List<Item> items = itemService.findByOwnerId(2);
        List<ExchangeRequest> exchangeRequests = exchangeRequestService.findByReceiverIdAndStatus(
                2, Status.IN_PROCESS.getValue()
        );

        request.setAttribute(ITEMS, items);
        request.setAttribute(EXCHANGE_REQUESTS, exchangeRequests);

        forward(Jsp.USER_PANEL);
    }

    private void exchangeItems() throws ServletException, IOException {
        List<Item> items = itemService.findAvailableItems().stream()
                .filter(item -> item.getOwner().getId() != 2)
                .collect(Collectors.toList());

        request.setAttribute(ITEMS, items);

        forward(Jsp.USER_PANEL_EXCHANGE_ITEMS);
    }

    private void myItems() throws ServletException, IOException {
        List<Item> items = itemService.findByOwnerId(2);

        request.setAttribute(ITEMS, items);

        forward(Jsp.USER_PANEL_ITEMS);
    }

    private void myExchangeItems() throws ServletException, IOException {
        List<Item> items = itemService.findByOwnerId(2).stream()
                .filter(Item::getAvailable).collect(Collectors.toList());

        request.setAttribute(ITEMS, items);

        forward(Jsp.USER_PANEL_MY_EXCHANGE_ITEMS);
    }
}
