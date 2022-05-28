package com.example.porject2.services;

import com.example.porject2.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class UserService {

    private ArrayList<User> users = new ArrayList<>();
   public final CartService cartService;
    public final ProductService productService;
    public final MerchantStockService merchantStockService;
    public final PurchaseHistoryService purchaseHistoryService;
    public ArrayList<User> getUsers(){
        return users;
    }

    public boolean addUsers(User user){
        return users.add(user);
    }

    public boolean updateUsers(User user,Integer index){
        if(index > users.size()-1 || index <0){
            return false;
        }
        User currentUser = users.set(index,user);
        return true;
    }

    public boolean deleteUsers(Integer index){
        if(index > users.size()-1 || index <0){
            return false;
        }
        users.remove((int)index);
        return true;
    }

    public User getuserID(String userid){
    for(User user:users){
        if(user.getId().equals(userid))
            return user;
        }
    return null;
    }

    public Integer addProductToCart(String userid,String productid) {
        User user = getuserID(userid);
        if (user == null) {
            return -1;
        }
        Product product = productService.getproductID(productid);
        if (product == null) {
            return 0;
        }
      Cart cart=  cartService.getUserCart(userid);
        ArrayList<Product> products = cart.getProducts();
        products.add(product);
        cart.setProducts(products);
        return 1;
    }


    public Integer deleteProductToCart(String userid,String productid) {
        User user = getuserID(userid);
        if (user == null) {
            return -1;
        }
        Product product = productService.getproductID(productid);
        if (product == null) {
            return 0;
        }
        Cart cart=  cartService.getUserCart(userid);
        ArrayList<Product> products = cart.getProducts();
        products.remove(product);
//        products.add(product);
//        cart.setProducts(products);
        return 1;
    }

    public Integer addProductToMerchantStock(String productid,String merchantid,Integer stock ){
        Product product = productService.getproductID(productid);
        if (product == null) {
            return 0;
        }
        for(MerchantStock merchantStock: merchantStockService.getMerchantStocks()){
        if(merchantStockService.getmerchantID(merchantid).equals(merchantid)){
            merchantStock.setStock(merchantStock.getStock()+stock);
        }
    }
        return 1;
    }

    public Integer direct(String userid,String productid,String merchantid){
        User user = getuserID(userid);
        if (user == null) {
            return -1;
        }
        Product product = productService.getproductID(productid);
        if (product == null) {
            return 0;
        }

        MerchantStock merchantStock =merchantStockService.getmerchantID(merchantid);
        if (merchantStock == null) {
            return 1;
        }
        if(merchantStock.getStock() != 0 && user.getBalance()>=product.getPrice()){
            return 2;
        }
        merchantStock.setStock(merchantStock.getStock()-1);
        user.setBalance(user.getBalance()-product.getPrice());
        return 3;
    }

    public Integer comment(String userid,String productid, Comment comment){
        for (PurchaseHistory purchaseHistory:purchaseHistoryService.getPurchaseHistories()){
            if(purchaseHistory.getUserID().equals(userid)){
                return -1;
            }
            if(purchaseHistory.getProductID().equals(productid)){
                return 0;
            }
            productService.getproductID(productid).getComment().add(comment);

        }
        return 1;
    }

    public ArrayList getComments(String productid){
        Product product = productService.getproductID(productid);
        if(product != null){
            return product.getComment();
        }
        return null;
    }

    public ArrayList<Product> getFiveStars(){
        ArrayList<Product> productsFiveStarts = new ArrayList<>();
        for(Product product : productService.getProducts()){
            for(Comment comment: product.getComment()){
                if (comment.getRate() == 5){
                    productsFiveStarts.add(product);
                }
            }
        }
        return productsFiveStarts;
    }

    public ArrayList getPurchaseHistory() {
        return purchaseHistoryService.getPurchaseHistories();
    }

}

