package com.ConsigueVentas.TiendaAccesorios.Order.Dto;

import com.ConsigueVentas.TiendaAccesorios.Order.Dto.OrderDetail.OrderDetailResponseDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class OrderResponseDto {
    //Order
    private Long order_id;
    private String order_phoneNumber;
    private String order_address;
    private LocalDate order_date;
    private String order_status;
    private BigDecimal order_total;


    //User
    private String user_name;
    private String user_email;

    //Order Detail
    private List<OrderDetailResponseDto> orderDetailResponseDto;
}
