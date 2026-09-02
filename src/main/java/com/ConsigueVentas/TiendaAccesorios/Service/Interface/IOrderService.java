package com.ConsigueVentas.TiendaAccesorios.Service.Interface;

import com.ConsigueVentas.TiendaAccesorios.Dto.Order.OrderRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Dto.Order.OrderResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Dto.Order.OrderSummary.OrderSummaryResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Entity.Order;

import java.util.List;

public interface IOrderService {
    //Create
    Order createOrder(OrderRequestDto orderRequestDto);

    //Read
    List<OrderSummaryResponseDto> getAllOrder();
    OrderResponseDto getOrderById(Long id);

    //Update
    Order updateOrder(Long id, Order order);

    //Delete
    void deleteOrder(Long id);
}
