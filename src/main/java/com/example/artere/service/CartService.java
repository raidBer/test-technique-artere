package com.example.artere.service;

import com.example.artere.model.Cart;
import com.example.artere.model.CartItem;
import com.example.artere.model.Product;
import com.example.artere.repository.CartRepository;
import com.example.artere.repository.CartItemRepository;
import com.example.artere.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    public Cart getCart(Long cartId) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if (cart.isEmpty()) {
            throw new IllegalArgumentException("Cart not found");
        }
        return cart.get();
    }

    public CartItem addProductToCart(Long cartId, Long productId, int quantity) {
        Cart cart = getCart(cartId);
        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }

        Optional<CartItem> existingItem = cartItemRepository.findByCartAndProduct(cart, product.get());

        CartItem cartItem;
        if (existingItem.isPresent()) {
            cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product.get());
            cartItem.setQuantity(quantity);
            cartItem.setPrice(product.get().getPrice());
        }

        return cartItemRepository.save(cartItem);
    }

    public void removeProductFromCart(Long cartId, Long productId) {
        Cart cart = getCart(cartId);
        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }

        Optional<CartItem> cartItem = cartItemRepository.findByCartAndProduct(cart, product.get());
        if (cartItem.isPresent()) {
            cartItemRepository.delete(cartItem.get());
        }
    }

    public CartItem updateQuantity(Long cartId, Long productId, int newQuantity) {
        Cart cart = getCart(cartId);
        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }

        Optional<CartItem> cartItem = cartItemRepository.findByCartAndProduct(cart, product.get());
        if (cartItem.isEmpty()) {
            throw new IllegalArgumentException("Product not in cart");
        }

        CartItem item = cartItem.get();
        if (newQuantity <= 0) {
            cartItemRepository.delete(item);
            return null;
        }

        item.setQuantity(newQuantity);
        return cartItemRepository.save(item);
    }

    public double getCartTotal(Long cartId) {
        Cart cart = getCart(cartId);
        return cart.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    public List<CartItem> getCartItems(Long cartId) {
        Cart cart = getCart(cartId);
        return cart.getItems();
    }
}
