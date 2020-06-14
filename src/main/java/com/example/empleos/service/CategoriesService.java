package com.example.empleos.service;

import com.example.empleos.model.Category;
import com.example.empleos.model.Vacant;
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

    /**
     * Metodo para guardar una categoria
     *
     * @param category Categortia a guardar
     */
    @Override
    public void save(Category category) {
        categoriesRepo.save(category);
        System.out.println();
    }

    /**
     * Metodo para buscar todas las categorias
     *
     * @return Todas las categorias
     */

    @Override
    public List<Category> findAll() {
        return categoriesRepo.findAll();
    }

    /**
     * Metodo para borrar una categoria por su id
     *
     * @param idCategory Id de la categoria a borrar
     */

    @Override
    public void delete(int idCategory) {
        categoriesRepo.deleteById(idCategory);
    }

    /**
     * Metodo para retornar una categoria de la lista
     * @param id Numero de identificacion de la categoria a buscar
     * @return Categoria encontrada
     */

    @Override
    public Category findById(Integer id) {
        Optional<Category> optional = categoriesRepo.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

}
