package com.example.empleos.service;

import com.example.empleos.model.Category;
import com.example.empleos.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService implements CategoriesServiceInterface {

    @Autowired
    private CategoriesRepository categoriesRepo;

    private LinkedList<Category> list;

    @Override
    public void save(Category category) {
        categoriesRepo.save(category);
        System.out.println();
    }

    @Override
    public List<Category> findAll() {
        return categoriesRepo.findAll();
    }

    @Override
    public Category findById(int id) {
        Optional<Category> optional = categoriesRepo.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}
