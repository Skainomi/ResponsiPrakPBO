package com.maid.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Model {
    public void insert(ArrayList<String> data) throws SQLException;

    public void update(ArrayList<String> data, String selectedID) throws SQLException;

    public ResultSet get() throws SQLException;

    public void delete(String data) throws SQLException;
}
