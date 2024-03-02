package com.activos.activosparking.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "AutomovilDTO", description = "DTO para Automovil")
public class AutomovilDTO {
    @Schema(name = "id", description = "Identificador del automóvil", example = "1")
    private long id;
    @Schema(name = "placa", description = "Placa del automóvil", example = "ABC123")
    private String placa;
    @Schema(name = "typeAutomovil", description = "Tipo de automóvil", example = "1")
    private TypeAutomovilDTO typeAutomovil;
    @Schema(name = "fechaIngreso", description = "Fecha de ingreso del automóvil", example = "2021-01-01")
    private LocalDateTime fechaIngreso;
    @Schema(name = "fechaSalida", description = "Fecha de salida del automóvil", example = "2021-01-01")
    private LocalDateTime fechaSalida;
}
