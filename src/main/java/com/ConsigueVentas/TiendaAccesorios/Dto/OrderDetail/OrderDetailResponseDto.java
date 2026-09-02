package com.ConsigueVentas.TiendaAccesorios.Dto.OrderDetail;

import com.ConsigueVentas.TiendaAccesorios.Dto.Order.OrderResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Dto.Product.ProductResponseDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class OrderDetailResponseDto {

    //Order Detail
    private Integer orderDetail_quantity;
    private BigDecimal orderDetail_unitPrice;
    private BigDecimal orderDetail_subTotal;

    //Product
    private Long product_id;
    private String product_name;
    private String product_imageUrl;

    //Category
    private String category_name;

    //User
    private String user_name;
    private String user_email;

    //Order
    private String order_phoneNumber;
    private String order_address;
    private LocalDate order_date;
    private String order_status;
    private BigDecimal order_total;
}
