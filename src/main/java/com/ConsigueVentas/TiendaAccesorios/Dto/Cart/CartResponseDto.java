package com.ConsigueVentas.TiendaAccesorios.Dto.Cart;

import com.ConsigueVentas.TiendaAccesorios.Dto.CartItem.CartItemResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CartResponseDto {
    private Long id;
    private BigDecimal totalPrice;
    private List<CartItemResponseDto> items;
}
