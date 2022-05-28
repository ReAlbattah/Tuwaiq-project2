package com.example.porject2.services;

import com.example.porject2.model.Product;
import com.example.porject2.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    private ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Product> getProducts(){
        return products;
    }

    public boolean addProducts(Product product){
        return products.add(product);
    }

    public boolean updateProducts(Product product,Integer index){
        if(index > products.size()-1 || index <0){
            return false;
        }
        Product currentProduct = products.set(index,product);
        return true;
    }

    public boolean deleteProducts(Integer index){
        if(index > products.size()-1 || index <0){
            return false;
        }
        products.remove((int)index);
        return true;
    }

    public Product getproductID(String productid){
        for(Product product:products){
            if(product.getId().equals(productid))
                return product;
        }
        return null;
    }

}
