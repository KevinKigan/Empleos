package com.example.empleos.service;

import com.example.empleos.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CategoriesServiceInterface {
    void save(Category category);
    List<Category> findAll();
    Page<Category> findAll(Pageable page);
    void delete(int idCategory);
    Category findById(Integer id);

}
