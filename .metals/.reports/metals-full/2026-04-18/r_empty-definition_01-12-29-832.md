error id: file:///C:/Users/DELL/Downloads/artere/src/main/java/com/example/artere/controller/CategoryController.java:_empty_/ResponseEntity#
file:///C:/Users/DELL/Downloads/artere/src/main/java/com/example/artere/controller/CategoryController.java
empty definition using pc, found symbol in pc: _empty_/ResponseEntity#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 880
uri: file:///C:/Users/DELL/Downloads/artere/src/main/java/com/example/artere/controller/CategoryController.java
text:
```scala
package com.example.artere.controller;

import com.example.artere.model.Category;
import com.example.artere.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Create root category
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestParam String name, @RequestParam String description) {
        Category category = categoryService.createCategory(name, description);
        return ResponseEntity@@.status(HttpStatus.CREATED).body(category);
    }

    // Create subcategory
    @PostMapping("/{parentId}/subcategories")
    public ResponseEntity<Category> createSubCategory(@PathVariable Long parentId,
                                                       @RequestParam String name,
                                                       @RequestParam String description) {
        try {
            Category category = categoryService.createSubCategory(parentId, name, description);
            return ResponseEntity.status(HttpStatus.CREATED).body(category);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Get category by ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Get all root categories
    @GetMapping
    public ResponseEntity<List<Category>> getAllRootCategories() {
        List<Category> categories = categoryService.getAllRootCategories();
        return ResponseEntity.ok(categories);
    }

    // Get subcategories of a category
    @GetMapping("/{parentId}/subcategories")
    public ResponseEntity<List<Category>> getSubCategories(@PathVariable Long parentId) {
        try {
            List<Category> subCategories = categoryService.getSubCategories(parentId);
            return ResponseEntity.ok(subCategories);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Update category
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id,
                                                    @RequestParam(required = false) String name,
                                                    @RequestParam(required = false) String description) {
        try {
            Category category = categoryService.updateCategory(id, name, description);
            return ResponseEntity.ok(category);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Delete category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Link category to parent
    @PostMapping("/{categoryId}/link-to-parent/{parentId}")
    public ResponseEntity<Category> linkCategoryToParent(@PathVariable Long categoryId, @PathVariable Long parentId) {
        try {
            Category category = categoryService.linkCategoryToParent(categoryId, parentId);
            return ResponseEntity.ok(category);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Unlink category from parent
    @PostMapping("/{categoryId}/unlink-from-parent")
    public ResponseEntity<Category> unlinkCategoryFromParent(@PathVariable Long categoryId) {
        try {
            Category category = categoryService.unlinkCategoryFromParent(categoryId);
            return ResponseEntity.ok(category);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/ResponseEntity#