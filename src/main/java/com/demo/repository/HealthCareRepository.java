package com.demo.repository;


import com.demo.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthCareRepository extends JpaRepository<Doctor, Integer> {
    // Add custom repository methods if needed
}
