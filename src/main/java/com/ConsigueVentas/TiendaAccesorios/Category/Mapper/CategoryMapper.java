package com.ConsigueVentas.TiendaAccesorios.Category.Mapper;

import com.ConsigueVentas.TiendaAccesorios.Category.Dto.CategoryResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Category.Entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponseDto toCategoryDto(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

}
