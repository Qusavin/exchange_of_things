package ru.rsreu.exchangeofthings.service.impl;

import ru.rsreu.exchangeofthings.dao.DAOFactory;
import ru.rsreu.exchangeofthings.dao.ExchangeRequestDAO;
import ru.rsreu.exchangeofthings.database.entity.ExchangeRequest;
import ru.rsreu.exchangeofthings.service.ExchangeRequestService;

import java.util.List;

public class ExchangeRequestServiceImpl implements ExchangeRequestService {
    private static volatile ExchangeRequestServiceImpl instance;
    private ExchangeRequestDAO exchangeRequestDAO;

    public ExchangeRequestServiceImpl(ExchangeRequestDAO exchangeRequestDAO) {
        this.exchangeRequestDAO = exchangeRequestDAO;
    }

    @Override
    public List<ExchangeRequest> findByReceiverIdAndStatus(int receiverId, String status) {
        return exchangeRequestDAO.findByReceiverIdAndStatus(receiverId, status);
    }

    @Override
    public void save(ExchangeRequest exchangeRequest) {
        exchangeRequestDAO.save(exchangeRequest);
    }

    @Override
    public void deleteById(int id) {
        exchangeRequestDAO.deleteById(id);
    }

    @Override
    public void updateStatus(int id, String status) {
        exchangeRequestDAO.updateStatus(id, status);
    }

    public static ExchangeRequestServiceImpl getInstance() {
        synchronized (ExchangeRequestServiceImpl.class) {
            if (instance == null) {
                ExchangeRequestDAO exchangeRequestDAO = DAOFactory.getExchangeRequestDAO();
                instance = new ExchangeRequestServiceImpl(exchangeRequestDAO);
            }
        }

        return instance;
    }
}
