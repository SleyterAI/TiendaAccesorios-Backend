package com.ConsigueVentas.TiendaAccesorios.Product.Dto;

import com.ConsigueVentas.TiendaAccesorios.Category.Entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDto {

    @NotBlank(message = "Nombre no puede estar en blanco")
    private String name;

    @NotBlank(message = "Descripcion no puede estar en blanco")
    private String description;

    @NotNull(message = "Nombre no puede estar en blanco")
    @Positive(message = "El precio debe ser mayor que 0")
    private BigDecimal price;

    @NotNull(message = "Stock no puede estar en blanco")
    @Positive(message = "El stock debe ser mayor que 0")
    private Integer stock;

    @NotBlank(message = "Imagen Url no puede estar en blanco")
    private String imageUrl;

    @NotNull(message = "Activo solo true or false")
    private Boolean visible;

    @NotNull(message = "Categoria no puede estar en blanco")
    private Category category;
}
