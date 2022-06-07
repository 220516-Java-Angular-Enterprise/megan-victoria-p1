package com.revature.ers.services;

import com.revature.ers.daos.UserDAO;
import com.revature.ers.dtos.requests.NewUserRequest;
import com.revature.ers.models.User;
import com.revature.ers.utils.annotations.Inject;

import java.util.List;

public class UserService {
    @Inject
    private final UserDAO userDAO;

    @Inject
    public UserService(UserDAO userDAO){
        this.userDAO=userDAO;
    }
    public User login(String username,String password){
        User user=new User();
        List<User> users=userDAO.getAll();

        for(User u:users){(
            if(u.getUsername().equals(username)){
                user.setId(u.getId());
                user.setUsername(u.getUsername());
                if(u.getPassword().equals(password)){
                    user.setPassword(u.getPassword());
                    break;
                }
            }
            if(u.getPassword().equals(password)){
                user.setPassword(u.getPassword());
            }
        }
  return isValidCredentials(user);
    }
    public User register(NewUserRequest){
        User user =request.extractUser();

        if(isNotDuplicateUsername(user.getUsername())){
            if(isValidUsername(user.getUsername())){
                if(isValidPassword(user.getPassword())){
            }
        }
    }
}
