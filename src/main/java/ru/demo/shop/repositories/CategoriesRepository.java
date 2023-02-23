package ru.demo.shop.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demo.shop.models.Category;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Integer> {
}
