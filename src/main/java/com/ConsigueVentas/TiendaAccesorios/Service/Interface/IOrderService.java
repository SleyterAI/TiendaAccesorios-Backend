package com.ConsigueVentas.TiendaAccesorios.Service.Interface;

import com.ConsigueVentas.TiendaAccesorios.Dto.Order.OrderRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Entity.Order;

import java.util.List;

public interface IOrderService {
    //Create
    Order createOrder(OrderRequestDto orderRequestDto);

    //Read
    List<Order> getAllOrder();
    Order getOrderById(Long id);

    //Update
    Order updateOrder(Long id, Order order);

    //Delete
    void deleteOrder(Long id);
}
