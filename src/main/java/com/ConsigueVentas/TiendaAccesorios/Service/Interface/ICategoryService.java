package com.ConsigueVentas.TiendaAccesorios.Service.Interface;

import com.ConsigueVentas.TiendaAccesorios.Dto.Category.CategoryRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Dto.Category.CategoryResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Entity.Category;

import java.util.List;

public interface ICategoryService {
    //Create
    Category createCategory(CategoryRequestDto categoryRequestDto);

    //Read
    List<CategoryResponseDto> getAllCategory();
    Category getCategoryById(Long id);

    //Update
    Category updateCategory(Long id, CategoryRequestDto categoryRequestDto);

    //Delete
    void deleteCategory(Long id);
}
