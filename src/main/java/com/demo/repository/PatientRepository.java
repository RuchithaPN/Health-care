package com.demo.repository;


import com.demo.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    // You can add custom queries or methods specific to patients if needed
}
