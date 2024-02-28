package com.activos.activosparking.controller;

import com.activos.activosparking.controller.dto.AutomovilDTO;
import com.activos.activosparking.controller.mapper.Mapper;
import com.activos.activosparking.service.AutomovilService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/automovil")
@RequiredArgsConstructor
@Api(value = "AutomovilController", description = "Operaciones relacionadas con automóviles")
public class AutomovilController {

    private final AutomovilService automovilService;

    @PostMapping("/ingreso")
    @ApiOperation(value = "Agregar un automóvil al estacionamiento", response = AutomovilDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Automóvil agregado correctamente"),
            @ApiResponse(code = 400, message = "Error en la solicitud")
    })
    public ResponseEntity addAutomovil(@RequestBody AutomovilDTO automovilDTO) {
        try {
            return ResponseEntity.ok(Mapper.toAutomovilDTO(automovilService.addAutomovil(Mapper.toAutomovil(automovilDTO))));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/salida")
    @ApiOperation(value = "Sacar un automóvil del estacionamiento", response = AutomovilDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Automóvil sacado correctamente"),
            @ApiResponse(code = 400, message = "Error en la solicitud")
    })
    public ResponseEntity salidaAutomovil(@RequestBody AutomovilDTO automovilDTO) {
        try {
            return ResponseEntity.ok(Mapper.toAutomovilDTO(automovilService.salidaAutomovil(Mapper.toAutomovil(automovilDTO))));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping()
    @ApiOperation(value = "Actualizar un automóvil", response = AutomovilDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Automóvil actualizado correctamente"),
            @ApiResponse(code = 400, message = "Error en la solicitud")
    })
    public ResponseEntity updateAutomovil(@RequestBody AutomovilDTO automovilDTO) {
        try {
            return ResponseEntity.ok(Mapper.toAutomovilDTO(automovilService.updateAutomovil(Mapper.toAutomovil(automovilDTO))));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Eliminar un automóvil", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Automóvil eliminado correctamente"),
            @ApiResponse(code = 400, message = "Error en la solicitud")
    })
    public ResponseEntity deleteAutomovil(@PathVariable long id) {
        try {
            automovilService.deleteAutomovil(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    @ApiOperation(value = "Obtener todos los automóviles", response = AutomovilDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Automóviles obtenidos correctamente"),
            @ApiResponse(code = 400, message = "Error en la solicitud")
    })
    public ResponseEntity getAllAutomovil() {
        try {
            return ResponseEntity.ok(Mapper.toAutomovilDTOList(automovilService.getAllAutomovil()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
