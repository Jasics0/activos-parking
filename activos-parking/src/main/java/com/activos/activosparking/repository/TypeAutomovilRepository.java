package com.activos.activosparking.repository;

import com.activos.activosparking.model.TypeAutomovil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeAutomovilRepository extends CrudRepository<TypeAutomovil, Long> {

    List<TypeAutomovil> findAll();
}
