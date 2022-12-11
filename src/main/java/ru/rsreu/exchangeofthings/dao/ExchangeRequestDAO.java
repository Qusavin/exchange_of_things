package ru.rsreu.exchangeofthings.dao;

import ru.rsreu.exchangeofthings.database.entity.ExchangeRequest;

import java.util.List;

public interface ExchangeRequestDAO {
    List<ExchangeRequest> findByReceiverIdAndStatus(int receiverId, String status);

    void save(ExchangeRequest exchangeRequest);

    void deleteById(int id);

    void updateStatus(int id, String status);
}