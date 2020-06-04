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
        try {
            return list.stream().filter(cat -> cat.getId() == id).findFirst().get();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer categoriesSize() {
        return list.size();
    }

    public CategoriesService(){
        list = new LinkedList<>();
        // Category 1
        Category cat1 = new Category();
        cat1.setId(1);
        cat1.setName("Contabilidad");
        cat1.setDescription("Descripcion de la Category Contabilidad");

        // Category 2
        Category cat2 = new Category();
        cat2.setId(2);
        cat2.setName("Ventas");
        cat2.setDescription("Trabajos relacionados con Ventas");


        // Category 3
        Category cat3 = new Category();
        cat3.setId(3);
        cat3.setName("Comunicaciones");
        cat3.setDescription("Trabajos relacionados con Comunicaciones");

        // Category 4
        Category cat4 = new Category();
        cat4.setId(4);
        cat4.setName("Arquitectura");
        cat4.setDescription("Trabajos de Arquitectura");

        // Category 5
        Category cat5 = new Category();
        cat5.setId(5);
        cat5.setName("Educacion");
        cat5.setDescription("Maestros, tutores, etc");

        // Category 6
        Category cat6 = new Category();
        cat6.setId(6);
        cat6.setName("Desarrollo de software");
        cat6.setDescription("Trabajo para programadores");

        /**
         * Agregamos los 5 objetos de tipo Category a la lista ...
         */
        list.add(cat1);
        list.add(cat2);
        list.add(cat3);
        list.add(cat4);
        list.add(cat5);
        list.add(cat6);

    }
}
