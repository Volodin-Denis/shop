package ru.demo.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demo.shop.models.User;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
}
