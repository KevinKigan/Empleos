package com.example.empleos.service;

import com.example.empleos.model.User;
import com.example.empleos.model.Vacant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsersServiceInterface {
    void save(User user);
    List<User> findAll();
    User findById(Integer id);
    void blockUnblock(Integer id);
    Page<User> findAll(Pageable pageable);
    User findByUsername(String username);

}
