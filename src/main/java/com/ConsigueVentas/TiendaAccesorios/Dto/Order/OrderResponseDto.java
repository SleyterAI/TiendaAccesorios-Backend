package com.ConsigueVentas.TiendaAccesorios.Dto.Order;

import com.ConsigueVentas.TiendaAccesorios.Dto.OrderDetail.OrderDetailResponseDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class OrderResponseDto {
/*
    private String userName;
    private String phoneNumber;
    private String address;
    private LocalDate date;
    private String status;
    private BigDecimal total;

    private List<OrderDetailResponseDto> orderDetailResponseDto;*/
    private String message; //order created successfully
}
