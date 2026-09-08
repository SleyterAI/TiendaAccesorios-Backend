package com.ConsigueVentas.TiendaAccesorios.Cart.Dto.CartSyncItem;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartSyncItemRequestDto {
    @NotNull(message = "El ID del producto es obligatorio")
    Long productId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad mínima es 1")
    @Max(value = 4, message = "La cantidad máxima permitida por producto es 4")
    Integer quantity;
}




