package com.ConsigueVentas.TiendaAccesorios.Mapper.Order;

import com.ConsigueVentas.TiendaAccesorios.Dto.Order.OrderResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Dto.Order.OrderSummary.OrderSummaryResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Dto.OrderDetail.OrderDetailResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Entity.Order;
import com.ConsigueVentas.TiendaAccesorios.Entity.OrderDetail;
import com.ConsigueVentas.TiendaAccesorios.Entity.Product;
import com.ConsigueVentas.TiendaAccesorios.Entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class OrderMapper {

    //method for getAllOrder
    //returns -> full summary order
    public OrderSummaryResponseDto toOrderSummaryDto(Order order) {
        User user = order.getUser();
        return OrderSummaryResponseDto.builder()
                // Order
                .order_id(order.getId())
                .order_phoneNumber(order.getPhoneNumber())
                .order_address(order.getAddress())
                .order_date(order.getDate())
                .order_status(order.getStatus())
                .order_total(order.getTotal())

                // User
                .user_name(user.getUsername())
                .user_email(user.getEmail())
                .build();
    }

    // 2 methods for getOrderById
    //returns -> full order + orderDetail
    public OrderResponseDto toOrderDto(Order order) {
        User user = order.getUser();
        return OrderResponseDto.builder()

                // Order
                .order_id(order.getId())
                .order_phoneNumber(order.getPhoneNumber())
                .order_address(order.getAddress())
                .order_date(order.getDate())
                .order_status(order.getStatus())
                .order_total(order.getTotal())

                // User
                .user_name(user.getUsername())
                .user_email(user.getEmail())

                // OrderDetails

                .orderDetailResponseDto(
                        order.getOrderDetail()
                                .stream()
                                .map(this::toOrderDetailDto)
                                .toList()
                )

                .build();
    }

    private OrderDetailResponseDto toOrderDetailDto(OrderDetail orderDetail) {

        Product product = orderDetail.getProduct();
        Order order = orderDetail.getOrder();
        User user = orderDetail.getOrder().getUser();

        return OrderDetailResponseDto.builder()

                // OrderDetail
                .orderDetail_quantity(orderDetail.getQuantity())
                .orderDetail_unitPrice(orderDetail.getUnitPrice())
                .orderDetail_subTotal(orderDetail.getSubTotal())

                // Product
                .product_id(product.getId())
                .product_name(product.getName())
                .product_imageUrl(product.getImageUrl())

                // Category
                .category_name(orderDetail.getProduct().getCategory().getName())

                //User
                .user_name(user.getUsername())
                .user_email(user.getEmail())

                //Order
                .order_address(order.getAddress())
                .order_phoneNumber(order.getPhoneNumber())
                .order_date(order.getDate())
                .order_status(order.getStatus())
                .order_total(order.getTotal())

                .build();
    }

    /* metodo unido
    public OrderResponseDto mapToDto(Order order) {
        List<OrderDetailResponseDto> details = order.getOrderDetail()
                .stream() .map(
                        detail -> OrderDetailResponseDto.builder()
                        // OrderDetail
                        .orderDetail_quantity(detail.getQuantity())
                                .orderDetail_unitPrice(detail.getUnitPrice())
                                .orderDetail_subTotal(detail.getSubTotal())
                        // Product
                        .product_id(detail.getProduct().getId())
                                .product_name(detail.getProduct().getName())
                                .product_imageUrl(detail.getProduct().getImageUrl())
                        //Category
                        .category_name(detail.getProduct().getCategory().getName())
                        //User
                        .user_name(detail.getOrder().getUser().getUsername())
                                .user_email(detail.getOrder().getUser().getEmail())
                        //Order
                        .order_address(detail.getOrder().getAddress())
                                .order_phoneNumber(detail.getOrder().getPhoneNumber())
                                .order_date(detail.getOrder().getDate())
                                .order_status(detail.getOrder().getStatus())
                                .order_total(detail.getOrder().getTotal())
                                .build())
                .toList();
        return OrderResponseDto.builder()
        //Order
        .order_id(order.getId())
                .order_phoneNumber(order.getPhoneNumber())
                .order_address(order.getAddress())
                .order_date(order.getDate())
                .order_status(order.getStatus())
                .order_total(order.getTotal())
        // User
        .user_name(order.getUser().getUsername())
                .user_email(order.getUser().getEmail())
        // Order Detail
        .orderDetailResponseDto(details)
                .build();
    }
     */
}
