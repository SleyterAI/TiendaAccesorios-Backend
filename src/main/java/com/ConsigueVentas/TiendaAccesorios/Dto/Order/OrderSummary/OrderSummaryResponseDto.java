package com.ConsigueVentas.TiendaAccesorios.Dto.Order.OrderSummary;

import com.ConsigueVentas.TiendaAccesorios.Dto.OrderDetail.OrderDetailResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class OrderSummaryResponseDto {
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

}
