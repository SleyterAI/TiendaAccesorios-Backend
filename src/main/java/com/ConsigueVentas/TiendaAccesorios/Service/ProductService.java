package com.ConsigueVentas.TiendaAccesorios.Service;

import com.ConsigueVentas.TiendaAccesorios.Dto.Product.Admin.ProductResponseAdminDto;
import com.ConsigueVentas.TiendaAccesorios.Dto.Product.ProductRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Dto.Product.ProductResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Entity.Product;
import com.ConsigueVentas.TiendaAccesorios.Repository.ProductRepository;
import com.ConsigueVentas.TiendaAccesorios.Service.Interface.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    /*@Override
    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }*/

    @Override
    public Product createProduct(ProductRequestDto productRequestDto) {
        Product product = Product.builder()
                .name(productRequestDto.getName())
                .description(productRequestDto.getDescription())
                .price(productRequestDto.getPrice())
                .stock(productRequestDto.getStock())
                .imageUrl(productRequestDto.getImageUrl())
                .visible(productRequestDto.getVisible())
                .category(productRequestDto.getCategory())
                .build();
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductResponseAdminDto> getAllProductAdmin() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> ProductResponseAdminDto.builder()
                        .id(product.getId())
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
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El producto no existe"));
    }

    @Override
    public Product updateProduct(Long id, ProductRequestDto productRequestDto) {
        Product updateProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El producto no existe"));

        updateProduct.setName(productRequestDto.getName());
        updateProduct.setDescription(productRequestDto.getDescription());
        updateProduct.setPrice(productRequestDto.getPrice());
        updateProduct.setStock(productRequestDto.getStock());
        updateProduct.setImageUrl(productRequestDto.getImageUrl());
        updateProduct.setVisible(productRequestDto.getVisible());
        updateProduct.setCategory(productRequestDto.getCategory() );

        return productRepository.save(updateProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("El producto no existe");
        }
        productRepository.deleteById(id);
    }


    public List<ProductResponseDto> filterProducts(String categoryName, Boolean visible) {
        if (categoryName != null && visible != null) {
            return productRepository.findByCategory_NameAndVisible(categoryName, visible);
        } else if (categoryName != null) {
            return productRepository.findByCategory_Name(categoryName);
        } else if (visible != null) {
            return productRepository.findByVisible(visible);
        }
        List<Product> products = productRepository.findAll();
         return products.stream()
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
    }

    public Product toggleProductVisibility(Long id, Boolean newVisibility) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        product.setVisible(newVisibility);
        return productRepository.save(product);
    }
}
