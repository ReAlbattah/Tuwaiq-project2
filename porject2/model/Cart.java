package com.example.porject2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;


@Data
public class Cart {
    @NotEmpty
    @Size(max = 3)
    private String id;
    @NotEmpty
    @Size(max = 3)
    private String userID;
    private ArrayList<Product> products;

    public Cart(String id, String userID) {
        this.id = id;
        this.userID = userID;
        this.products = new ArrayList<Product>();
    }
}
