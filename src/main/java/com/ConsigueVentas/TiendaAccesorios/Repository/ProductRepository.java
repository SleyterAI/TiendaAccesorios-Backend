package com.ConsigueVentas.TiendaAccesorios.Repository;

import com.ConsigueVentas.TiendaAccesorios.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory_NameAndVisible(String categoryName, Boolean visible);

    List<Product> findByCategory_Name(String categoryName);

    List<Product> findByVisible(Boolean visible);
}
