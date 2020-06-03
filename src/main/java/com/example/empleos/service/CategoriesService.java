package com.example.empleos.service;

import com.example.empleos.model.Category;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CategoriesService implements CategoriesServiceInterface{

    private LinkedList<Category> list = null;

    @Override
    public void save(Category category) {
        list.add(category);
    }

    @Override
    public List<Category> findAll() {
        return list;
    }

    @Override
    public Category findById(int id) {

        return null;
    }

    public CategoriesService(){
        list = new LinkedList<>();
        Category category_1 = new Category();
        category_1.setId(1);
        category_1.setName("Creada");
        category_1.setDescription("Vacante creada pero sin aprobación");

        Category category_2 = new Category();
        category_2.setId(2);
        category_2.setName("Aprobada");
        category_2.setDescription("Vacante creada y con aprobación");

        Category category_3 = new Category();
        category_3.setName("Eliminada");
        category_3.setDescription("Vacante eliminada");
        category_3.setId(3);

        list.add(category_1);
        list.add(category_2);
        list.add(category_3);

    }
}
