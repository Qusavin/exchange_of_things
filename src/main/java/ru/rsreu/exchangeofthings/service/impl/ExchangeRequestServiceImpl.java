package ru.rsreu.exchangeofthings.service.impl;

import ru.rsreu.exchangeofthings.dao.DAOFactory;
import ru.rsreu.exchangeofthings.dao.ExchangeRequestDAO;
import ru.rsreu.exchangeofthings.dao.ItemDAO;
import ru.rsreu.exchangeofthings.database.entity.ExchangeRequest;
import ru.rsreu.exchangeofthings.database.entity.Item;
import ru.rsreu.exchangeofthings.enums.Status;
import ru.rsreu.exchangeofthings.service.ExchangeRequestService;

import java.util.List;

public class ExchangeRequestServiceImpl implements ExchangeRequestService {
    private static volatile ExchangeRequestServiceImpl instance;
    private ExchangeRequestDAO exchangeRequestDAO;
    private ItemDAO itemDAO;

    public ExchangeRequestServiceImpl(ExchangeRequestDAO exchangeRequestDAO, ItemDAO itemDAO) {
        this.exchangeRequestDAO = exchangeRequestDAO;
        this.itemDAO = itemDAO;
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

        exchangeRequestDAO.updateStatus(senItem, recItem, status.getValue());
        itemDAO.updateOwner(senItem.getId(), recItem.getOwner().getId());
        itemDAO.updateOwner(recItem.getId(), senItem.getOwner().getId());
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

                instance = new ExchangeRequestServiceImpl(exchangeRequestDAO, itemDAO);
            }
        }

        return instance;
    }
}
