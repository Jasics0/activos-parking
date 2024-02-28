package com.activos.activosparking.service;

import com.activos.activosparking.model.Automovil;
import com.activos.activosparking.repository.AutomovilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AutomovilService {

    private final AutomovilRepository automovilRepository;

    public void deleteAutomovil(long id) {
        automovilRepository.deleteById(id);
    }

    public List<Automovil> getAllAutomovil() {
        return automovilRepository.findAll();
    }

    public Automovil addAutomovil(Automovil automovil) {
        automovil.setFechaIngreso(LocalDate.now());
        return automovilRepository.save(automovil);
    }

    public Automovil updateAutomovil(Automovil automovil) {
        Automovil automovilToUpdate = automovilRepository.findById(automovil.getId()).orElse(null);
        if (automovilToUpdate == null) {
            throw new RuntimeException("Automovil not found");
        }
        automovilToUpdate.setPlaca(automovil.getPlaca());
        automovilToUpdate.setFechaIngreso(automovil.getFechaIngreso());
        automovilToUpdate.setFechaSalida(automovil.getFechaSalida());
        automovilToUpdate.setTypeAutomovil(automovil.getTypeAutomovil());
        return automovilRepository.save(automovilToUpdate);
    }

    public List<Automovil> findByTypeAutomovilId(long id) {
        return automovilRepository.findByTypeAutomovilId(id);
    }

    public List<Automovil> findByFechaIngreso(LocalDate fechaIngreso) {
        return automovilRepository.findByFechaIngreso(fechaIngreso);
    }

    public List<Automovil> findByFechaSalida(LocalDate fechaSalida) {
        return automovilRepository.findByFechaSalida(fechaSalida);
    }

    public List<Automovil> findByTypeAutomovilType(String type) {
        return automovilRepository.findByTypeAutomovilType(type);
    }

    public Automovil salidaAutomovil(Automovil automovil) {
        Automovil automovilToUpdate = automovilRepository.findByPlacaAndFechaSalidaIsNull(automovil.getPlaca()).orElse(null);
        if (automovilToUpdate == null) {
            throw new RuntimeException("Automovil not found");
        }
        automovilToUpdate.setFechaSalida(LocalDate.now());
        return automovilRepository.save(automovilToUpdate);
    }

}
