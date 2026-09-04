package com.ConsigueVentas.TiendaAccesorios.Service.Interface;

import com.ConsigueVentas.TiendaAccesorios.Dto.Cart.CartResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Dto.CartItem.CartItemRequestDto;

import java.util.List;

public interface ICartService {

    //create
    CartResponseDto addOrUpdateItem(String email, CartItemRequestDto request);
    CartResponseDto syncCart(String email, List<CartItemRequestDto> items);

    //read
    CartResponseDto getCartByUserEmail(String email);

    //update


    //delete
    CartResponseDto clearCart(String email);
    CartResponseDto removeItem(String email, Long productId);
}
