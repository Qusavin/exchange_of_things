package ru.rsreu.exchangeofthings.database.dao;

import ru.rsreu.exchangeofthings.persistent.request.ExchangeRequest;
import ru.rsreu.exchangeofthings.persistent.entity.Item;

import java.util.List;

public interface ExchangeRequestDAO {
    List<ExchangeRequest> findByStatus(String status);

    List<ExchangeRequest> findByReceiverIdAndStatus(int receiverId, String status);

    void save(ExchangeRequest exchangeRequest);

    void deleteById(int id);

    void updateStatus(Item senItem, Item recItem, String status);
}
