package com.example.porject2.services;

import com.example.porject2.model.Category;
import com.example.porject2.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {

    private ArrayList<Category> categories = new ArrayList<>();

    public ArrayList<Category> getCategories(){
        return categories;
    }

    public boolean addCategories(Category category){
        return categories.add(category);
    }

    public boolean updateCategories(Category category,Integer index){
        if(index > categories.size()-1 || index <0){
            return false;
        }
        Category currentCategory = categories.set(index,category);
        return true;
    }

    public boolean deleteCategories(Integer index){
        if(index > categories.size()-1 || index <0){
            return false;
        }
        categories.remove((int)index);
        return true;
    }

}
