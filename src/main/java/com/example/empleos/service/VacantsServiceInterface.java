package com.example.empleos.service;

import com.example.empleos.model.Vacant;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface VacantsServiceInterface {
    List<Vacant> findAll();
    Page<Vacant> findAll(Pageable pageable);
    Vacant findById(Integer id);
    void save(Vacant vacant);
    List<Vacant> findOutstanding(boolean outstanding);
    void delete(int idVacant);
    List<Vacant> findByExample(Example<Vacant> example);
}
