package com.example.porject2.controllers;

import com.example.porject2.model.Api;
import com.example.porject2.model.Category;
import com.example.porject2.model.Product;
import com.example.porject2.services.CategoryService;
import com.example.porject2.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ArrayList<Category>> getCategory(){
        return ResponseEntity.status(200).body(categoryService.getCategories());
    }

    @PostMapping
    public ResponseEntity<Api> addCategory(@RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean add = categoryService.addCategories(category);
        if (!add){
            return ResponseEntity.status(400).body(new Api("Error adding category", 400));
        }
             return ResponseEntity.status(200).body(new Api("Category added", 200));
    }

    @PutMapping("/{index}")
    public ResponseEntity<Api> updateCategory(@RequestBody @Valid Category category,@PathVariable Integer index,Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }

        boolean upadte = categoryService.updateCategories(category, index);
        if(!upadte){
            return ResponseEntity.status(400).body(new Api("Error updating category", 400));
        }
            return ResponseEntity.status(200).body(new Api("Category updated", 200));
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<Api> deleteCategory(@PathVariable Integer index){
        boolean delete = categoryService.deleteCategories(index);
        if(!delete){
            return ResponseEntity.status(400).body(new Api("Error deleting category", 400));
        }
        return ResponseEntity.status(200).body(new Api("category deleted", 200));
    }
}
