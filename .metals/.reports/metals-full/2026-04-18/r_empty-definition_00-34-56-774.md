error id: file:///C:/Users/DELL/Downloads/artere/src/main/java/com/example/artere/controller/ProductController.java:_empty_/`<any>`#build#
file:///C:/Users/DELL/Downloads/artere/src/main/java/com/example/artere/controller/ProductController.java
empty definition using pc, found symbol in pc: _empty_/`<any>`#build#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1247
uri: file:///C:/Users/DELL/Downloads/artere/src/main/java/com/example/artere/controller/ProductController.java
text:
```scala
package com.example.artere.controller;

import com.example.artere.model.Product;
import com.example.artere.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Create product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestParam String name,
                                                  @RequestParam double price,
                                                  @RequestParam int stock,
                                                  @RequestParam Long categoryId) {
        try {
            Product product = productService.createProduct(name, price, stock, categoryId);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).bui@@ld();
        }
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Get products by category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long categoryId) {
        try {
            List<Product> products = productService.getProductsByCategory(categoryId);
            return ResponseEntity.ok(products);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Update product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                                  @RequestParam(required = false) String name,
                                                  @RequestParam(required = false) Double price,
                                                  @RequestParam(required = false) Integer stock) {
        try {
            Product product = productService.updateProduct(id, name, price, stock);
            return ResponseEntity.ok(product);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Link product to category
    @PostMapping("/{productId}/link-to-category/{categoryId}")
    public ResponseEntity<Product> linkProductToCategory(@PathVariable Long productId, @PathVariable Long categoryId) {
        try {
            Product product = productService.linkProductToCategory(productId, categoryId);
            return ResponseEntity.ok(product);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Unlink product from category
    @PostMapping("/{productId}/unlink-from-category")
    public ResponseEntity<Product> unlinkProductFromCategory(@PathVariable Long productId) {
        try {
            Product product = productService.unlinkProductFromCategory(productId);
            return ResponseEntity.ok(product);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/`<any>`#build#