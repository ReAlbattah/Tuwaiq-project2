package com.example.porject2.controllers;

import com.example.porject2.model.Api;
import com.example.porject2.model.Product;
import com.example.porject2.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ArrayList<Product>> getProduct(){
        return ResponseEntity.status(200).body(productService.getProducts());
    }

    @PostMapping
    public ResponseEntity<Api> addProduct(@RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean add = productService.addProducts(product);
        if (!add){
            return ResponseEntity.status(400).body(new Api("Error adding product", 400));
        }
             return ResponseEntity.status(200).body(new Api("Product added", 200));
    }

    @PutMapping("/{index}")
    public ResponseEntity<Api> updateProduct(@RequestBody @Valid Product product,@PathVariable Integer index,Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }

        boolean upadte = productService.updateProducts(product,index);
        if(!upadte){
            return ResponseEntity.status(400).body(new Api("Error updating product", 400));
        }
            return ResponseEntity.status(200).body(new Api("Product updated", 200));
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<Api> deleteProduct(@PathVariable Integer index){
        boolean delete = productService.deleteProducts(index);
        if(!delete){
            return ResponseEntity.status(400).body(new Api("Error deleting product", 400));
        }
        return ResponseEntity.status(200).body(new Api("Product deleted", 200));
    }
}
