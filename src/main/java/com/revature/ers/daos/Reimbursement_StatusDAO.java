package com.revature.ers.daos;

import com.revature.ers.models.Reimbursement_Status;
import com.revature.ers.utils.custom_exceptions.InvalidSQLException;
import com.revature.ers.utils.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Reimbursement_StatusDAO implements CrudDAO<Reimbursement_Status> {

Connection con = DatabaseConnection.getCon();
    @Override
    public void save(Reimbursement_Status obj) {

    }

    @Override
    public void update(Reimbursement_Status obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Reimbursement_Status getById(String status_id) {
        Reimbursement_Status reimbursement_status = new Reimbursement_Status();
        try{
            PreparedStatement ps=con.prepareStatement("SELECT from reimbursement_status WHERE status_id =?");
            ps.setString(1,status_id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                reimbursement_status= new Reimbursement_Status(rs.getString("status_id"),rs.getString("status"));
            }
        }catch (SQLException e){
            throw new InvalidSQLException("An error occurred when trying to obtain the reimbursement status from the database.");
        }return reimbursement_status;
    }

    @Override
    public List<Reimbursement_Status> getAll() {
        return null;
    }
}
