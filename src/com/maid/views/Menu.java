package com.maid.views;

import com.maid.controllers.BukuController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Menu extends JFrame implements ActionListener {
    private JPanel MainPanel;
    private JTextField et_nama;
    private JTextField et_jumlah;
    private JTextField et_harga;
    private JButton btn_tambah;
    private JButton btn_update;
    private JButton btn_delete;
    private JButton btn_clear;
    private JTable tb_tableData;
    private JScrollPane sp_scrollTable;
    DefaultTableModel model;
    String selectedID;

    public JButton getBtn_clear() {
        return btn_clear;
    }

    public JTextField getEt_nama() {
        return et_nama;
    }

    public JTextField getEt_jumlah() {
        return et_jumlah;
    }

    public JTextField getEt_harga() {
        return et_harga;
    }

    BukuController bukuController = new BukuController();

    public Menu(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        this.setVisible(true);
        model = (DefaultTableModel) tb_tableData.getModel();
        bukuController.index(this);
        JButton[] buttons = {
                btn_tambah,
                btn_update,
                btn_delete,
                btn_clear
        };
        for (JButton button : buttons) {
            button.addActionListener(this);
        }
        tb_tableData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JTextField[] jTextFields = {
                        getEt_nama(),
                        getEt_jumlah(),
                        getEt_harga(),
                };
                ArrayList<String> data = new ArrayList<>(Arrays.asList(
                        String.valueOf(getModelTableData().getValueAt(tb_tableData.getSelectedRow(), 0)),
                        String.valueOf(getModelTableData().getValueAt(tb_tableData.getSelectedRow(), 1)),
                        String.valueOf(getModelTableData().getValueAt(tb_tableData.getSelectedRow(), 2))
                ));
                for (int i = 0; i < jTextFields.length; i++) {
                    jTextFields[i].setText(data.get(i));
                }
                selectedID = String.valueOf(getModelTableData().getValueAt(tb_tableData.getSelectedRow(), 0));
            }
        });
    }

    public DefaultTableModel getModelTableData() {
        return (DefaultTableModel) tb_tableData.getModel();
    }

    private boolean checkSelectedID() {
        if (selectedID == null) {
            JOptionPane.showMessageDialog(null, "Perintah Berhasil!!");
            return true;
        }
        return false;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (!bukuController.checkInput(this, e)) {
            JOptionPane.showMessageDialog(null, "Isi Semua Input!!");
            return;
        }
        boolean status = false;
        Object source = e.getSource();
        if (btn_tambah.equals(source)) {
            status = bukuController.addData(this);
            bukuController.clearData(this);
        } else if (btn_clear.equals(source)) {
            status = bukuController.clearData(this);
        } else if (btn_delete.equals(source)) {
            if (checkSelectedID()) return;
            status = bukuController.deleteData(this, selectedID);
            bukuController.clearData(this);
        } else if (btn_update.equals(source)) {
            if (checkSelectedID()) return;
            status = bukuController.updateData(this, selectedID);
            bukuController.clearData(this);
        }
        if (status) {
            JOptionPane.showMessageDialog(null, "Perintah Berhasil!!");
            return;
        }
        JOptionPane.showMessageDialog(null, "Perintah Gagal!!");
    }
}
