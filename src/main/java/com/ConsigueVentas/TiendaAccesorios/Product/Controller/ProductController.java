package com.ConsigueVentas.TiendaAccesorios.Product.Controller;

import com.ConsigueVentas.TiendaAccesorios.Product.Dto.Admin.ProductResponseAdminDto;
import com.ConsigueVentas.TiendaAccesorios.Product.Dto.ProductRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Product.Dto.ProductResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Product.Entity.Product;
import com.ConsigueVentas.TiendaAccesorios.Product.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(productRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProduct(
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) Boolean visible) {
        return ResponseEntity.ok(productService.filterProducts(categoryName, visible));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/admin")
    public ResponseEntity<List<ProductResponseAdminDto>> getProductAdmin() {
        return ResponseEntity.ok(productService.getAllProductAdmin());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id, @Valid @RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(productService.updateProduct(id, productRequestDto));
    }

    @PatchMapping("/{id}/visible")
    public ResponseEntity<String> toggleProductVisibility(@PathVariable Long id, @RequestBody Product newVisible) {
        Product product = productService.toggleProductVisibility(id, newVisible.getVisible());
        return ResponseEntity.ok("Product Id: "+product.getId()+", visible now is: "+product.getVisible());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
