package com.nimap.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nimap.entity.Category;
import com.nimap.service.CategoryService;

import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
 @Autowired
 private CategoryService categoryService;

 @GetMapping
 public Page<Category> getAllCategories(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
     Pageable pageable = PageRequest.of(page, size);
     return categoryService.getAllCategories(pageable);
 }

 @PostMapping
 public Category createCategory(@RequestBody Category category) {
	  System.out.println("Received Category: " + category);
     return categoryService.createCategory(category);
 }

 @GetMapping("/{id}")
 public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
     Optional<Category> category = categoryService.getCategoryById(id);
     return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
 }

 @PutMapping("/{id}")
 public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
     Category updatedCategory = categoryService.updateCategory(id, categoryDetails);
     return ResponseEntity.ok(updatedCategory);
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
     categoryService.deleteCategory(id);
     return ResponseEntity.noContent().build();
 }
}
