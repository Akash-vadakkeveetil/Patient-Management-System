package com.patient_service.patient_service.service;


import com.patient_service.patient_service.dto.PatientRequestDTO;
import com.patient_service.patient_service.dto.PatientResponseDTO;
import com.patient_service.patient_service.exception.EmailAlreadyExistsException;
import com.patient_service.patient_service.exception.PatientNotFoundException;
import com.patient_service.patient_service.mapper.PatientMapper;
import com.patient_service.patient_service.model.Patient;
import com.patient_service.patient_service.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    //dependency injection
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients (){
        List<Patient> patients = patientRepository.findAll();
        //now we have to covert the above entity object "patients" to DTO object
        //we creating the mapper package here for that


        List<PatientResponseDTO> patientResponseDTOs =
                patients.stream().map(patient -> PatientMapper.toDTO(patient)).toList();

        return patientResponseDTOs;
    }


    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){

        //if already an email exist then we cant add patient and hence throw exception
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail()))
            throw new EmailAlreadyExistsException("A patient with this email already exist" + patientRequestDTO.getEmail());

        //we cannot pass our patientRequestDTO model to the save directly cause it may cause the
        //type compatibility issue is gonna arise so we have to convert the DTO to instance object

        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toDTO(newPatient);
    }


    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO){

        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));

        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id))
            throw new EmailAlreadyExistsException("A patient with this email already exist" + patientRequestDTO.getEmail());

        //a small problem in the above email checking code is that when we are trying to update it a user
        //which already exists , the spring considers it as already existing mail and throws error
        //hence we need write a new method in jpa to ignore this


        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);
    }

    public void deletePatient(UUID id){
        patientRepository.deleteById(id);
    }

}
