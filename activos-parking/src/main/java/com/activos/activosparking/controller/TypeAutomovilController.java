package com.activos.activosparking.controller;

import com.activos.activosparking.controller.dto.TypeAutomovilDTO;
import com.activos.activosparking.controller.mapper.Mapper;
import com.activos.activosparking.service.TypeAutomovilService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typeAutomovil")
@RequiredArgsConstructor
@Api(value = "TypeAutomovilController", description = "Operaciones relacionadas con tipos de automóviles")

public class TypeAutomovilController {

    private final TypeAutomovilService typeAutomovilService;


    @GetMapping()
    @ApiOperation(value = "Obtener todos los tipos de automóviles", response = TypeAutomovilDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tipos de automóviles obtenidos correctamente"),
            @ApiResponse(code = 400, message = "Error en la solicitud")
    })
    public ResponseEntity getAllTypeAutomovil() {
        try {
            List<TypeAutomovilDTO> typeAutomovilDTOList = Mapper.toTypeAutomovilDTOList(typeAutomovilService.getAllTypeAutomovil());
            return ResponseEntity.ok(typeAutomovilDTOList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping()
    @ApiOperation(value = "Agregar un tipo de automóvil", response = TypeAutomovilDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tipo de automóvil agregado correctamente"),
            @ApiResponse(code = 400, message = "Error en la solicitud")
    })
    public ResponseEntity addTypeAutomovil(@RequestBody TypeAutomovilDTO typeAutomovilDTO) {
        try {
            return ResponseEntity.ok(Mapper.toTypeAutomovilDTO(typeAutomovilService.addTypeAutomovil(Mapper.toTypeAutomovil(typeAutomovilDTO))));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping()
    @ApiOperation(value = "Actualizar un tipo de automóvil", response = TypeAutomovilDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tipo de automóvil actualizado correctamente"),
            @ApiResponse(code = 400, message = "Error en la solicitud")
    })
    public ResponseEntity updateTypeAutomovil(@RequestBody TypeAutomovilDTO typeAutomovilDTO) {
        try {
            return ResponseEntity.ok(Mapper.toTypeAutomovilDTO(typeAutomovilService.updateTypeAutomovil(Mapper.toTypeAutomovil(typeAutomovilDTO))));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Eliminar un tipo de automóvil", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tipo de automóvil eliminado correctamente"),
            @ApiResponse(code = 400, message = "Error en la solicitud")
    })
    public ResponseEntity deleteTypeAutomovil(@PathVariable long id) {
        try {
            typeAutomovilService.deleteTypeAutomovil(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
