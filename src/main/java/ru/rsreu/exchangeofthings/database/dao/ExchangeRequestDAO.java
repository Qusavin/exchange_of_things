package ru.rsreu.exchangeofthings.database.dao;

import ru.rsreu.exchangeofthings.persistent.entity.User;
import ru.rsreu.exchangeofthings.persistent.entity.ExchangeRequest;
import ru.rsreu.exchangeofthings.persistent.entity.Item;

import java.util.List;

/**
 * Interface for exchange request dao.
 */
public interface ExchangeRequestDAO {
    /**
     * Find exchange requests by status.
     *
     * @param status Status of exchange request
     * @return List of exchange requests
     */
    List<ExchangeRequest> findByStatus(String status);

    /**
     * Find exchange requests by receiver id.
     *
     * @param receiverId Receiver id
     * @param status Exchange request status
     * @return List of exchange requests
     */
    List<ExchangeRequest> findByReceiverIdAndStatus(int receiverId, String status);

    /**
     * Save exchange request.
     *
     * @param exchangeRequest Exchange request
     */
    void save(ExchangeRequest exchangeRequest);

    /**
     * Delete exchange request by id.
     *
     * @param id Exchange request id
     */
    void deleteById(int id);

    /**
     * Update status of exchange request.
     *
     * @param senItem Sending item
     * @param recItem Receiving item
     * @param status Status
     */
    void updateStatus(Item senItem, Item recItem, String status);

    /**
     * Update status of exchange request.
     *
     * @param recItem Receiving item
     * @param status Status
     */
    void updateStatusByReceiverItem(Item recItem, String status);

    /**
     * Update status of exchange request.
     *
     * @param senItem Sending item
     * @param status Status
     */
    void updateStatusBySenderItem(Item senItem, String status);

    /**
     * Reject exchange requests by user.
     *
     * @param user User
     */
    void rejectByBlockedUser(User user);
}
