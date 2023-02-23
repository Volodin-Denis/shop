package ru.demo.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.demo.shop.models.Item;
import ru.demo.shop.repositories.ItemsRepository;

import java.util.List;

@Service
public class ItemsService {

    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemsService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public List<Item> findAllByCategoryId(int categoryId) {
        return itemsRepository.findAllByCategoryId(categoryId);
    }

    public Item findOne(int id) {
        return itemsRepository.findById(id).orElse(null);
    }
}
