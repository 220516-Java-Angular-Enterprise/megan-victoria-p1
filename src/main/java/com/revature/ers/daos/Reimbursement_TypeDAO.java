package com.revature.ers.daos;

import com.revature.ers.models.Reimbursement_Type;
import com.revature.ers.utils.database.DatabaseConnection;
import com.revature.ers.utils.custom_exceptions.InvalidSQLException;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Reimbursement_TypeDAO implements CrudDAO<Reimbursement_Type> {

    @Override
    public void save(Reimbursement_Type obj) {

    }

    @Override
    public void update(Reimbursement_Type obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Reimbursement_Type getById(String id) {
        return null;
    }

    @Override
    public List<Reimbursement_Type> getAll() {
        return null;
    }
}
