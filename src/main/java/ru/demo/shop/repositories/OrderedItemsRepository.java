package ru.demo.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demo.shop.models.OrderedItem;
import ru.demo.shop.models.OrderedItemsId;

@Repository
public interface OrderedItemsRepository extends JpaRepository<OrderedItem, OrderedItemsId> {
}
