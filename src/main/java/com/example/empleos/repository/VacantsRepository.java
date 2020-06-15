package com.example.empleos.repository;

import com.example.empleos.model.Vacant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacantsRepository extends JpaRepository<Vacant, Integer> {
    List<Vacant> findByStatus(String status);
    List<Vacant> findByStatusAndOutstanding(String status, boolean outstanding);
    List<Vacant> findBySalaryBetweenOrderBySalaryDesc(double bottom, double top);
    List<Vacant> findByStatusIn(String[] status);
}
