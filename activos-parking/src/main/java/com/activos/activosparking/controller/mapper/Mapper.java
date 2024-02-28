package com.activos.activosparking.controller.mapper;

import com.activos.activosparking.controller.dto.AutomovilDTO;
import com.activos.activosparking.controller.dto.TypeAutomovilDTO;
import com.activos.activosparking.model.Automovil;
import com.activos.activosparking.model.TypeAutomovil;

import java.util.List;

public class Mapper {

    public static TypeAutomovilDTO toTypeAutomovilDTO(TypeAutomovil typeAutomovil) {
        TypeAutomovilDTO typeAutomovilDTO = new TypeAutomovilDTO();
        typeAutomovilDTO.setId(typeAutomovil.getId());
        typeAutomovilDTO.setType(typeAutomovil.getType());
        return typeAutomovilDTO;
    }

    public static TypeAutomovil toTypeAutomovil(TypeAutomovilDTO typeAutomovilDTO) {
        com.activos.activosparking.model.TypeAutomovil typeAutomovil = new com.activos.activosparking.model.TypeAutomovil();
        typeAutomovil.setId(typeAutomovilDTO.getId());
        typeAutomovil.setType(typeAutomovilDTO.getType());
        return typeAutomovil;
    }

    public static Automovil toAutomovil(AutomovilDTO automovilDTO) {
        Automovil automovil = new Automovil();
        automovil.setId(automovilDTO.getId());
        automovil.setPlaca(automovilDTO.getPlaca());
        automovil.setTypeAutomovil(toTypeAutomovil(automovilDTO.getTypeAutomovil()));
        automovil.setFechaIngreso(automovilDTO.getFechaIngreso());
        automovil.setFechaSalida(automovilDTO.getFechaSalida());
        return automovil;
    }

    public static AutomovilDTO toAutomovilDTO(Automovil automovil) {
        AutomovilDTO automovilDTO = new AutomovilDTO();
        automovilDTO.setId(automovil.getId());
        automovilDTO.setPlaca(automovil.getPlaca());
        automovilDTO.setTypeAutomovil(toTypeAutomovilDTO(automovil.getTypeAutomovil()));
        automovilDTO.setFechaIngreso(automovil.getFechaIngreso());
        automovilDTO.setFechaSalida(automovil.getFechaSalida());
        return automovilDTO;
    }


    public static List<AutomovilDTO> toAutomovilDTOList(List<Automovil> automovilList) {
        return automovilList.stream().map(Mapper::toAutomovilDTO).toList();
    }

    public static List<Automovil> toAutomovilList(List<AutomovilDTO> automovilDTOList) {
        return automovilDTOList.stream().map(Mapper::toAutomovil).toList();
    }

    public static List<TypeAutomovilDTO> toTypeAutomovilDTOList(List<TypeAutomovil> typeAutomovilList) {
        return typeAutomovilList.stream().map(Mapper::toTypeAutomovilDTO).toList();
    }

    public static List<TypeAutomovil> toTypeAutomovilList(List<TypeAutomovilDTO> typeAutomovilDTOList) {
        return typeAutomovilDTOList.stream().map(Mapper::toTypeAutomovil).toList();
    }

}
