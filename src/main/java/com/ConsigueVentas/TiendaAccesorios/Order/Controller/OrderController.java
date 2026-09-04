package com.ConsigueVentas.TiendaAccesorios.Order.Controller;

import com.ConsigueVentas.TiendaAccesorios.Order.Dto.OrderRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Order.Dto.OrderResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Order.Dto.OrderSummary.OrderSummaryResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Order.Entity.Order;
import com.ConsigueVentas.TiendaAccesorios.Order.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<OrderSummaryResponseDto>> getAllOrder() {
        return ResponseEntity.ok(orderService.getAllOrder());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> changeStatus(
            @PathVariable Long id, @RequestBody OrderSummaryResponseDto orderSummary) {

        Order order = orderService.changeStatus(id, orderSummary.getOrder_status());

        return ResponseEntity.ok("Status changed to: "+orderSummary.getOrder_status());
    }
}
