package com.revature.ers.services;

import com.revature.ers.daos.UserDAO;
import com.revature.ers.dtos.requests.*;
import com.revature.ers.models.User;
import com.revature.ers.utils.annotations.Inject;
import com.revature.ers.utils.custom_exceptions.*;

import java.util.List;
import java.util.UUID;
// this is so I can push
public class UserService {
    @Inject
    private final UserDAO userDAO;

    @Inject
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User login(LoginRequest request) {
        User user = new User();

        if (!isValidUsername(request.getUsername()) || !isValidPassword(request.getPassword()))
            throw new InvalidRequestException("Invalid username or password");
        user = userDAO.getUserByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (user == null) throw new AuthenticationException("Invalid credentials provided.");
        if (!user.isIs_active()) throw new AuthenticationException("Invalid credentials provided.");

        return user;
    }

    public User register(NewUserRequest request) {
        User user = request.extractUser();

        if (isNotDuplicateUsername(user.getUsername())) {
            if (isValidUsername(user.getUsername())) {
                if (isValidPassword(user.getPassword())) {
                    user.setId(UUID.randomUUID().toString());
                    userDAO.save(user);
                } else
                    throw new InvalidRequestException("Invalid password. Minimum eight characters,at least one letter, one number and one special character. ");
            } else throw new InvalidRequestException("Invalid username. Username needs to be 8-20 characters long ");
        } else throw new ResourceConflictException("Username is already taken");
        return user;
    }

    public User getUserById(String id) {
        return userDAO.getById(id);
    }

    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    public List<User> getUserByUsername(String name) {
        return userDAO.getUsersByUsername(name);
    }

    public List<User> getUserByRole(String role) {
        return userDAO.getUsersByRole(role);
    }

    public List<User> getUserStatus (String status) {
        return userDAO.getUserActive(status);
    }

// VALIDATIONS
    private boolean isValidUsername(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    private boolean isNotDuplicateUsername(String username) {
        return !userDAO.getAllUsernames().contains(username);
    }

    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\\\d)(?=.*[@$!%*#?&])[A-Za-z\\\\d@$!%*#?&]{8,}$");
    }

    private User isValidCredentials(User user) {
        if (user.getUsername() == null && user.getPassword() == null)
            throw new InvalidUserException("Incorrect username and password.");
        else if (user.getUsername() == null) throw new InvalidUserException("Incorrect username.");
        else if (user.getPassword() == null) throw new InvalidUserException("Incorrect password.");

        return user;
    }

//    ADMIN FUNCTIONS
    public void updatePassword(ChangePasswordRequest changePasswordRequest) {
      userDAO.updateUserPassword(changePasswordRequest.getPassword(), changePasswordRequest.getUsername());
    }

    public void changeUserStatus(IsActiveRequest isActiveRequest) {
        userDAO.updateIs_active(isActiveRequest.getIs_active(), isActiveRequest.getUsername());
    }

    public void changeUserRole(ChangeUserRole changeUserRole) {
        userDAO.updateUserRole(changeUserRole.getRole_id(), changeUserRole.getUsername());
    }
}

