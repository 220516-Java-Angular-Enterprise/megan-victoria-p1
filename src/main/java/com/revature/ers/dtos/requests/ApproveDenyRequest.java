package com.revature.ers.dtos.requests;

public class ApproveDenyRequest {
    private String id;
    private String status_id;

    public ApproveDenyRequest(){}


    public ApproveDenyRequest(String id, String status_id) {
        this.id = id;
        this.status_id = status_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    @Override
    public String toString() {
        return "ApproveDenyRequest{" +
                "id='" + id + '\'' +
                ", status_id='" + status_id + '\'' +
                '}';
    }
}
