package com.demo.controller;

import com.demo.model.Appointment;
import com.demo.model.Doctor;
import com.demo.model.Patient;
import com.demo.service.HealthCareService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/healthcare")
public class HealthCareController {
    private final HealthCareService healthCareService;

    public HealthCareController(HealthCareService healthCareService) {
        this.healthCareService = healthCareService;
    }

    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return healthCareService.getAllDoctors();
    }

    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return healthCareService.getAllPatients();
    }

    @GetMapping("/appointments/{doctorId}")
    public List<Appointment> getAppointmentsForDoctor(@PathVariable int doctorId) {
        return healthCareService.getDoctorById(doctorId)
                .map(healthCareService::getAppointmentsForDoctor)
                .orElse(null);
    }
}

