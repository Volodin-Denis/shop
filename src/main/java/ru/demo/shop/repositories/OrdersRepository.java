package ru.demo.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demo.shop.models.Order;
import ru.demo.shop.models.Status;
import ru.demo.shop.models.User;

import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findOrderByUserAndStatus(User user, Status status);
}
