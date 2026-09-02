package com.ConsigueVentas.TiendaAccesorios.Repository;

import com.ConsigueVentas.TiendaAccesorios.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
