package com.ConsigueVentas.TiendaAccesorios.Controller;

import com.ConsigueVentas.TiendaAccesorios.Dto.Product.ProductRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Dto.Product.ProductResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Entity.Product;
import com.ConsigueVentas.TiendaAccesorios.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
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
        List<Product> products = productService.filterProducts(categoryName, visible);
        List<ProductResponseDto> response = products.stream()
                .map(product -> ProductResponseDto.builder()
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .stock(product.getStock())
                        .imageUrl(product.getImageUrl())
                        .visible(product.getVisible())
                        .categoryName(product.getCategory().getName())
                        .build()
                )
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/admin")
    public ResponseEntity<List<Product>> getProductAdmin() {
        return ResponseEntity.ok(productService.getAllProductAdmin());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id, @Valid @RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(productService.updateProduct(id, productRequestDto));
    }

    @PatchMapping("/{id}/visible")
    public ResponseEntity<Product> changeVisible(@PathVariable Long id, @RequestBody Product newVisible) {
        Product product = productService.toggleProductVisibility(id, newVisible.getVisible());
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
