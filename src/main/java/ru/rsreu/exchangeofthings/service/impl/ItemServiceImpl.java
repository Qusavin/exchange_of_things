package ru.rsreu.exchangeofthings.service.impl;

import ru.rsreu.exchangeofthings.dao.DAOFactory;
import ru.rsreu.exchangeofthings.dao.ItemDAO;
import ru.rsreu.exchangeofthings.database.entity.Item;
import ru.rsreu.exchangeofthings.service.ItemService;

import java.util.List;

public class ItemServiceImpl implements ItemService {
    private static volatile ItemServiceImpl instance;
    private ItemDAO itemDAO;

    public ItemServiceImpl(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @Override
    public List<Item> findAll() {
        return itemDAO.findAll();
    }

    @Override
    public Item save(Item item) {
        return itemDAO.save(item)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Item findById(int id) {
        return itemDAO.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Item> findByOwnerId(int ownerId) {
        return itemDAO.findByOwnerId(ownerId);
    }

    @Override
    public List<Item> findAvailableItems() {
        return itemDAO.findAvailableItems();
    }

    @Override
    public void deleteById(int id) {
        itemDAO.deleteById(id);
    }

    @Override
    public void updateIsAvailable(int id) {
        itemDAO.updateIsAvailable(id);
    }

    public static ItemServiceImpl getInstance() {
        synchronized (ItemServiceImpl.class) {
            if (instance == null) {
                ItemDAO itemDAO = DAOFactory.getItemDAO();
                instance = new ItemServiceImpl(itemDAO);
            }
        }

        return instance;
    }
}
