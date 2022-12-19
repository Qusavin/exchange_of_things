package ru.rsreu.exchangeofthings.api.command;

import ru.rsreu.exchangeofthings.persistent.entity.Item;
import ru.rsreu.exchangeofthings.persistent.enums.Jsp;
import ru.rsreu.exchangeofthings.service.ItemService;
import ru.rsreu.exchangeofthings.service.ServiceFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.rsreu.exchangeofthings.constant.RequestAttribute.ITEM;
import static ru.rsreu.exchangeofthings.constant.RequestParam.USER_PANEL_ITEM_ID;

public class UserPanelThingCommand extends FrontCommand {
    private ItemService itemService;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        itemService = ServiceFactory.getItemService();
    }

    @Override
    public void process() throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(USER_PANEL_ITEM_ID));

        itemService.updateViewsNumber(id);

        Item updatedItem = itemService.findById(id);
        request.setAttribute(ITEM, updatedItem);
        forward(Jsp.USER_PANEL_THING);
    }
}
