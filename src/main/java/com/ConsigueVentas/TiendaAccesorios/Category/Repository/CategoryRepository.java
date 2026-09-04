package com.ConsigueVentas.TiendaAccesorios.Category.Repository;

import com.ConsigueVentas.TiendaAccesorios.Category.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
