package com.ConsigueVentas.TiendaAccesorios.Product.Dto.Admin;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductResponseAdminDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private String imageUrl;
    private Boolean visible;
    private String categoryName;
}
