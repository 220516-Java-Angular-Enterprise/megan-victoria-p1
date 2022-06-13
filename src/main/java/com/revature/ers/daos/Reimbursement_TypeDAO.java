package com.revature.ers.daos;

import com.revature.ers.models.Reimbursement_Type;
import com.revature.ers.utils.custom_exceptions.InvalidSQLException;
import com.revature.ers.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Reimbursement_TypeDAO implements CrudDAO<Reimbursement_Type> {

    @Override
    public  void save(Reimbursement_Type obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO reimbursement_type(id,type) VALUES(?,?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getType());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when trying to save a new reimbursement type to the database");
        }
    }

    @Override
    public void update(Reimbursement_Type obj) {
        delete(obj.getId());
        save(obj);

    }

    @Override
    public void delete(String id) {
        try(Connection con=ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM reimbursement_types WHERE id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            throw new InvalidSQLException("An error occurred when trying to delete a reimbursement type.");
        }

    }

    @Override
    public Reimbursement_Type getById(String id) {
        Reimbursement_Type reimbursement_type = new Reimbursement_Type();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursement_types WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
                reimbursement_type = new Reimbursement_Type(
                        rs.getString("id"),
                        rs.getString("type"));

        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when trying to get a Reimbursement Type.");
        }
        return reimbursement_type;
    }
    @Override
    public List<Reimbursement_Type> getAll() {
      List<Reimbursement_Type> reimbursement_types= new ArrayList<>();
      try(Connection con=ConnectionFactory.getInstance().getConnection()){
          PreparedStatement ps= con.prepareStatement("SELECT * FROM reimbursement_types");
          ResultSet rs= ps.executeQuery();
          while(rs.next()){
              Reimbursement_Type reimbursement_type= new Reimbursement_Type(
                      rs.getString("id"),
                      rs.getString("type"));
              reimbursement_types.add(reimbursement_type);
          }
          }catch (SQLException e){
              throw new InvalidSQLException("An error occurred when trying to get a Reimbursement Type.");
          }
      return reimbursement_types;
      }
    }

