package com.revature.ers.dtos.requests;

public class IsActiveRequest {
    private String username;
    private boolean is_active;

    public IsActiveRequest(){}


    public IsActiveRequest(String username, boolean is_active) {
        this.username = username;
        this.is_active = is_active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    @Override
    public String toString() {
        return "IsActiveRequest{" +
                "username='" + username + '\'' +
                ", is_active='" + is_active + '\'' +
                '}';
    }
}
