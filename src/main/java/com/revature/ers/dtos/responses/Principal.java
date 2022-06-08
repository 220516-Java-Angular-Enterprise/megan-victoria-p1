package com.revature.ers.dtos.responses;

import com.revature.ers.models.User;

//We need unique identifiers = id,username,role.
public class Principal {
    private String id;
    private String username;
    private String role_id;

    public Principal(){

    }

    public Principal(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role_id = user.getRole_id();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", role_id='" + role_id + '\'' +
                '}';
    }
}

