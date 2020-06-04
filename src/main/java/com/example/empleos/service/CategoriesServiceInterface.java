package com.example.empleos.service;

import com.example.empleos.model.Category;

import java.util.List;


public interface CategoriesServiceInterface {
    void save(Category category);
    List<Category> findAll();
    Category findById(int id);

    Integer categoriesSize();
}
