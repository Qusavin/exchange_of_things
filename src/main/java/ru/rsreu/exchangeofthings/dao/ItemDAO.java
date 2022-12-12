package ru.rsreu.exchangeofthings.dao;

import ru.rsreu.exchangeofthings.database.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDAO {
    Optional<Item> save(Item item);

    Optional<Item> findById(int id);

    List<Item> findByOwnerId(int ownerId);

    List<Item> findAvailableItems();

    void deleteById(int id);

    void updateIsAvailable(int id);

    void updateOwner(int itemId, int ownerId);
}
