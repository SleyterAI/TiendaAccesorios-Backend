package com.ConsigueVentas.TiendaAccesorios.Order.Repository;

import com.ConsigueVentas.TiendaAccesorios.Order.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserEmail(String email);
}
