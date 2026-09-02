package com.ConsigueVentas.TiendaAccesorios.Dto.Product;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class ProductResponseDto {

    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private String imageUrl;
    private Boolean visible;
    private String categoryName;
}
