package com.ConsigueVentas.TiendaAccesorios.User.Dto.Customer;

import com.ConsigueVentas.TiendaAccesorios.User.Dto.Card.CardRequestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequestDto {

    @NotBlank(message = "Name can't be blank")
    private String name;

    @NotBlank(message = "Last name can't be blank")
    private String lastName;

    @NotBlank(message = "Phone number can't be blank")
    private String phoneNumber;

    @NotBlank(message = "Address can't be blank")
    private String address;

    @NotNull(message = "Card can't be null")
    private CardRequestDto card;
}

