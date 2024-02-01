package com.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.model.Appointment;
import com.demo.model.Doctor;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByDoctor(Doctor doctor);
}

