package com.ConsigueVentas.TiendaAccesorios.Category.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDto {
    @NotBlank(message = "Nombre no puede estar en blanco")
    private String name;
}
