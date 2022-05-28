package com.example.porject2.controllers;

import com.example.porject2.model.Api;
import com.example.porject2.model.Cart;
import com.example.porject2.model.User;
import com.example.porject2.services.CartService;
import com.example.porject2.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("cart")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<ArrayList<Cart>> getCart(){
        return ResponseEntity.status(200).body(cartService.getCarts());
    }



//    @PostMapping
//    public ResponseEntity<Api> addUser(@RequestBody @Valid User user, Errors errors) {
//        if (errors.hasErrors()) {
//            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
//        }
//        boolean add = userService.addUsers(user);
//        if (!add){
//            return ResponseEntity.status(400).body(new Api("Error adding", 400));
//        }
//             return ResponseEntity.status(200).body(new Api("User added", 200));
//    }
//
//    @PutMapping("/{index}")
//    public ResponseEntity<Api> updateUser(@RequestBody @Valid User user,@PathVariable Integer index,Errors errors){
//        if (errors.hasErrors()) {
//            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
//        }
//
//        boolean upadte = userService.updateUsers(user, index);
//        if(!upadte){
//            return ResponseEntity.status(400).body(new Api("Error updating ", 400));
//        }
//            return ResponseEntity.status(200).body(new Api("User updated", 200));
//    }
//
//    @DeleteMapping("/{index}")
//    public ResponseEntity<Api> deleteUser(@PathVariable Integer index){
//        boolean delete = userService.deleteUsers(index);
//        if(!delete){
//            return ResponseEntity.status(400).body(new Api("Error deleting ", 400));
//        }
//        return ResponseEntity.status(200).body(new Api("User deleted", 200));
//    }
}
