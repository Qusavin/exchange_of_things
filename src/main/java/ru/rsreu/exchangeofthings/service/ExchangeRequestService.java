package ru.rsreu.exchangeofthings.service;

import ru.rsreu.exchangeofthings.database.entity.ExchangeRequest;
import ru.rsreu.exchangeofthings.enums.Status;

import java.util.List;

public interface ExchangeRequestService {
    List<ExchangeRequest> findByReceiverIdAndStatus(int receiverId, String status);

    void deleteById(int id);

    void updateStatus(int senItemId, int recItemId, Status status);
}
