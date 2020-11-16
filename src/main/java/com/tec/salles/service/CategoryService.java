package com.tec.salles.service;

import com.tec.salles.entity.Category;
import com.tec.salles.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category saveOrUpdate(final Category category) {
        if(category.getId() == null) {
            category.setId(UUID.randomUUID().toString());
        }

        return this.categoryRepository.save(category);
    }

    public Category findById(final String categoryId) {
        return this.categoryRepository.findById(categoryId).orElseThrow();
    }

    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    public void delete(final String categoryId) {
        this.categoryRepository.delete(this.findById(categoryId));
    }
}
