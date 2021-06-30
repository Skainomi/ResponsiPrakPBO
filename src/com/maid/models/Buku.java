package com.maid.models;

import java.sql.*;
import java.util.ArrayList;

public class Buku implements Model {

    private Statement statement;
    Connection connection;


    public Buku() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/aset_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root",
                    ""
            );
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void insert(ArrayList<String> data) throws SQLException {
        statement.executeUpdate("INSERT INTO `aset` (`nama_aset`, `jumlah_aset`, `harga_aset`, `nilai_aset`) VALUES ('" + data.get(0) + "', '" + data.get(1) + "', '" + data.get(2) + "', '" + data.get(3) + "')");
    }

    @Override
    public void update(ArrayList<String> data, String primaryKey) throws SQLException {
        statement.execute("UPDATE `aset` SET `nama_aset` = '" + data.get(0) + "', `jumlah_aset` = '" + data.get(1) + "', `harga_aset` = '" + data.get(2) + "', `nilai_aset` = '" + data.get(3) + "' WHERE `aset`.`nama_aset` = '" + primaryKey + "'");
    }

    @Override
    public ResultSet get() throws SQLException {
        ResultSet resultSet;
        resultSet = statement.executeQuery("SELECT * FROM `aset`");
        return resultSet;
    }

    @Override
    public void delete(String data) throws SQLException {
        statement.execute("DELETE FROM `aset` WHERE `aset`.`nama_aset` = '"+ data +"'");
    }

}
