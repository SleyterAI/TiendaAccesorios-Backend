package com.ConsigueVentas.TiendaAccesorios.Service;

import com.ConsigueVentas.TiendaAccesorios.Dto.Order.OrderRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Dto.OrderDetail.OrderDetailRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Entity.Order;
import com.ConsigueVentas.TiendaAccesorios.Entity.OrderDetail;
import com.ConsigueVentas.TiendaAccesorios.Entity.Product;
import com.ConsigueVentas.TiendaAccesorios.Repository.OrderRepository;
import com.ConsigueVentas.TiendaAccesorios.Repository.ProductRepository;
import com.ConsigueVentas.TiendaAccesorios.Service.Interface.IOrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    /*@Override
    public Pedido createPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }*/

    @Override
    @Transactional
    public Order createOrder(OrderRequestDto orderRequestDto) {

        Order order = new Order();

        //datos q vienen del request
        order.setUser(orderRequestDto.getUser());
        order.setPhoneNumber(orderRequestDto.getPhoneNumber());
        order.setAddress(orderRequestDto.getAddress());

        //datos backend no request
        order.setDate(LocalDate.now());
        order.setStatus("Pendiente");

        //se crea el total tipo BigDecimal
        BigDecimal total = BigDecimal.ZERO;

        //recorrido de la lista detallePedidoRequestDto que se encuentra en pedidoRequest
        for (OrderDetailRequestDto orderDetailRequestDto : orderRequestDto.getOrderDetailRequestDto()) {

            //guardar producto, validar producto y stock
            Product product = findProductAndValidateStock(orderDetailRequestDto);

            Integer quantity = orderDetailRequestDto.getQuantity();

            //se obtiene el precio por backend de la bd
            BigDecimal unitPrice = product.getPrice();
            //se calcula el subtotal del pedido
            BigDecimal subtotal = unitPrice.multiply(
                    BigDecimal.valueOf(orderDetailRequestDto.getQuantity()));

            //se crea el detallePedido
            OrderDetail orderDetail = OrderDetail.builder()
                    .quantity(orderDetailRequestDto.getQuantity())
                    .unitPrice(unitPrice)
                    .subTotal(subtotal)
                    .product(product)
                    .order(order)
                    .build();


            //se agrega el detallePedido al pedido general
            order.getOrderDetail().add(orderDetail);

            //se calcula el total segun los subtotal
            total = total.add(subtotal);
            // Descontar stock
            product.setStock(product.getStock() - quantity);
        }

        //se añade el total al pedido general
        order.setTotal(total);

        //se guarda el pedido
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).
                orElseThrow(() ->
                        new RuntimeException("El pedido no existe")
                );
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


    private Product findProductAndValidateStock(OrderDetailRequestDto requestDto) {
        Product product = productRepository
                .findById(requestDto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Integer quantity = requestDto.getQuantity();

        if (product.getStock() < quantity || requestDto.getQuantity() == 0) {
            throw new RuntimeException("Stock insuficiente para el producto: " + product.getName()
                    + ". Stock disponible: " + product.getStock() + ", cantidad solicitada: " + quantity);
        }
        return product;
    }
}
