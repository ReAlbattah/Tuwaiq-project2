package com.example.porject2.controllers;

import com.example.porject2.model.Api;
import com.example.porject2.model.Comment;
import com.example.porject2.model.Product;
import com.example.porject2.model.User;
import com.example.porject2.services.ProductService;
import com.example.porject2.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ArrayList<User>> getUser(){
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<Api> addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean add = userService.addUsers(user);
        if (!add){
            return ResponseEntity.status(400).body(new Api("Error adding", 400));
        }
             return ResponseEntity.status(200).body(new Api("User added", 200));
    }

    @PutMapping("/{index}")
    public ResponseEntity<Api> updateUser(@RequestBody @Valid User user,@PathVariable Integer index,Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }

        boolean upadte = userService.updateUsers(user, index);
        if(!upadte){
            return ResponseEntity.status(400).body(new Api("Error updating ", 400));
        }
            return ResponseEntity.status(200).body(new Api("User updated", 200));
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<Api> deleteUser(@PathVariable Integer index){
        boolean delete = userService.deleteUsers(index);
        if(!delete){
            return ResponseEntity.status(400).body(new Api("Error deleting ", 400));
        }
        return ResponseEntity.status(200).body(new Api("User deleted", 200));
    }

    @PutMapping("/add/{userid}/{productid}")// user can add product to a cart.
    public ResponseEntity<Api> addProductToCart(@PathVariable String userid,@PathVariable String productid ){
        Integer cartAdd = userService.addProductToCart(userid,productid);
        switch (cartAdd){
            case -1:
                return ResponseEntity.status(400).body(new Api("user id not found ",400));
            case 0:
                return ResponseEntity.status(400).body(new Api("product id not found ",400));
            case 1:
                return ResponseEntity.status(201).body(new Api("product add to cart ",201));
            default:
                return ResponseEntity.status(500).body(new Api("server error  ",500));
        }
    }

    @PutMapping("/delete/{userid}/{productid}")// user can delete product to a cart.
    public ResponseEntity<Api> deleteProductToCart(@PathVariable String userid,@PathVariable String productid ){
        Integer cartdelete = userService.deleteProductToCart(userid,productid);
        switch (cartdelete){
            case -1:
                return ResponseEntity.status(400).body(new Api("user id not found ",400));
            case 0:
                return ResponseEntity.status(400).body(new Api("product id not found ",400));
            case 1:
                return ResponseEntity.status(201).body(new Api("product deleted from cart ",201));
            default:
                return ResponseEntity.status(500).body(new Api("server error  ",500));
        }
    }

    @PutMapping("/addtomerchantstock/{merchantid}/{productid}/{amount}")// user can delete product to a cart.
    public ResponseEntity<Api> addProductToMerchantStock(@PathVariable String merchantid,@PathVariable String productid,@PathVariable Integer amount ) {
        Integer cartAdd = userService.addProductToMerchantStock(merchantid, productid, amount);
        switch (cartAdd) {
            case 0:
                return ResponseEntity.status(400).body(new Api("product id not found ", 400));
            case 1:
                return ResponseEntity.status(201).body(new Api("product added ", 201));
            default:
                return ResponseEntity.status(500).body(new Api("server error  ", 500));
        }
    }
        @PutMapping("/directbuy/{merchantid}/{productid}/{userid}")// user buy direct.
        public ResponseEntity<Api> direct(@PathVariable String merchantid,@PathVariable String productid,@PathVariable String userid ) {
            Integer cartAdd = userService.direct(userid, productid, merchantid);
            switch (cartAdd) {
                case -1:
                    return ResponseEntity.status(400).body(new Api("user id not found ", 400));
                case 0:
                    return ResponseEntity.status(400).body(new Api("product id not found ", 400));
                case 1:
                    return ResponseEntity.status(400).body(new Api("Merchant stock id not found ", 400));
                case 2:
                    return ResponseEntity.status(400).body(new Api("Stock is empty or your balance less than price ", 400));
                case 3:
                    return ResponseEntity.status(201).body(new Api("Your order is completed ", 201));
                default:
                    return ResponseEntity.status(500).body(new Api("server error  ", 500));
            }
        }

    @PutMapping("/comment/{userid}/{productid}")//
    public ResponseEntity<Api> direct(@PathVariable String userid,@PathVariable String productid, @RequestBody Comment comment ) {
        Integer commentAdd = userService.comment(userid, productid, comment);
        switch (commentAdd) {
            case -1:
                return ResponseEntity.status(400).body(new Api("user id not found ", 400));
            case 0:
                return ResponseEntity.status(400).body(new Api("product id not found ", 400));
            case 1:
                return ResponseEntity.status(400).body(new Api("Comment post ", 400));

            default:
                return ResponseEntity.status(500).body(new Api("server error  ", 500));
        }
    }

    @GetMapping("/allcomments")
    public ResponseEntity getComments(String productid){
        ArrayList comments = userService.getComments(productid);
        if( comments != null){
            return ResponseEntity.status(200).body(comments);
        }
        return ResponseEntity.status(400).body(new Api("User id not found", 400));
    }

    @PostMapping ("/rate")
    public ResponseEntity getFiveStars(){
        return ResponseEntity.status(200).body(userService.getFiveStars());
    }

    @PostMapping("/history")
    public ResponseEntity getPurchaseHistory(){
        return ResponseEntity.status(200).body(userService.getPurchaseHistory());
    }

}

