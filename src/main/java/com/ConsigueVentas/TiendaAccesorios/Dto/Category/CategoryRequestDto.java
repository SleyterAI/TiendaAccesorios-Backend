package com.ConsigueVentas.TiendaAccesorios.Dto.Category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDto {
    @NotBlank(message = "Nombre no puede estar en blanco")
    private String name;
}
