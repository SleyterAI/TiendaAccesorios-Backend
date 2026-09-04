package com.ConsigueVentas.TiendaAccesorios.Cart.Dto;

import com.ConsigueVentas.TiendaAccesorios.Cart.Dto.CartItem.CartItemRequestDto;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SyncCartRequestDto {
    @NotEmpty(message = "La lista de items no puede estar vacía")
    List<CartItemRequestDto> items;
}
