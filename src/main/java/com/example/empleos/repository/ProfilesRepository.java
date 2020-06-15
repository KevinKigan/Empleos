package com.example.empleos.repository;

import com.example.empleos.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilesRepository extends JpaRepository<Profile, Integer> {
}
