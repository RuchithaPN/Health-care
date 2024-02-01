package com.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;




import com.demo.model.Appointment;
import com.demo.model.Doctor;
import com.demo.model.Patient;
import com.demo.repository.HealthCareRepository;
import com.demo.repository.PatientRepository;
import com.demo.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HealthCareService {
    private final HealthCareRepository healthCareRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    private List<Appointment> appointments = new ArrayList<>();
    private Map<Doctor, List<Appointment>> doctorAppointmentsMap = new HashMap<>();

    public HealthCareService(
            HealthCareRepository healthCareRepository,
            PatientRepository patientRepository,
            AppointmentRepository appointmentRepository) {
        this.healthCareRepository = healthCareRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<Doctor> getAllDoctors() {
        return healthCareRepository.findAll();
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<Appointment> getAppointmentsForDoctor(Doctor doctor) {
        // Fetch appointments from the database
        List<Appointment> appointmentsForDoctor = appointmentRepository.findByDoctor(doctor);

        // Sort appointments based on appointment time
        appointmentsForDoctor.sort(Comparator.comparing(Appointment::getAppointmentTime));

      
        doctorAppointmentsMap.put(doctor, appointmentsForDoctor);

        return appointmentsForDoctor;
    }

    public void addDoctor(Doctor doctor) {
        healthCareRepository.save(doctor);
    }

    public void addPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public void scheduleAppointment(Doctor doctor, Patient patient, LocalDateTime appointmentTime) {
        // Assuming you have an appointment repository, add the appointment
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentTime(appointmentTime);

        appointmentRepository.save(appointment);

        // Update the in-memory map for doctor's appointments
        doctorAppointmentsMap.computeIfAbsent(doctor, k -> new ArrayList<>()).add(appointment);
    }

    public Optional<Doctor> getDoctorById(int doctorId) {
        return healthCareRepository.findById(doctorId);
    }
}
