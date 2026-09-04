package com.ConsigueVentas.TiendaAccesorios.Order.Repository;

import com.ConsigueVentas.TiendaAccesorios.Order.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
