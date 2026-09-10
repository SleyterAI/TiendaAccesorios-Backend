package com.ConsigueVentas.TiendaAccesorios.User.Dto.Customer;

import com.ConsigueVentas.TiendaAccesorios.User.Dto.Card.CardRequestDto;
import com.ConsigueVentas.TiendaAccesorios.User.Entity.Card;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDto {
    @NotBlank(message = "Name cant be blank")
    private String name;

    @NotBlank(message = "Name cant be blank")
    private String lastName;

    @NotBlank(message = "Name cant be blank")
    private String phoneNumber;

    @NotBlank(message = "Name cant be blank")
    private String address;

    @NotNull(message = "Name cant be blank")
    private CardRequestDto card;

    @NotNull(message = "User ID can't be null")
    private Long userId;
}
