package com.ConsigueVentas.TiendaAccesorios.Dto.OrderDetail;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailResponseDto {
    private Long productoId;
    private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subTotal;
}
