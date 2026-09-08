package com.ConsigueVentas.TiendaAccesorios.Cart.Service;

import com.ConsigueVentas.TiendaAccesorios.Cart.Dto.CartResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Cart.Dto.CartItem.CartItemRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Cart.Dto.CartSyncItem.CartSyncItemRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Cart.Dto.MessageResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Cart.Entity.Cart;
import com.ConsigueVentas.TiendaAccesorios.Cart.Entity.CartItem;
import com.ConsigueVentas.TiendaAccesorios.Product.Entity.Product;
import com.ConsigueVentas.TiendaAccesorios.User.Entity.User;
import com.ConsigueVentas.TiendaAccesorios.Cart.Mapper.CartMapper;
import com.ConsigueVentas.TiendaAccesorios.Cart.Repository.CartRepository;
import com.ConsigueVentas.TiendaAccesorios.Product.Repository.ProductRepository;
import com.ConsigueVentas.TiendaAccesorios.User.Repository.UserRepository;
import com.ConsigueVentas.TiendaAccesorios.Cart.Service.Interfaces.ICartService;
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

        int quantityAdded = 1;
        validateStockAndLimit(product, quantityAdded);

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();

            int newQuantity = item.getQuantity() + quantityAdded;

            validateStockAndLimit(product, newQuantity);

            item.setQuantity(newQuantity);
        } else {
            CartItem newItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(quantityAdded)
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

    @Override
    public CartResponseDto syncCart(String email, List<CartSyncItemRequestDto> itemsDto) {
        Cart cart = getOrCreateCart(email);
        cart.getItems().clear(); // Limpia los anteriores para sincronizar la lista completa enviada desde Angular

        for (CartSyncItemRequestDto dto : itemsDto) {
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
    public CartResponseDto decreaseItem(String email, Long productId){
        Cart cart = getOrCreateCart(email);

        CartItem item = cart.getItems().stream()
                .filter(cartItem ->
                        cartItem.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Product not found in cart"));
        if (item.getQuantity() == 1) {
            return removeItem(email, productId);
        }
        item.setQuantity(item.getQuantity() - 1);
        calculateTotals(cart);

        return cartMapper.toDto(cartRepository.save(cart));
    }

    // --- Métodos Privados Auxiliares ---
    private Cart getOrCreateCart(String email) {
        return cartRepository.findByUserEmail(email)
                .orElseGet(() -> {
                    User user = userRepository.findByEmail(email)
                            .orElseThrow(() -> new RuntimeException("User not found"));
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
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    private void validateStockAndLimit(Product product, int quantity) {
        if (quantity > 4) {
            throw new IllegalArgumentException("Only 4 products available: " + product.getName());
        }
        if (product.getStock() < quantity) {
            throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
        }
    }

    private void calculateTotals(Cart cart) {
        BigDecimal total = cart.getItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        cart.setTotalPrice(total);
    }

}
