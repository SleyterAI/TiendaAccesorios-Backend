package com.ConsigueVentas.TiendaAccesorios.Controller;

import com.ConsigueVentas.TiendaAccesorios.Dto.Cart.CartResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Dto.Cart.SyncCartRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Dto.CartItem.CartItemRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartResponseDto> getCart(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(cartService.getCartByUserEmail(userDetails.getUsername()));
    }

    @PostMapping("/item")
    public ResponseEntity<CartResponseDto> addOrUpdateItem(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody CartItemRequestDto request) {
        return ResponseEntity.ok(cartService.addOrUpdateItem(userDetails.getUsername(), request));
    }

    @PutMapping("/sync")
    public ResponseEntity<CartResponseDto> syncCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody SyncCartRequestDto request) {
        return ResponseEntity.ok(cartService.syncCart(userDetails.getUsername(), request.getItems()));
    }

    @DeleteMapping("/item/{productId}")
    public ResponseEntity<CartResponseDto> removeItem(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long productId) {
        return ResponseEntity.ok(cartService.removeItem(userDetails.getUsername(), productId));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<CartResponseDto> clearCart(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(cartService.clearCart(userDetails.getUsername()));
    }
}
