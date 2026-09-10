package com.ConsigueVentas.TiendaAccesorios.User.Dto.Card;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardRequestDto {

    @NotBlank(message = "Card number cant be blank")
    private String cardNumber;

    @NotBlank(message = "expiration date cant be blank")
    private LocalDate expirationDate;
}
