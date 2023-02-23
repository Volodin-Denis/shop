package ru.demo.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.demo.shop.models.Item;
import ru.demo.shop.models.Order;
import ru.demo.shop.models.OrderedItem;
import ru.demo.shop.repositories.OrderedItemsRepository;

@Service
public class OrderedItemsService {

    private final OrderedItemsRepository orderedItemsRepository;

    @Autowired
    public OrderedItemsService(OrderedItemsRepository orderedItemsRepository) {
        this.orderedItemsRepository = orderedItemsRepository;
    }

    @Transactional
    public void save(OrderedItem orderedItem){
        orderedItemsRepository.save(orderedItem);
    }

    @Transactional
    public void remove(OrderedItem orderedItem){
        orderedItemsRepository.delete(orderedItem);
    }

    @Transactional
    public void deleteFromCart(Order cart, Item item) {
        for(OrderedItem existingOrderedItem : cart.getOrderedItems()){
        if(existingOrderedItem.getItem().equals(item)) {
            remove(existingOrderedItem);
            return;
        }
    }

    }
}
