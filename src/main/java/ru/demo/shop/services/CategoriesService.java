package ru.demo.shop.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.demo.shop.models.Category;
import ru.demo.shop.repositories.CategoriesRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public List<Category> findAll(){
        return categoriesRepository.findAll();
    }

    public Category findOne(int id) {
        return categoriesRepository.findById(id).orElse(null);
    }
}
