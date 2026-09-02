package com.ConsigueVentas.TiendaAccesorios.Dto.Category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponseDto {
    private Long id;
    private String name;
}
