package ru.rsreu.exchangeofthings.database.dao;

import ru.rsreu.exchangeofthings.persistent.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDAO {
    List<Item> findAll();

    Optional<Item> save(Item item);

    Optional<Item> findById(int id);

    List<Item> findByOwnerId(int ownerId);

    List<Item> findAvailableItems();

    void deleteById(int id);

    void updateIsAvailable(int id);

    void updateOwner(int itemId, int ownerId);

    void updateViewsNumber(int id);
}