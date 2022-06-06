package com.revature.ers.daos;

import com.revature.ers.models.User;
import com.revature.ers.utils.custom_exceptions.InvalidSQLException;
import com.revature.ers.utils.database.DatabaseConnection;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<User> {
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(User obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, username, email, password, given_name, surname, is_active, role_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUsername());
            ps.setString(3, obj.getEmail());
            ps.setString(4, obj.getPassword());
            ps.setString(5, obj.getGiven_name());
            ps.setString(6, obj.getSurname());
            ps.setBoolean(7, obj.isIs_active());
            ps.setString(8, obj.getRole_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(User obj) {

    }

    @Override
    public void delete(String id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to update data from to the database.");
        }
    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
