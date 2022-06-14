package com.revature.ers.dtos.responses;

import com.revature.ers.models.User;

//We need unique identifiers = id,username,role.
public class Principal {
    private String id;
    private String username;
    private String role_id;
    private boolean is_active;

    public Principal(){

    }


    public Principal(String id, String username, String role_id) {
        this.id = id;
        this.username = username;
        this.role_id = role_id;
    }

    public Principal(String id, String username, String role_id, boolean is_active) {
        this.id = id;
        this.username = username;
        this.role_id = role_id;
        this.is_active = is_active;
    }

    public Principal(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role_id = user.getRole_id();
        this.is_active = user.isIs_active();

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

    public boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = Boolean.parseBoolean(is_active);
    }

    @Override
    public String toString() {
        return "Principal{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", role_id='" + role_id + '\'' +
                ", is_active='" + is_active + '\'' +
                '}';
    }
}

