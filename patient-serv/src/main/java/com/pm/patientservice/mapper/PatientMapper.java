package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

public class PatientMapper {
    public  static PatientResponseDTO toDTO(Patient patient){

        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        patientResponseDTO.setId(patient.getId().toString());
        patientResponseDTO.setAddress(patient.getAddress());
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        patientResponseDTO.setEmail(patient.getEmail());
        return patientResponseDTO;

    }
    public static Patient toModel(PatientRequestDTO dto){
        Patient patient =new Patient();
        patient.setAddress(dto.getAddress());
        patient.setEmail(dto.getEmail());
        patient.setName(dto.getName());
        patient.setRegisteredDate(LocalDate.parse(dto.getRegisteredDate()));
        patient.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
        return patient;
    }
}
