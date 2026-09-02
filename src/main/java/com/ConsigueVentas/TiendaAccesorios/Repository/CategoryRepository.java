package com.ConsigueVentas.TiendaAccesorios.Repository;

import com.ConsigueVentas.TiendaAccesorios.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
