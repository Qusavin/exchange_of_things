package ru.rsreu.exchangeofthings.command;

import ru.rsreu.exchangeofthings.database.entity.Item;
import ru.rsreu.exchangeofthings.enums.Jsp;
import ru.rsreu.exchangeofthings.service.ItemService;
import ru.rsreu.exchangeofthings.service.ServiceFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static ru.rsreu.exchangeofthings.constant.RequestAttribute.*;
import static ru.rsreu.exchangeofthings.constant.RequestParam.USER_PANEL_ITEM_ID;

public class UserPanelExchangeCommand extends FrontCommand {
    private ItemService itemService;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        itemService = ServiceFactory.getItemService();
    }

    @Override
    public void process() throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(USER_PANEL_ITEM_ID));
        Item exchangeItem = itemService.findById(id);
        List<Item> items = itemService.findByOwnerId(1).stream()
                .filter(Item::getAvailable).collect(Collectors.toList());

        request.setAttribute(ITEMS_ATTR, items);
        request.setAttribute(EXCHANGE_ITEM, exchangeItem);
        forward(Jsp.USER_PANEL_EXCHANGE);
    }

    @Override
    public void send() throws ServletException, IOException {
    }
}
