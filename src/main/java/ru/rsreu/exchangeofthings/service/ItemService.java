package ru.rsreu.exchangeofthings.service;

import ru.rsreu.exchangeofthings.database.entity.Item;

import java.util.List;

public interface ItemService {
    List<Item> findAll();

    Item save(Item item);

    Item findById(int id);

    List<Item> findByOwnerId(int ownerId);

    List<Item> findAvailableItems();

    void deleteById(int id);

    void updateIsAvailable(int id);
}
