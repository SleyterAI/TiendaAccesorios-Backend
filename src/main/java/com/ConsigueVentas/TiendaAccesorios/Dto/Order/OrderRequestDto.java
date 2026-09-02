package com.ConsigueVentas.TiendaAccesorios.Dto.Order;

import com.ConsigueVentas.TiendaAccesorios.Dto.OrderDetail.OrderDetailRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Entity.OrderDetail;
import com.ConsigueVentas.TiendaAccesorios.Entity.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderRequestDto {

    @NotBlank
    private User user;

    @NotBlank
    @Pattern(regexp = "^[0-9]{9}$",
            message = "El celular debe tener 9 dígitos")
    private String phoneNumber;

    @NotBlank
    private String address;

    @NotEmpty(message = "El pedido debe tener al menos un producto")
    private List<@Valid OrderDetailRequestDto> orderDetailRequestDto = new ArrayList<>();
}
