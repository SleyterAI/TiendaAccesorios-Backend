package com.ConsigueVentas.TiendaAccesorios.Mapper.Cart;

import com.ConsigueVentas.TiendaAccesorios.Dto.Cart.CartResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Dto.CartItem.CartItemResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Entity.Cart;
import com.ConsigueVentas.TiendaAccesorios.Entity.CartItem;
import com.ConsigueVentas.TiendaAccesorios.Entity.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CartMapper {

    public CartResponseDto toDto(Cart cart) {
        if (cart == null) {
            return null;
        }

        List<CartItemResponseDto> itemDtos = cart.getItems().stream()
                .map(this::toItemDto)
                .toList();

        return new CartResponseDto(
                cart.getId(),
                cart.getTotalPrice(),
                itemDtos
        );
    }

    public CartItemResponseDto toItemDto(CartItem item) {
        Product product = item.getProduct();

        BigDecimal subTotal = product.getPrice()
                .multiply(BigDecimal.valueOf(item.getQuantity()));

        return new CartItemResponseDto(
                item.getId(),
                product.getId(),
                product.getName(),
                product.getImageUrl(),
                product.getPrice(),
                item.getQuantity(),
                subTotal
        );
    }
}