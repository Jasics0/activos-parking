package com.activos.activosparking.service;

import com.activos.activosparking.model.TypeAutomovil;
import com.activos.activosparking.repository.TypeAutomovilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeAutomovilService {

    private final TypeAutomovilRepository typeAutomovilRepository;


    public List<TypeAutomovil> getAllTypeAutomovil() {
        return typeAutomovilRepository.findAll();
    }

    public TypeAutomovil addTypeAutomovil(TypeAutomovil typeAutomovil) {
        return typeAutomovilRepository.save(typeAutomovil);
    }

    public TypeAutomovil updateTypeAutomovil(TypeAutomovil typeAutomovil) {

        TypeAutomovil typeAutomovilToUpdate = typeAutomovilRepository.findById(typeAutomovil.getId()).orElse(null);

        if (typeAutomovilToUpdate == null) {
            throw new RuntimeException("TypeAutomovil not found");
        }

        typeAutomovilToUpdate.setType(typeAutomovil.getType());

        return typeAutomovilRepository.save(typeAutomovilToUpdate);
    }

    public void deleteTypeAutomovil(long id) {
        typeAutomovilRepository.deleteById(id);
    }

}
