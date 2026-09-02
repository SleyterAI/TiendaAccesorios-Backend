package com.ConsigueVentas.TiendaAccesorios.Repository;

import com.ConsigueVentas.TiendaAccesorios.Dto.Product.ProductResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<ProductResponseDto> findByCategory_NameAndVisible(String categoryName, Boolean visible);

    List<ProductResponseDto> findByCategory_Name(String categoryName);

    List<ProductResponseDto> findByVisible(Boolean visible);
}
