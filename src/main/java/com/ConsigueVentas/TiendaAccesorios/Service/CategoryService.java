package com.ConsigueVentas.TiendaAccesorios.Service;

import com.ConsigueVentas.TiendaAccesorios.Dto.Category.CategoryRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Dto.Category.CategoryResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Entity.Category;
import com.ConsigueVentas.TiendaAccesorios.Entity.Product;
import com.ConsigueVentas.TiendaAccesorios.Mapper.Category.CategoryMapper;
import com.ConsigueVentas.TiendaAccesorios.Repository.CategoryRepository;
import com.ConsigueVentas.TiendaAccesorios.Service.Interface.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category createCategory(CategoryRequestDto categoryRequestDto) {
        Category category = Category.builder()
                .name(categoryRequestDto.getName())
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public List<CategoryResponseDto> getAllCategory() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toCategoryDto)
                .toList();
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
