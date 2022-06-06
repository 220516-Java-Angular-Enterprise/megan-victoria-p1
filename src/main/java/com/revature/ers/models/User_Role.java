package com.revature.ers.models;

public class User_Role {
    private String id;
    private String role;

    public User_Role() {}


    public User_Role(String id, String role) {
        this.id = id;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User_Role{" +
                "id='" + id + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
