package com.ConsigueVentas.TiendaAccesorios.Mapper.Category;

import com.ConsigueVentas.TiendaAccesorios.Dto.Category.CategoryResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Entity.Category;
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
