package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){

        this.patientRepository=patientRepository;

    }

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patientList = patientRepository.findAll();
        return  patientList.stream().map(PatientMapper::toDTO).collect(Collectors.toList());


    }
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("A patient with email "+patientRequestDTO.getEmail()+ " already exists");
        }
        return PatientMapper.toDTO(patientRepository.save(PatientMapper.toModel(patientRequestDTO)));
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO){
        if(!patientRepository.existsById(id)){
            throw new PatientNotFoundException("Patient Not found with ID"+id);
        }
        Patient patient=patientRepository.findById(id).orElseThrow(()->new PatientNotFoundException("Patient Not found with ID"+id));
        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id) ){
            throw new EmailAlreadyExistsException("A patient with email "+patientRequestDTO.getEmail()+ " already exists");
        }
        patient.setName(patientRequestDTO.getName());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        Patient updatedPatient =patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);


    }
    public void deletePatient(UUID id){
        patientRepository.deleteById(id);
    }


}
