package com.example.artere.service;

import com.example.artere.model.Category;
import com.example.artere.model.Product;
import com.example.artere.repository.CategoryRepository;
import com.example.artere.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    private Category testCategory;
    private Product testProduct;

    @BeforeEach
    public void setUp() {
        testCategory = new Category();
        testCategory.setId(1L);
        testCategory.setName("Electronics");
        testCategory.setDescription("Electronic devices");

        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setName("MacBook Pro");
        testProduct.setPrice(1299.99);
        testProduct.setStock(10);
    }

    @Test
    public void testCreateProductWithCategory() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(testCategory));
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        Product result = productService.createProduct("MacBook Pro", 1299.99, 10, 1L);

        assertNotNull(result);
        assertEquals("MacBook Pro", result.getName());
        assertEquals(1299.99, result.getPrice());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testLinkProductToCategory() {
        testProduct.setCategory(null);
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(testCategory));
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        Product result = productService.linkProductToCategory(1L, 1L);

        assertNotNull(result);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    /*
    NOTE: Pour un vrai projet, il faudrait tester TOUT:

    - Les services: créer, modifier, supprimer produits/catégories
    - La liaison produit-catégorie (link/unlink)
    - Les erreurs: catégorie inexistante, produit pas trouvé
    - Les cas limites: prix négatif, stock zéro, etc.
    - Les contrôleurs
    - La base de données: cascade delete
    - La sérialisation JSON: pas d'infini loop, pas de références circulaires
     
    Ici j'ai juste fait 2 tests de base pour montrer que j'ai compris le concept.
    */
}
