package com.revature.ers.models;

public class Reimbursement_Type {
    private String id;
    private String type;

    public Reimbursement_Type() {}


    public Reimbursement_Type(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Reimbursement_Type{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
