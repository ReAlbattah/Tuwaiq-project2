package com.example.porject2.controllers;

import com.example.porject2.model.Api;
import com.example.porject2.model.Merchant;
import com.example.porject2.model.MerchantStock;
import com.example.porject2.services.MerchantService;
import com.example.porject2.services.MerchantStockService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("merchantstock")
@AllArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;

    @GetMapping
    public ResponseEntity<ArrayList<MerchantStock>> getMerchant(){
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStocks());
    }

    @PostMapping
    public ResponseEntity<Api> addMerchant(@RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean add = merchantStockService.addMerchantStocks(merchantStock);
        if (!add){
            return ResponseEntity.status(400).body(new Api("Error adding ", 400));
        }
             return ResponseEntity.status(200).body(new Api("Merchant stock added", 200));
    }

    @PutMapping("/{index}")
    public ResponseEntity<Api> updateMerchant(@RequestBody @Valid MerchantStock merchantStock,@PathVariable Integer index,Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }

        boolean upadte = merchantStockService.updateMerchantStocks(merchantStock, index);
        if(!upadte){
            return ResponseEntity.status(400).body(new Api("Error updating ", 400));
        }
            return ResponseEntity.status(200).body(new Api("Merchant stock updated", 200));
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<Api> deleteMerchant(@PathVariable Integer index){
        boolean delete = merchantStockService.deleteMerchantStocks(index);
        if(!delete){
            return ResponseEntity.status(400).body(new Api("Error deleting ", 400));
        }
        return ResponseEntity.status(200).body(new Api("Merchant stock deleted", 200));
    }
}
