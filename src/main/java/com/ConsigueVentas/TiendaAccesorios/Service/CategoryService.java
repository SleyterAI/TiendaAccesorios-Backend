package com.ConsigueVentas.TiendaAccesorios.Service;

import com.ConsigueVentas.TiendaAccesorios.Dto.Category.CategoryRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Entity.Category;
import com.ConsigueVentas.TiendaAccesorios.Repository.CategoryRepository;
import com.ConsigueVentas.TiendaAccesorios.Service.Interface.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CategoryRequestDto categoryRequestDto) {
        return null;
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return null;
    }

    @Override
    public Category updateCategory(Long id, CategoryRequestDto categoryRequestDto) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {
    }
}
