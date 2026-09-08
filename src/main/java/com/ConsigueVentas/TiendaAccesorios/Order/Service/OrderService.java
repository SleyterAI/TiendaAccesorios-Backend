package com.ConsigueVentas.TiendaAccesorios.Order.Service;

import com.ConsigueVentas.TiendaAccesorios.Cart.Entity.Cart;
import com.ConsigueVentas.TiendaAccesorios.Cart.Entity.CartItem;
import com.ConsigueVentas.TiendaAccesorios.Cart.Repository.CartRepository;
import com.ConsigueVentas.TiendaAccesorios.Order.Dto.OrderRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Order.Dto.OrderResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Order.Dto.OrderSummary.OrderSummaryResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Order.Dto.OrderDetail.OrderDetailRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Order.Entity.Order;
import com.ConsigueVentas.TiendaAccesorios.Order.Entity.OrderDetail;
import com.ConsigueVentas.TiendaAccesorios.Product.Entity.Product;
import com.ConsigueVentas.TiendaAccesorios.Order.Mapper.OrderMapper;
import com.ConsigueVentas.TiendaAccesorios.Order.Repository.OrderRepository;
import com.ConsigueVentas.TiendaAccesorios.Product.Repository.ProductRepository;
import com.ConsigueVentas.TiendaAccesorios.Order.Service.Interface.IOrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;


    @Override
    public OrderResponseDto createOrder(String email, OrderRequestDto orderRequestDto) {

        // save cart from user
        Cart cart = cartRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("No user cart active"));

        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Empty cart the order is not possible");
        }

        Order order = new Order();
        //data form request
        order.setUser(cart.getUser());
        order.setPhoneNumber(orderRequestDto.getPhoneNumber());
        order.setAddress(orderRequestDto.getAddress());

        order.setDate(LocalDate.now());
        order.setStatus("PENDIENTE");

        order.setTotal(cart.getTotalPrice());
        List<OrderDetail> orderDetails = new ArrayList<>();

        //use itemCart for validation n add to orderDetails
        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();
            Integer quantity = cartItem.getQuantity();
            Integer stock = product.getStock();
            BigDecimal unitPrice = product.getPrice();

            if (stock < quantity) {
                throw new IllegalStateException(
                        "Out of stock for product: " + product.getName());
            }

            product.setStock(stock - quantity);
            productRepository.save(product);

            //subtotal
            BigDecimal subtotal = unitPrice.multiply(
                    BigDecimal.valueOf(quantity));

            //add itemCart to orderDetail
            OrderDetail orderDetail = OrderDetail.builder()
                    .quantity(quantity)
                    .unitPrice(unitPrice)
                    .subTotal(subtotal)
                    .product(product)
                    .order(order)
                    .build();

            orderDetails.add(orderDetail);

        }
        order.setOrderDetail(orderDetails);
        //save order in bd
        Order savedOrder = orderRepository.save(order);

        //clear cart after successfully purchase
        cart.getItems().clear();
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cart);

        return orderMapper.toOrderDto(savedOrder);
    }

    @Override
    public List<OrderSummaryResponseDto> getAllOrder() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toOrderSummaryDto)
                .toList();
    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
         Order order = orderRepository.findById(id).
                orElseThrow(() ->
                        new RuntimeException("El pedido no existe")
                );

        return orderMapper.toOrderDto(order);
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        Order updateOrder = orderRepository.findById(id).
                orElseThrow(() ->
                        new RuntimeException("El pedido no existe")
                );
        updateOrder.setUser(order.getUser());
        updateOrder.setPhoneNumber(order.getPhoneNumber());
        updateOrder.setAddress(order.getAddress());
        updateOrder.setDate(order.getDate());
        updateOrder.setStatus(order.getStatus());
        updateOrder.setTotal(order.getTotal());
        return orderRepository.save(updateOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("El pedido no existe");
        }
        orderRepository.deleteById(id);
    }

    public Order changeStatus(Long id, String newStatus) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

}
