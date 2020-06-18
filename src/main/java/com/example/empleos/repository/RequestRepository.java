package com.example.empleos.repository;

import com.example.empleos.model.Request;
import com.example.empleos.model.User;
import com.example.empleos.model.Vacant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Integer> {
    List<Request> findByUser(User user);
    Request findByVacantAndUser(Vacant vacant, User User);
}
