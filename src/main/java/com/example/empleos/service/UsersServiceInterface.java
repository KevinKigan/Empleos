package com.example.empleos.service;

import com.example.empleos.model.User;

import java.util.List;

public interface UsersServiceInterface {
    void save(User user);
    void delete(int idUser);
    List<User> findAll();
    User findById(Integer id);

}
