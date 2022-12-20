package ru.rsreu.exchangeofthings.database.dao;

import ru.rsreu.exchangeofthings.persistent.entity.Item;

import java.util.List;
import java.util.Optional;

/**
 * Interface by item dao.
 */
public interface ItemDAO {
    /**
     * Find all items.
     *
     * @return List of items
     */
    List<Item> findAll();

    /**
     * Save item.
     *
     * @param item Item
     * @return Saved item
     */
    Optional<Item> save(Item item);

    /**
     * Find item by id.
     *
     * @param id Item id
     * @return Item
     */
    Optional<Item> findById(int id);

    /**
     * Find item by owner id.
     *
     * @param ownerId Owner id
     * @return Item
     */
    List<Item> findByOwnerId(int ownerId);

    /**
     * Find available items.
     *
     * @return List of available items
     */
    List<Item> findAvailableItems();

    /**
     * Delete item by id.
     *
     * @param id Item id
     */
    void deleteById(int id);

    /**
     * Update is available status of item by id.
     *
     * @param id Item id
     */
    void updateIsAvailable(int id);

    /**
     * Update owner id of item by item id.
     *
     * @param itemId Item id
     * @param ownerId Owner id
     */
    void updateOwner(int itemId, int ownerId);

    /**
     * Update views number by item id.
     *
     * @param id Item id
     */
    void updateViewsNumber(int id);
}
