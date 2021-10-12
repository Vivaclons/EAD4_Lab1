package kz.spring.categories.controller;

import kz.spring.categories.model.Category;
import kz.spring.categories.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @GetMapping("/{CategoryId}")
    public ResponseEntity<?> getProductById(@PathVariable("CategoryId") Long ProductId) {
        return ResponseEntity.ok(categoryService.getCategoryById(ProductId));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody Category category, @RequestHeader("Authorization") String auth) {
        return ResponseEntity.ok(categoryService.createCategory(category, auth));
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody Category category, @RequestHeader("Authorization") String auth) {
        return ResponseEntity.ok(categoryService.updateCategory(category, auth));
    }

    @DeleteMapping("/delete/{CategoryId}")
    public void deleteProduct(@PathVariable("ProductId") Long CategoryId, @RequestHeader("Authorization") String auth) {
        categoryService.deleteCategory(CategoryId, auth);
    }


}
