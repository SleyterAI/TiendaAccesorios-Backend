package com.ConsigueVentas.TiendaAccesorios.Cart.Controller;

import com.ConsigueVentas.TiendaAccesorios.Cart.Dto.CartResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Cart.Dto.SyncCartRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Cart.Dto.CartItem.CartItemRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Cart.Service.CartService;
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

    @PostMapping("/item")
    public ResponseEntity<CartResponseDto> addOrUpdateItem(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody CartItemRequestDto request) {
        return ResponseEntity.ok(cartService.addOrUpdateItem(userDetails.getUsername(), request));
    }

    @GetMapping
    public ResponseEntity<CartResponseDto> getCart(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(cartService.getCartByUserEmail(userDetails.getUsername()));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<CartResponseDto> clearCart(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(cartService.clearCart(userDetails.getUsername()));
    }

    @DeleteMapping("/item/{productId}")
    public ResponseEntity<CartResponseDto> removeItem(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long productId) {
        return ResponseEntity.ok(cartService.removeItem(userDetails.getUsername(), productId));
    }

    @PutMapping("/sync")
    public ResponseEntity<CartResponseDto> syncCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody SyncCartRequestDto request) {
        return ResponseEntity.ok(cartService.syncCart(userDetails.getUsername(), request.getItems()));
    }

    @PatchMapping("/item/{productId}/decrease")
    public ResponseEntity<CartResponseDto> decreaseItem(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long productId) {
        return ResponseEntity.ok(cartService.decreaseItem(userDetails.getUsername(), productId));
    }
}
