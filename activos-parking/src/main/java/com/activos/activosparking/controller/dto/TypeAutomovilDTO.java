package com.activos.activosparking.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "TypeAutomovilDTO", description = "DTO para TypeAutomovil")
public class TypeAutomovilDTO {
    @Schema(name = "id", description = "Identificador del tipo de automóvil", example = "1")
    private long id;
    @Schema(name = "type", description = "Tipo de automóvil", example = "Sedan")
    private String type;
}
