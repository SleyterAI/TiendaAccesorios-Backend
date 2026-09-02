package com.ConsigueVentas.TiendaAccesorios.Controller;

import com.ConsigueVentas.TiendaAccesorios.Dto.Order.OrderRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Entity.Order;
import com.ConsigueVentas.TiendaAccesorios.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        Order order = orderService.createOrder(orderRequestDto); //verificar si el frontend usa datos del
                                                                //backend para verificar los pedidos
        return ResponseEntity.status(HttpStatus.CREATED).body("Order created");
    }


    @GetMapping
    /*@PreAuthorize("hasRole('ADMIN')")*/
    public ResponseEntity<List<Order>> getAllPedido() {
        return ResponseEntity.ok(orderService.getAllOrder());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> changeStatus(
            @PathVariable Long id, @RequestBody String newStatus) {

        Order order = orderService.changeStatus(id, newStatus);

        return ResponseEntity.ok(order.getStatus());
    }

    /*
    private OrderResponseDto toResponse(Order order){

        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .userName()
                .phoneNumber()
                .address()
                .date()
                .status()
                .total()


        OrderResponseDto orderResponseDto = new OrderResponseDto();
        pedidoResponseDto.setClienteNombre(pedido.getClienteNombre());
        pedidoResponseDto.setCelular(pedido.getCelular());
        pedidoResponseDto.setDireccion(pedido.getDireccion());
        pedidoResponseDto.setFecha(pedido.getFecha());
        pedidoResponseDto.setEstado(pedido.getEstado());
        pedidoResponseDto.setTotal(pedido.getTotal());

        List<DetallePedidoResponseDto> detalles =
                pedido.getDetalles()
                        .stream()
                        .map(detalle -> {
                            DetallePedidoResponseDto detalleResponse =
                                    new DetallePedidoResponseDto();

                            detalleResponse.setProductoId(detalle.getProducto().getId());
                            detalleResponse.setProductoNombre(detalle.getProducto().getNombre());
                            detalleResponse.setCantidad(detalle.getCantidad());
                            detalleResponse.setPrecioUnitario(detalle.getPrecioUnitario());
                            detalleResponse.setSubTotal(detalle.getSubTotal());
                            return detalleResponse;})
                        .toList();
        pedidoResponseDto.setDetalles(detalles);
        return pedidoResponseDto;
    }*/
}
