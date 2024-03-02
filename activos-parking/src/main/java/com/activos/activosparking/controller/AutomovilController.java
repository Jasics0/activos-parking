package com.activos.activosparking.controller;

import com.activos.activosparking.controller.dto.AutomovilDTO;
import com.activos.activosparking.controller.mapper.Mapper;
import com.activos.activosparking.service.AutomovilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/automovil")
@RequiredArgsConstructor
@Tag(name = "AutomovilController", description = "Operaciones relacionadas con automóviles")
public class AutomovilController {

    private final AutomovilService automovilService;

    /**
     * Agrega un automóvil al estacionamiento.
     *
     * @param automovilDTO DTO del automóvil a agregar.
     * @return ResponseEntity con el DTO del automóvil agregado.
     */
    @PostMapping("/ingreso")
    @Operation(summary = "Agregar un automóvil al estacionamiento")
    @ApiResponse(responseCode = "200", description = "Automóvil agregado correctamente")
    @ApiResponse(responseCode = "400", description = "Error en la solicitud")
    public ResponseEntity addAutomovil(@RequestBody AutomovilDTO automovilDTO) {
        try {
            return ResponseEntity.ok(Mapper.toAutomovilDTO(automovilService.addAutomovil(Mapper.toAutomovil(automovilDTO))));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Sacar un automóvil del estacionamiento y calcular el valor a pagar.
     *
     * @param automovilDTO DTO del automóvil a sacar.
     * @return ResponseEntity con el mensaje del valor a pagar.
     */
    @PostMapping("/salida")
    @Operation(summary = "Sacar un automóvil del estacionamiento y calcular el valor a pagar")
    @ApiResponse(responseCode = "200", description = "Automóvil sacado correctamente, valor a pagar calculado")
    @ApiResponse(responseCode = "400", description = "Error en la solicitud")
    public ResponseEntity salidaAutomovil(@RequestBody AutomovilDTO automovilDTO) {
        try {
            AutomovilDTO automovilResponse = Mapper.toAutomovilDTO(automovilService.salidaAutomovil(Mapper.toAutomovil(automovilDTO)));
            double valorPagar = automovilService.calculatePayment(automovilResponse.getId());
            return ResponseEntity.ok("El valor a pagar es: $" + valorPagar);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Actualizar un automóvil existente.
     *
     * @param automovilDTO DTO del automóvil con los datos actualizados.
     * @return ResponseEntity con el DTO del automóvil actualizado.
     */
    @PutMapping()
    @Operation(summary = "Actualizar un automóvil existente")
    @ApiResponse(responseCode = "200", description = "Automóvil actualizado correctamente")
    @ApiResponse(responseCode = "400", description = "Error en la solicitud")
    public ResponseEntity updateAutomovil(@RequestBody AutomovilDTO automovilDTO) {
        try {
            return ResponseEntity.ok(Mapper.toAutomovilDTO(automovilService.updateAutomovil(Mapper.toAutomovil(automovilDTO))));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Eliminar un automóvil por su ID.
     *
     * @param id ID del automóvil a eliminar.
     * @return ResponseEntity con el resultado de la eliminación.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un automóvil por su ID")
    @ApiResponse(responseCode = "200", description = "Automóvil eliminado correctamente")
    @ApiResponse(responseCode = "400", description = "Error en la solicitud")
    public ResponseEntity deleteAutomovil(@PathVariable long id) {
        try {
            automovilService.deleteAutomovil(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Obtener todos los automóviles en el estacionamiento.
     *
     * @return ResponseEntity con la lista de DTOs de automóviles.
     */
    @GetMapping()
    @Operation(summary = "Obtener todos los automóviles en el estacionamiento")
    @ApiResponse(responseCode = "200", description = "Automóviles obtenidos correctamente")
    @ApiResponse(responseCode = "400", description = "Error en la solicitud")
    public ResponseEntity getAllAutomovil() {
        try {
            return ResponseEntity.ok(Mapper.toAutomovilDTOList(automovilService.getAllAutomovil()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
