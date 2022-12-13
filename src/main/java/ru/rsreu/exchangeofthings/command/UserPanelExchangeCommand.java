package ru.rsreu.exchangeofthings.command;

import ru.rsreu.exchangeofthings.database.entity.Item;
import ru.rsreu.exchangeofthings.database.entity.User;
import ru.rsreu.exchangeofthings.enums.Jsp;
import ru.rsreu.exchangeofthings.enums.Status;
import ru.rsreu.exchangeofthings.enums.UserPanelExchangeMode;
import ru.rsreu.exchangeofthings.service.ExchangeRequestService;
import ru.rsreu.exchangeofthings.service.ItemService;
import ru.rsreu.exchangeofthings.service.ServiceFactory;
import ru.rsreu.exchangeofthings.util.UserUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static ru.rsreu.exchangeofthings.constant.RequestAttribute.*;
import static ru.rsreu.exchangeofthings.constant.RequestAttribute.MODE;
import static ru.rsreu.exchangeofthings.constant.RequestParam.*;

public class UserPanelExchangeCommand extends FrontCommand {
    private ItemService itemService;
    private ExchangeRequestService exchangeRequestService;
    private User user;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        user = UserUtil.getFromRequest(request).orElseThrow(RuntimeException::new);
        itemService = ServiceFactory.getItemService();
        exchangeRequestService = ServiceFactory.getExchangeRequestService();
    }

    @Override
    public void process() throws ServletException, IOException {
        String senItemIdAsString = request.getParameter(SEN_ITEM_ID);
        String recItemIdAsString = request.getParameter(REC_ITEM_ID);

        UserPanelExchangeMode mode = senItemIdAsString == null
                ? UserPanelExchangeMode.MAKE_REQUEST
                : UserPanelExchangeMode.COMPLETE_REQUEST;

        List<Item> items = itemService.findByOwnerId(user.getId()).stream()
                .filter(Item::getAvailable).collect(Collectors.toList());

        Item senItem = mode == UserPanelExchangeMode.MAKE_REQUEST
                ? items.get(0)
                : itemService.findById(Integer.parseInt(senItemIdAsString));
        Item recItem = itemService.findById(Integer.parseInt(recItemIdAsString));

        request.setAttribute(MODE, mode);
        request.setAttribute(ITEMS, items);
        request.setAttribute(SEN_ITEM, senItem);
        request.setAttribute(REC_ITEM, recItem);

        forward(Jsp.USER_PANEL_EXCHANGE);
    }

    @Override
    public void send() {
        Status status = Status.valueOf(request.getParameter(ITEM_STATUS).toUpperCase());
        int senItemId = Integer.parseInt(request.getParameter(SEN_ITEM_ID));
        int recItemId = Integer.parseInt(request.getParameter(REC_ITEM_ID));

        exchangeRequestService.updateStatus(senItemId, recItemId, status);
    }
}
