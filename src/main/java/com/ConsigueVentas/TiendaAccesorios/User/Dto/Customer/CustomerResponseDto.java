package com.ConsigueVentas.TiendaAccesorios.User.Dto.Customer;

import com.ConsigueVentas.TiendaAccesorios.User.Dto.Card.CardResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class CustomerResponseDto {

    private String name;
    private String lastName;
    private String phoneNumber;
    private String address;
    private CardResponseDto card;
}
