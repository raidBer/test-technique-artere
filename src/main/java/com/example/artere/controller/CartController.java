package com.example.artere.controller;

import com.example.artere.model.Cart;
import com.example.artere.model.CartItem;
import com.example.artere.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Cart> createCart() {
        Cart cart = cartService.createCart();
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable Long id) {
        try {
            Cart cart = cartService.getCart(id);
            return ResponseEntity.ok(cart);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItem> addProductToCart(@PathVariable Long cartId,
                                                      @RequestParam Long productId,
                                                      @RequestParam int quantity) {
        try {
            CartItem cartItem = cartService.addProductToCart(cartId, productId, quantity);
            return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        try {
            cartService.removeProductFromCart(cartId, productId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{cartId}/items/{productId}")
    public ResponseEntity<CartItem> updateQuantity(@PathVariable Long cartId,
                                                    @PathVariable Long productId,
                                                    @RequestParam int quantity) {
        try {
            CartItem cartItem = cartService.updateQuantity(cartId, productId, quantity);
            if (cartItem == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(cartItem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}/total")
    public ResponseEntity<Double> getCartTotal(@PathVariable Long id) {
        try {
            double total = cartService.getCartTotal(id);
            return ResponseEntity.ok(total);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long id) {
        try {
            List<CartItem> items = cartService.getCartItems(id);
            return ResponseEntity.ok(items);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
