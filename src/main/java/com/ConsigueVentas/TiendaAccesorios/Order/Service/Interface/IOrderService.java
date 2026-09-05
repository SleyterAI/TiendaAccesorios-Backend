package com.ConsigueVentas.TiendaAccesorios.Order.Service.Interface;

import com.ConsigueVentas.TiendaAccesorios.Order.Dto.OrderRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Order.Dto.OrderResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Order.Dto.OrderSummary.OrderSummaryResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Order.Entity.Order;

import java.util.List;

public interface IOrderService {
    //Create
    OrderResponseDto createOrder(String email, OrderRequestDto orderRequestDto);

    //Read
    List<OrderSummaryResponseDto> getAllOrder();
    OrderResponseDto getOrderById(Long id);

    //Update
    Order updateOrder(Long id, Order order);

    //Delete
    void deleteOrder(Long id);
}
