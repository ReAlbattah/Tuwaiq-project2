package com.example.porject2.services;

import com.example.porject2.model.Cart;
import com.example.porject2.model.Product;
import com.example.porject2.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class CartService {

    private ArrayList<Cart> carts = new ArrayList<>();
    public ArrayList<Cart> getCarts(){
        return carts;
    }


    public boolean addCarts(Cart cart){
        return carts.add(cart);
    }

//    public Cart getCartID(String cartid){
//        for(Cart cart:carts){
//            if(cart.getId().equals(cartid))
//                return cart;
//        }
//        return null;
//    }

    public Cart getUserCart(String userid){
        for(Cart cart:carts){
            if(cart.getUserID().equals(userid))
                return cart;
        }
        Cart cart = new Cart(userid,userid);
        addCarts(cart);
         return cart;

    }

//    public Cart getproductID(String productid){
//        for(Cart cart:carts){
//            if(user.getId().equals(userid))
//                return user;
//        }
//        return null;
//    }

//    public boolean updateUsers(User user,Integer index){
//        if(index > users.size()-1 || index <0){
//            return false;
//        }
//        User currentUser = users.set(index,user);
//        return true;
//    }
//
//    public boolean deleteUsers(Integer index){
//        if(index > users.size()-1 || index <0){
//            return false;
//        }
//        users.remove((int)index);
//        return true;
//    }

}
