package com.maid.controllers;

import com.maid.models.Buku;
import com.maid.views.Menu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class BukuController {

    Buku buku = new Buku();
    private final String[] koloDB = {"nama_aset", "jumlah_aset", "harga_aset", "nilai_aset"};
    private final String[] namaKolom = {"Nama", "Jumlah", "Harga", "Nilai"};

    public void index(Menu menu) {
        for (String s : namaKolom) {
            menu.getModelTableData().addColumn(s);
        }
        try {
            showTableData(menu.getModelTableData());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<String> getData() {
        return new ArrayList<>();
    }

    private void showTableData(DefaultTableModel model) throws SQLException {
        model.setRowCount(0);
        ResultSet resultSet = buku.get();
        Object[] row = new Object[6];
        while (resultSet.next()) {
            for (int i = 0; i < 4; i++) {
                row[i] = resultSet.getString(koloDB[i]);
            }
            model.addRow(row);
        }
    }

    public boolean addData(Menu menu) {
        ArrayList<String> data = new ArrayList<>(Arrays.asList(
                menu.getEt_nama().getText(),
                menu.getEt_jumlah().getText(),
                menu.getEt_harga().getText(),
                String.valueOf(Double.parseDouble(menu.getEt_harga().getText()) * Double.parseDouble(menu.getEt_jumlah().getText()))
        ));
        try {
            buku.insert(data);
            showTableData(menu.getModelTableData());
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean updateData(Menu menu, String selectedID) {
        ArrayList<String> data = new ArrayList<>(Arrays.asList(
                menu.getEt_nama().getText(),
                menu.getEt_jumlah().getText(),
                menu.getEt_harga().getText(),
                String.valueOf(Double.parseDouble(menu.getEt_harga().getText()) * Double.parseDouble(menu.getEt_jumlah().getText()))
        ));

        try {
            buku.update(data, selectedID);
            showTableData(menu.getModelTableData());
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean deleteData(Menu menu, String selectedID) {
        try {
            buku.delete(selectedID);
            showTableData(menu.getModelTableData());
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean clearData(Menu menu) {
        JTextField[] buttons = {
                menu.getEt_nama(),
                menu.getEt_jumlah(),
                menu.getEt_harga(),
        };
        for (JTextField button : buttons) {
            button.setText("");
        }
        return menu.getEt_nama() != null;
    }

    public boolean checkInput(Menu menu, ActionEvent e){
        if (e.getSource() == menu.getBtn_clear())
            return true;
        ArrayList<String> data = new ArrayList<>(Arrays.asList(
                menu.getEt_nama().getText(),
                menu.getEt_jumlah().getText(),
                menu.getEt_harga().getText()
        ));
        for (String datum : data) {
            if (datum.equals(""))
                return false;
        }
        return true;
    }

}
