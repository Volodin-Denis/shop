package ru.demo.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.demo.shop.models.*;
import ru.demo.shop.repositories.OrderedItemsRepository;
import ru.demo.shop.repositories.OrdersRepository;

import java.util.Optional;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrderedItemsRepository orderedItemsRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository, OrderedItemsRepository orderedItemsRepository) {
        this.ordersRepository = ordersRepository;
        this.orderedItemsRepository = orderedItemsRepository;
    }

    public Order getCart(User user){
        Optional<Order> cart = ordersRepository.findOrderByUserAndStatus(user, Status.IN_CART);
        if (cart.isPresent()) return cart.get();
        Order emptyCart = new Order(user, Status.IN_CART);
        ordersRepository.save(emptyCart);
        return emptyCart;
    }

    @Transactional
    public void save(Order order){
        for(OrderedItem orderedItem : order.getOrderedItems()) {
            orderedItemsRepository.save(orderedItem);
        }
        ordersRepository.save(order);
    }

    @Transactional
    public void updateCart(Order cart, Item item, int quantity) {
        for(OrderedItem existingOrderedItem : cart.getOrderedItems()){
            if(existingOrderedItem.getItem().equals(item)) {
                existingOrderedItem.setItemQuantity(existingOrderedItem.getItemQuantity() + quantity);
                save(cart);
                return;
            }
        }
        OrderedItem newOrderedItem = new OrderedItem(cart, item, quantity);
        orderedItemsRepository.save(newOrderedItem);
        cart.getOrderedItems().add(newOrderedItem);
        save(cart);
    }
}
