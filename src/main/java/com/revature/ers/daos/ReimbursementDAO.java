package com.revature.ers.daos;

import com.revature.ers.models.Reimbursement;
import com.revature.ers.utils.custom_exceptions.InvalidSQLException;
import com.revature.ers.utils.database.ConnectionFactory;
import java.lang.Number;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAO implements CrudDAO<Reimbursement> {

    @Override
    public void save(Reimbursement obj) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO reimbursements (id,amount,submitted,resolved,description,receipt,payment_id,author_id,resolver_id,status_id,type_id) VALUES(?,?,?,?,?,?,?,?,?,?,?) ");
            ps.setString(1, obj.getId());
            ps.setBigDecimal(2, (BigDecimal) obj.getAmount());
            ps.setTimestamp(3, obj.getSubmitted());
            ps.setTimestamp(4, obj.getResolved());
            ps.setString(5, obj.getDescription());
            ps.setBlob(6, obj.getReceipt());
            ps.setString(7, obj.getPayment_id());
            ps.setString(8, obj.getAuthor_id());
            ps.setString(9, obj.getResolver_id());
            ps.setString(10, obj.getStatus_id());
            ps.setString(11, obj.getType_id());
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when trying to save a Reimbursement to the database");
        }
    }

    @Override
    public void update(Reimbursement obj) {

    }

    @Override
    public void delete(String id) {


    }

    @Override
    public Reimbursement getById(String id) {
        Reimbursement reimbursement = new Reimbursement();
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursements WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                reimbursement = new Reimbursement(rs.getString("id"), rs.getBigDecimal("amount"), rs.getTimestamp("submitted"),
                        rs.getTimestamp("resolved"), rs.getString("description"), rs.getBlob("receipt"), rs.getString("payment_id"),
                        rs.getString("author_id"), rs.getString("resolver_id"), rs.getString("status_id"), rs.getString("type_id"));
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An exception occurred when trying to access the reimbursement id");
        }
        return reimbursement;
    }


    @Override
    public List<Reimbursement> getAll() {
        return null;
    }

    public List<Reimbursement> getReimbursementByStatusId(String status_id) {
        List<Reimbursement> reimbursement = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursements WHERE status_id=(?) ");
            ps.setString(1, status_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                reimbursement.add(new Reimbursement(rs.getString("id"), rs.getBigDecimal("amount"), rs.getTimestamp("submitted"),
                        rs.getTimestamp("resolved"), rs.getString("description"), rs.getBlob("receipt"), rs.getString("payment_id"),
                        rs.getString("author_id"), rs.getString("resolver_id"), rs.getString("status_id"), rs.getString("type_id")));
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when trying to get the status of the reimbursement");
        }
        return reimbursement;
    }
        public List<Reimbursement> getReimbursementByTypeId(String type_id) {
            List<Reimbursement> reimbursement = new ArrayList<>();
            try(Connection con = ConnectionFactory.getInstance().getConnection()) {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursements WHERE type_id=(?) ");
                ps.setString(1, type_id);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    reimbursement.add(new Reimbursement(rs.getString("id"), rs.getBigDecimal("amount"), rs.getTimestamp("submitted"),
                            rs.getTimestamp("resolved"), rs.getString("description"), rs.getBlob("receipt"), rs.getString("payment_id"),
                            rs.getString("author_id"), rs.getString("resolver_id"), rs.getString("status_id"), rs.getString("type_id")));
                }
            } catch (SQLException e) {
                throw new InvalidSQLException("An error occurred when trying to get the status of the reimbursement");
            }
            return reimbursement;
    }
}