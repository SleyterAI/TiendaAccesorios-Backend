package com.ConsigueVentas.TiendaAccesorios.Repository;

import com.ConsigueVentas.TiendaAccesorios.Entity.Cart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long>{
    @EntityGraph(attributePaths = {"items", "items.product", "user"})
    Optional<Cart> findByUserEmail(String email);
}
