package com.example.porject2.services;

import com.example.porject2.model.Comment;
import com.example.porject2.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommentService {

    private ArrayList<Comment> comments = new ArrayList<>();

    public ArrayList<Comment> getComments(){
        return comments;
    }

//    public boolean addUsers(User user){
//        return users.add(user);
//    }
//
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
