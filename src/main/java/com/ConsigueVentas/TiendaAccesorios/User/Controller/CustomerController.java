package com.ConsigueVentas.TiendaAccesorios.User.Controller;

import com.ConsigueVentas.TiendaAccesorios.User.Dto.Customer.CustomerRequestDto;
import com.ConsigueVentas.TiendaAccesorios.User.Dto.Customer.CustomerResponseDto;
import com.ConsigueVentas.TiendaAccesorios.User.Entity.Customer;
import com.ConsigueVentas.TiendaAccesorios.User.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/me")
    public ResponseEntity<CustomerResponseDto> getCustomerByUserEmail(
            Authentication authentication) {

        String userEmail = authentication.getName();
        return ResponseEntity.ok(
                customerService.getCustomerByUserEmail(userEmail)
        );
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(
            @Valid @RequestBody CustomerRequestDto customerRequestDto){
        System.out.println("DTO completo: " + customerRequestDto);
        System.out.println("USER ID: " + customerRequestDto.getUserId());
        customerService.createCustomer(customerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Customer created correctly");
    }
}
