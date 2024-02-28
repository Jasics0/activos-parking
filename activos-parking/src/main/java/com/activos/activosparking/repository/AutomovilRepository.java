package com.activos.activosparking.repository;

import com.activos.activosparking.model.Automovil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AutomovilRepository extends CrudRepository<Automovil, Long> {
    List<Automovil> findByTypeAutomovilId(long id);
    List<Automovil> findByFechaIngreso(LocalDate fechaIngreso);
    List<Automovil> findByFechaSalida(LocalDate fechaSalida);
    List<Automovil> findByTypeAutomovilType(String type);

    List<Automovil> findAll();

    Optional<Automovil> findByPlacaAndFechaSalidaIsNull(String placa);
}
