package com.activos.activosparking.controller;

import com.activos.activosparking.controller.dto.TypeAutomovilDTO;
import com.activos.activosparking.controller.mapper.Mapper;
import com.activos.activosparking.service.TypeAutomovilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typeAutomovil")
@RequiredArgsConstructor
@Tag(name = "TypeAutomovilController", description = "Operaciones relacionadas con tipos de automóviles")
public class TypeAutomovilController {

    private final TypeAutomovilService typeAutomovilService;

    /**
     * Obtener todos los tipos de automóviles.
     *
     * @return ResponseEntity con la lista de DTOs de tipos de automóviles.
     */
    @GetMapping()
    @Operation(summary = "Obtener todos los tipos de automóviles", responses = {
            @ApiResponse(responseCode = "200", description = "Tipos de automóviles obtenidos correctamente"),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud")
    })
    public ResponseEntity getAllTypeAutomovil() {
        try {
            List<TypeAutomovilDTO> typeAutomovilDTOList = Mapper.toTypeAutomovilDTOList(typeAutomovilService.getAllTypeAutomovil());
            return ResponseEntity.ok(typeAutomovilDTOList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Agregar un tipo de automóvil.
     *
     * @param typeAutomovilDTO DTO del tipo de automóvil a agregar.
     * @return ResponseEntity con el DTO del tipo de automóvil agregado.
     */
    @PostMapping()
    @Operation(summary = "Agregar un tipo de automóvil", responses = {
            @ApiResponse(responseCode = "200", description = "Tipo de automóvil agregado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud")
    })
    public ResponseEntity addTypeAutomovil(@RequestBody TypeAutomovilDTO typeAutomovilDTO) {
        try {
            return ResponseEntity.ok(Mapper.toTypeAutomovilDTO(typeAutomovilService.addTypeAutomovil(Mapper.toTypeAutomovil(typeAutomovilDTO))));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Actualizar un tipo de automóvil existente.
     *
     * @param typeAutomovilDTO DTO del tipo de automóvil con los datos actualizados.
     * @return ResponseEntity con el DTO del tipo de automóvil actualizado.
     */
    @PutMapping()
    @Operation(summary = "Actualizar un tipo de automóvil", responses = {
            @ApiResponse(responseCode = "200", description = "Tipo de automóvil actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud")
    })
    public ResponseEntity updateTypeAutomovil(@RequestBody TypeAutomovilDTO typeAutomovilDTO) {
        try {
            return ResponseEntity.ok(Mapper.toTypeAutomovilDTO(typeAutomovilService.updateTypeAutomovil(Mapper.toTypeAutomovil(typeAutomovilDTO))));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Eliminar un tipo de automóvil por su ID.
     *
     * @param id ID del tipo de automóvil a eliminar.
     * @return ResponseEntity con el resultado de la eliminación.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un tipo de automóvil por su ID", responses = {
            @ApiResponse(responseCode = "200", description = "Tipo de automóvil eliminado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud")
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
