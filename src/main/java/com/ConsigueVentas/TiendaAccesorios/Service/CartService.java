package com.ConsigueVentas.TiendaAccesorios.Service;

import com.ConsigueVentas.TiendaAccesorios.Dto.Cart.CartResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Dto.CartItem.CartItemRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Dto.CartItem.CartItemResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Entity.Cart;
import com.ConsigueVentas.TiendaAccesorios.Entity.CartItem;
import com.ConsigueVentas.TiendaAccesorios.Entity.Product;
import com.ConsigueVentas.TiendaAccesorios.Entity.User;
import com.ConsigueVentas.TiendaAccesorios.Mapper.Cart.CartMapper;
import com.ConsigueVentas.TiendaAccesorios.Repository.CartRepository;
import com.ConsigueVentas.TiendaAccesorios.Repository.ProductRepository;
import com.ConsigueVentas.TiendaAccesorios.Repository.UserRepository;
import com.ConsigueVentas.TiendaAccesorios.Service.Interface.ICartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    @Override
    public CartResponseDto addOrUpdateItem(String email, CartItemRequestDto request) {
        Cart cart = getOrCreateCart(email);
        Product product = findProductOrThrow(request.getProductId());

        validateStockAndLimit(product, request.getQuantity());

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            int newQuantity = existingItem.get().getQuantity() + request.getQuantity();
            validateStockAndLimit(product, newQuantity);
            existingItem.get().setQuantity(newQuantity);
        } else {
            CartItem newItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(request.getQuantity())
                    .build();
            cart.getItems().add(newItem);
        }

        calculateTotals(cart);
        return cartMapper.toDto(cartRepository.save(cart));
    }

    @Override
    public CartResponseDto syncCart(String email, List<CartItemRequestDto> itemsDto) {
        Cart cart = getOrCreateCart(email);
        cart.getItems().clear(); // Limpia los anteriores para sincronizar la lista completa enviada desde Angular

        for (CartItemRequestDto dto : itemsDto) {
            Product product = findProductOrThrow(dto.getProductId());
            validateStockAndLimit(product, dto.getQuantity());

            CartItem newItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(dto.getQuantity())
                    .build();
            cart.getItems().add(newItem);
        }

        calculateTotals(cart);
        return cartMapper.toDto(cartRepository.save(cart));
    }

    @Override
    public CartResponseDto getCartByUserEmail(String email) {
        Cart cart = getOrCreateCart(email);
        return cartMapper.toDto(cart);
    }

    @Override
    public CartResponseDto clearCart(String email) {
        Cart cart = getOrCreateCart(email);
        cart.getItems().clear();
        cart.setTotalPrice(BigDecimal.ZERO);
        return cartMapper.toDto(cartRepository.save(cart));
    }

    @Override
    public CartResponseDto removeItem(String email, Long productId) {
        Cart cart = getOrCreateCart(email);
        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));

        calculateTotals(cart);
        return cartMapper.toDto(cartRepository.save(cart));
    }

    // --- Métodos Privados Auxiliares ---
    private Cart getOrCreateCart(String email) {
        return cartRepository.findByUserEmail(email)
                .orElseGet(() -> {
                    User user = userRepository.findByEmail(email)
                            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                    Cart newCart = Cart.builder()
                            .user(user)
                            .totalPrice(BigDecimal.ZERO)
                            .items(new ArrayList<>())
                            .build();
                    return cartRepository.save(newCart);
                });
    }

    private Product findProductOrThrow(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    private void validateStockAndLimit(Product product, int quantity) {
        if (quantity > 4) {
            throw new IllegalArgumentException("No puedes comprar más de 4 unidades del producto: " + product.getName());
        }
        if (product.getStock() < quantity) {
            throw new IllegalArgumentException("Stock insuficiente para el producto: " + product.getName());
        }
    }

    private void calculateTotals(Cart cart) {
        BigDecimal total = cart.getItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        cart.setTotalPrice(total);
    }

}
