package com.ConsigueVentas.TiendaAccesorios.Cart.Mapper;

import com.ConsigueVentas.TiendaAccesorios.Cart.Dto.CartResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Cart.Dto.CartItem.CartItemResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Cart.Entity.Cart;
import com.ConsigueVentas.TiendaAccesorios.Cart.Entity.CartItem;
import com.ConsigueVentas.TiendaAccesorios.Product.Entity.Product;
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