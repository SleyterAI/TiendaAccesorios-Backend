package com.ConsigueVentas.TiendaAccesorios.Cart.Dto.CartItem;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRequestDto {
    @NotNull(message = "El ID del producto es obligatorio")
    Long productId;

}
