package com.ConsigueVentas.TiendaAccesorios.Category.Service.Interface;

import com.ConsigueVentas.TiendaAccesorios.Category.Dto.CategoryRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Category.Dto.CategoryResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Category.Entity.Category;

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
