package com.example.porject2.controllers;

import com.example.porject2.model.Api;
import com.example.porject2.model.Category;
import com.example.porject2.model.Merchant;
import com.example.porject2.services.CategoryService;
import com.example.porject2.services.MerchantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("merchant")
@AllArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping
    public ResponseEntity<ArrayList<Merchant>> getMerchant(){
        return ResponseEntity.status(200).body(merchantService.getMerchants());
    }

    @PostMapping
    public ResponseEntity<Api> addMerchant(@RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean add = merchantService.addMerchants(merchant);
        if (!add){
            return ResponseEntity.status(400).body(new Api("Error adding ", 400));
        }
             return ResponseEntity.status(200).body(new Api("Merchant added", 200));
    }

    @PutMapping("/{index}")
    public ResponseEntity<Api> updateMerchant(@RequestBody @Valid Merchant merchant,@PathVariable Integer index,Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }

        boolean upadte = merchantService.updateMerchants(merchant, index);
        if(!upadte){
            return ResponseEntity.status(400).body(new Api("Error updating ", 400));
        }
            return ResponseEntity.status(200).body(new Api("Merchant updated", 200));
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<Api> deleteMerchant(@PathVariable Integer index){
        boolean delete = merchantService.deleteMerchants(index);
        if(!delete){
            return ResponseEntity.status(400).body(new Api("Error deleting ", 400));
        }
        return ResponseEntity.status(200).body(new Api("Merchant deleted", 200));
    }
}
