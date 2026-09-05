package com.ConsigueVentas.TiendaAccesorios.Order.Dto;

import com.ConsigueVentas.TiendaAccesorios.Order.Dto.OrderDetail.OrderDetailRequestDto;
import com.ConsigueVentas.TiendaAccesorios.User.Entity.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderRequestDto {

    /*
    @NotNull(message = "User mandatory")
    private User user;*/

    @NotBlank
    @Pattern(regexp = "^[0-9]{9}$",
            message = "Phone number must have 9 digits")
    private String phoneNumber;

    @NotBlank
    private String address;

    /*
    @NotEmpty(message = "Order detail request dto is missing")
    private List<@Valid OrderDetailRequestDto> orderDetailRequestDto = new ArrayList<>();*/
}
