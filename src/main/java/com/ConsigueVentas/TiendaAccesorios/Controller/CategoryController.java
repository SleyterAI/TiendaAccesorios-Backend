package com.ConsigueVentas.TiendaAccesorios.Controller;

import com.ConsigueVentas.TiendaAccesorios.Dto.Category.CategoryRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Dto.Category.CategoryResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Entity.Category;
import com.ConsigueVentas.TiendaAccesorios.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.createCategory(categoryRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategoria() {

        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Long id, @RequestBody CategoryRequestDto categoriaRequestDto) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoriaRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
