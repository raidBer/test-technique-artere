error id: file:///C:/Users/DELL/Downloads/artere/src/main/java/com/example/artere/service/CategoryService.java:_empty_/CategoryRepository#save#
file:///C:/Users/DELL/Downloads/artere/src/main/java/com/example/artere/service/CategoryService.java
empty definition using pc, found symbol in pc: _empty_/CategoryRepository#save#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 3088
uri: file:///C:/Users/DELL/Downloads/artere/src/main/java/com/example/artere/service/CategoryService.java
text:
```scala
package com.example.artere.service;

import com.example.artere.model.Category;
import com.example.artere.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(String name, String description) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        return categoryRepository.save(category);
    }

    public Category createSubCategory(Long parentId, String name, String description) {
        Optional<Category> parent = categoryRepository.findById(parentId);
        if (parent.isEmpty()) {
            throw new IllegalArgumentException("Parent category not found");
        }
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        category.setParent(parent.get());
        return categoryRepository.save(category);
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public List<Category> getAllRootCategories() {
        return categoryRepository.findByParentIsNull();
    }

    public List<Category> getSubCategories(Long parentId) {
        Optional<Category> parent = categoryRepository.findById(parentId);
        if (parent.isEmpty()) {
            throw new IllegalArgumentException("Parent category not found");
        }
        return categoryRepository.findByParent(parent.get());
    }

    public Category updateCategory(Long id, String name, String description) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new IllegalArgumentException("Category not found");
        }
        Category cat = category.get();
        if (name != null && !name.isBlank()) {
            cat.setName(name);
        }
        if (description != null && !description.isBlank()) {
            cat.setDescription(description);
        }
        return categoryRepository.save(cat);
    }

    public void deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new IllegalArgumentException("Category not found");
        }
        categoryRepository.deleteById(id);
    }

    public Category linkCategoryToParent(Long categoryId, Long parentId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        Optional<Category> parent = categoryRepository.findById(parentId);

        if (category.isEmpty() || parent.isEmpty()) {
            throw new IllegalArgumentException("Category or parent not found");
        }

        Category cat = category.get();
        cat.setParent(parent.get());
        return categoryRepository.save@@(cat);
    }

    public Category unlinkCategoryFromParent(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new IllegalArgumentException("Category not found");
        }

        Category cat = category.get();
        cat.setParent(null);
        return categoryRepository.save(cat);
    }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/CategoryRepository#save#