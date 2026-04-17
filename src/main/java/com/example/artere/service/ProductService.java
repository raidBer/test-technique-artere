package com.example.artere.service;

import com.example.artere.model.Category;
import com.example.artere.model.Product;
import com.example.artere.repository.CategoryRepository;
import com.example.artere.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product createProduct(String name, double price, int stock, Long categoryId) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        if (categoryId != null) {
            Optional<Category> category = categoryRepository.findById(categoryId);
            if (category.isEmpty()) {
                throw new IllegalArgumentException("Category not found");
            }
            product.setCategory(category.get());
        }

        return productRepository.save(product);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new IllegalArgumentException("Category not found");
        }
        return productRepository.findByCategory(category.get());
    }

    public Product updateProduct(Long id, String name, Double price, Integer stock) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }

        Product prod = product.get();
        if (name != null && !name.isBlank()) {
            prod.setName(name);
        }
        if (price != null && price > 0) {
            prod.setPrice(price);
        }
        if (stock != null && stock >= 0) {
            prod.setStock(stock);
        }
        return productRepository.save(prod);
    }

    public void deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }
        productRepository.deleteById(id);
    }

    public Product linkProductToCategory(Long productId, Long categoryId) {
        Optional<Product> product = productRepository.findById(productId);
        Optional<Category> category = categoryRepository.findById(categoryId);

        if (product.isEmpty() || category.isEmpty()) {
            throw new IllegalArgumentException("Product or category not found");
        }

        Product prod = product.get();
        prod.setCategory(category.get());
        return productRepository.save(prod);
    }

    public Product unlinkProductFromCategory(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }

        Product prod = product.get();
        prod.setCategory(null);
        return productRepository.save(prod);
    }
}
