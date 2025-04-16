package com.patient_service.patient_service.repository;

import com.patient_service.patient_service.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    //we need only one email for a patient
    //error-handling-request-business-logic-custom-exception
    boolean existsByEmail(String email);//we want to expose a method to service layer
    boolean existsByEmailAndIdNot(String email, UUID id);
}
