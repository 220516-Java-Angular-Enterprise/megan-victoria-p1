package com.revature.ers.daos;

import com.revature.ers.models.User_Role;
import com.revature.ers.utils.custom_exceptions.InvalidSQLException;
import com.revature.ers.utils.database.ConnectionFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User_RoleDAO implements CrudDAO<User_Role> {

    @Override
    public void save(User_Role obj) {

    }

    @Override
    public void update(User_Role obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public User_Role getById(String id) {
        return null;
    }

    @Override
    public List<User_Role> getAll() {
        return null;
    }
}
