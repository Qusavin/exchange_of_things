package ru.rsreu.exchangeofthings.service.impl;

import ru.rsreu.exchangeofthings.database.dao.DAOFactory;
import ru.rsreu.exchangeofthings.database.dao.ExchangeRequestDAO;
import ru.rsreu.exchangeofthings.database.dao.ItemDAO;
import ru.rsreu.exchangeofthings.database.dao.NotificationDAO;
import ru.rsreu.exchangeofthings.persistent.entity.ExchangeRequest;
import ru.rsreu.exchangeofthings.persistent.entity.Item;
import ru.rsreu.exchangeofthings.persistent.entity.Notification;
import ru.rsreu.exchangeofthings.persistent.enums.Status;
import ru.rsreu.exchangeofthings.service.ExchangeRequestService;
import ru.rsreu.exchangeofthings.support.mapper.DAOMapper;
import ru.rsreu.exchangeofthings.support.util.MessageUtil;

import java.util.List;

public class ExchangeRequestServiceImpl implements ExchangeRequestService {
    private static volatile ExchangeRequestServiceImpl instance;
    private ExchangeRequestDAO exchangeRequestDAO;
    private ItemDAO itemDAO;
    private NotificationDAO notificationDAO;

    public ExchangeRequestServiceImpl(ExchangeRequestDAO exchangeRequestDAO, ItemDAO itemDAO, NotificationDAO notificationDAO) {
        this.exchangeRequestDAO = exchangeRequestDAO;
        this.itemDAO = itemDAO;
        this.notificationDAO = notificationDAO;
    }

    @Override
    public List<ExchangeRequest> findByStatus(Status status) {
        return exchangeRequestDAO.findByStatus(status.getValue());
    }

    @Override
    public List<ExchangeRequest> findByReceiverIdAndStatus(int receiverId, String status) {
        return exchangeRequestDAO.findByReceiverIdAndStatus(receiverId, status);
    }

    @Override
    public void updateStatus(int senItemId, int recItemId, Status status) {
        Item senItem = new Item(senItemId);
        Item recItem = new Item(recItemId);

        if (status == Status.IN_PROCESS) {
            ExchangeRequest exchangeRequest = new ExchangeRequest(
                    recItem,
                    senItem,
                    status.getValue()
            );

            exchangeRequestDAO.save(exchangeRequest);

            return;
        }

        senItem = itemDAO.findById(senItemId).orElseThrow(RuntimeException::new);
        recItem = itemDAO.findById(recItemId).orElseThrow(RuntimeException::new);

        exchangeRequestDAO.updateStatusByReceiverItem(recItem, Status.REJECTED.getValue());
        exchangeRequestDAO.updateStatus(senItem, recItem, status.getValue());

        if (status == Status.ACCEPTED) {
            itemDAO.updateOwner(senItem.getId(), recItem.getOwner().getId());
            itemDAO.updateOwner(recItem.getId(), senItem.getOwner().getId());
        }

        notificationDAO.save(new Notification(
                senItem.getOwner().getId(),
                recItem.getOwner().getId(),
                MessageUtil.getDeclineMessage(senItem.getTitle(), recItem.getTitle())
        ));
    }

    @Override
    public void deleteById(int id) {
        exchangeRequestDAO.deleteById(id);
    }

    public static ExchangeRequestServiceImpl getInstance() {
        synchronized (ExchangeRequestServiceImpl.class) {
            if (instance == null) {
                ExchangeRequestDAO exchangeRequestDAO = DAOFactory.getExchangeRequestDAO();
                ItemDAO itemDAO = DAOFactory.getItemDAO();
                NotificationDAO notificationDAO = DAOFactory.getNotificationDAO();

                instance = new ExchangeRequestServiceImpl(exchangeRequestDAO, itemDAO, notificationDAO);
            }
        }

        return instance;
    }
}
