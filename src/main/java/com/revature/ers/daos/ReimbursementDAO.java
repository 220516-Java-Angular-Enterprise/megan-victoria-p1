package com.revature.ers.daos;

import com.revature.ers.models.Reimbursement;
import com.revature.ers.models.User;
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


//    USER CREATE REIMBURSEMENT
    @Override
    public void save(Reimbursement obj) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO reimbursements (id,amount,submitted,resolved,description,receipt,payment_id,author_id,resolver_id,status_id,type_id) VALUES(?,?,?,?,?,?,?,?,?,?,?) ");
            ps.setString(1, obj.getId());
            ps.setBigDecimal(2, obj.getAmount());
            ps.setTimestamp(3, obj.getSubmitted());
            ps.setTimestamp(4, obj.getResolved());
            ps.setString(5, obj.getDescription());
            ps.setBlob(6, obj.getReceipt());
            ps.setString(7, obj.getPayment_id());
            ps.setString(8, obj.getAuthor_id());
            ps.setString(9, obj.getResolver_id());
            ps.setString(10, obj.getStatus_id());
            ps.setString(11, obj.getType_id());
            ps.executeUpdate();
        } catch (SQLException e) {
//            throw new InvalidSQLException("An error occurred when trying to save a Reimbursement to the database");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());

        }
    }

    @Override
    public void update(Reimbursement obj) {

    }

    @Override
    public void delete(String id) {


    }


//    VIEW REIMB DETAILS (FIN MAN AND USER)
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


//    FIN MAN GET ALL REIMBS
    @Override
    public List<Reimbursement> getAll() {
        List<Reimbursement> reimbs = new ArrayList<>();

        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursements");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reimbursement reimb = new Reimbursement();
                reimb.setId(rs.getString("id"));
                reimb.setAuthor_id(rs.getString("author_id"));
                reimb.setAmount(rs.getBigDecimal("amount"));
                reimb.setSubmitted(rs.getTimestamp("submitted"));
                reimb.setResolved(rs.getTimestamp("resolved"));
                reimb.setDescription(rs.getString("description"));
                reimb.setType_id(rs.getString("type_id"));
                reimb.setStatus_id(rs.getString("status_id"));

                reimbs.add(reimb);
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }
        return reimbs;
    }

//    public List<Reimbursement> getReimbursementByUserId(String author_id) {
//        List<Reimbursement> reimbursement = new ArrayList<>();
//        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
//            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursements WHERE author_id=(?) ");
//            ps.setString(1, author_id);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                reimbursement.add(new Reimbursement(rs.getString("id"), rs.getBigDecimal("amount"), rs.getTimestamp("submitted"),
//                        rs.getTimestamp("resolved"), rs.getString("description"), rs.getBlob("receipt"), rs.getString("payment_id"),
//                        rs.getString("author_id"), rs.getString("resolver_id"), rs.getString("status_id"), rs.getString("type_id")));
//            }
//        } catch (SQLException e) {
//            throw new InvalidSQLException("An error occurred when trying to get the status of the reimbursement");
//        }
//        return reimbursement;
//    }


//    FIN MAN GET REIMB BY STATUS
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

//    FIN MAN GET REIMB BY TYPE
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

//    FIN MAN UPDATE STATUS
    public void updateStatus(String status_id, String id) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE reimbursements SET status_id = ? WHERE id = ?");
            ps.setString(1, status_id);
            ps.setString(2, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to update data from to the database.");
        }
    }

//    USER UPDATE REIMB AMOUNT
    public void updateReimbAmount(BigDecimal amount, String id) {
    try(Connection con = ConnectionFactory.getInstance().getConnection()) {
        PreparedStatement ps = con.prepareStatement("UPDATE reimbursements SET amount = ? WHERE id = ?");
        ps.setBigDecimal(1, amount);
        ps.setString(2, id);
        ps.executeUpdate();

    } catch (SQLException e) {
        throw new InvalidSQLException("An error occurred when tyring to update data from to the database.");
    }
}


//    USER UPDATE RIMB DESCRIPTION
    public void updateReimbDescription(String description, String id) {
    try(Connection con = ConnectionFactory.getInstance().getConnection()) {
        PreparedStatement ps = con.prepareStatement("UPDATE reimbursements SET description = ? WHERE id = ?");
        ps.setString(1, description);
        ps.setString(2, id);
        ps.executeUpdate();

    } catch (SQLException e) {
        throw new InvalidSQLException("An error occurred when tyring to update data from to the database.");
    }
}

//USER GET OWN REIMB
    public List<Reimbursement> getAllByUser(String author_id) {
        List<Reimbursement> reimbs = new ArrayList<>();

        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursements WHERE author_id = ?");
            ps.setString(1, author_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reimbursement reimb = new Reimbursement();
                reimb.setId(rs.getString("id"));
                reimb.setAmount(rs.getBigDecimal("amount"));
                reimb.setSubmitted(rs.getTimestamp("submitted"));
                reimb.setResolved(rs.getTimestamp("resolved"));
                reimb.setDescription(rs.getString("description"));
                reimb.setType_id(rs.getString("type_id"));
                reimb.setStatus_id(rs.getString("status_id"));

                reimbs.add(reimb);
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }
        return reimbs;
    }

}