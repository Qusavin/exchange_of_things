package ru.rsreu.exchangeofthings.service;

import ru.rsreu.exchangeofthings.persistent.request.ExchangeRequest;
import ru.rsreu.exchangeofthings.persistent.enums.Status;

import java.util.List;

public interface ExchangeRequestService {
    List<ExchangeRequest> findByStatus(Status status);

    List<ExchangeRequest> findByReceiverIdAndStatus(int receiverId, String status);

    void deleteById(int id);

    void updateStatus(int senItemId, int recItemId, Status status);
}
