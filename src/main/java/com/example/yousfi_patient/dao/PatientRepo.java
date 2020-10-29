package com.example.yousfi_patient.dao;

import com.example.yousfi_patient.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient,Long> {
    Page<Patient> findByNomContains(String mc, Pageable pageable);
    Page<Patient> findByNomContainsOrderById(String mc, Pageable pageable);
}
