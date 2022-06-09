package com.revature.ers.daos;

import com.revature.ers.models.User;
import com.revature.ers.utils.custom_exceptions.InvalidSQLException;
import com.revature.ers.utils.database.DatabaseConnection;

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
//        encryption for password is set in the ps for password. 'bf' makes max pswd 72 and has an output length of 60)
//        gen_salt generates a new salt value
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, username, email, password, given_name, surname, is_active, role_id) VALUES (?, ?, ?, crypt(?, gen_salt('bf)), ?, ?, ?, ?)");
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
        User user = new User();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users where id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user = new User(rs.getString("id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("given_name"), rs.getString("surname"), rs.getBoolean("is_active"), rs.getString("role_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }

        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGiven_name(rs.getString("given_name"));
                user.setSurname(rs.getString("surname"));
                user.setIs_active(rs.getBoolean("is_active"));
                user.setRole_id(rs.getString("role_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }
        return users;
    }

    public List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT username FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                usernames.add(rs.getString("username").toLowerCase());
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }

        return usernames;
    }
    public User getUserByUsernameAndPassword(String username, String password){
        User user = null;
        try{
            PreparedStatement ps=con.prepareStatement("SELECT* FROM users WHERE username = ? AND password = crypt(?, password)");
            ps.setString(1,username);
            ps.setString(2,password);

            ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            user = new User(rs.getString("id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("given_name"), rs.getString("surname"), rs.getBoolean("is_active"), rs.getString("role_id"));
        }
        }catch (SQLException e){
            throw new InvalidSQLException("An error occurred trying to get username and password from the database.");
        }return user;
    }

    public List<User> getUsersByUsername(String name) {
        List<User> users = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(("SELECT * FROM users WHERE username LIKE ?"));
            ps.setString(1, name + '%');
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                users.add(new User(rs.getString("id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("given_name"), rs.getString("surname"), rs.getBoolean("is_active"), rs.getString("role_id")));
            }

        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to get data from to the database.");
        }
        return users;
    }
}


