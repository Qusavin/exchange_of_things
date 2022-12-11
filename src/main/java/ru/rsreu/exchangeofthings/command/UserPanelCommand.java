package ru.rsreu.exchangeofthings.command;

import ru.rsreu.exchangeofthings.database.entity.Item;
import ru.rsreu.exchangeofthings.enums.Jsp;
import ru.rsreu.exchangeofthings.enums.UserPanelTablePart;
import ru.rsreu.exchangeofthings.service.ItemService;
import ru.rsreu.exchangeofthings.service.ServiceFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static ru.rsreu.exchangeofthings.constant.RequestAttribute.ITEMS_ATTR;
import static ru.rsreu.exchangeofthings.constant.RequestParam.*;

public class UserPanelCommand extends FrontCommand {
    private ItemService itemService;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        itemService = ServiceFactory.getItemService();
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

        removeItem(tablePart);
        addItemToExchange(tablePart);

        renderTablePart(tablePart);
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

    private void addItemToExchange(UserPanelTablePart tablePart) {
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
        List<Item> items = itemService.findByOwnerId(1);

        request.setAttribute(ITEMS_ATTR, items);

        forward(Jsp.USER_PANEL);
    }

    private void exchangeItems() throws ServletException, IOException {
        List<Item> items = itemService.findAvailableItems();

        request.setAttribute(ITEMS_ATTR, items);

        forward(Jsp.USER_PANEL_EXCHANGE_ITEMS);
    }

    private void myItems() throws ServletException, IOException {
        List<Item> items = itemService.findByOwnerId(1);

        request.setAttribute(ITEMS_ATTR, items);

        forward(Jsp.USER_PANEL_ITEMS);
    }

    private void myExchangeItems() throws ServletException, IOException {
        List<Item> items = itemService.findByOwnerId(1).stream()
                .filter(Item::getAvailable).collect(Collectors.toList());

        request.setAttribute(ITEMS_ATTR, items);

        forward(Jsp.USER_PANEL_MY_EXCHANGE_ITEMS);
    }
}
