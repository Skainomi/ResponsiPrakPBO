package com.maid.views;

import com.maid.controllers.BukuController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Menu extends JFrame implements ActionListener, MouseListener {
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
    Menu menu;

    public JTable getTb_tableData() {
        return tb_tableData;
    }

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
        menu = this;
        JButton[] buttons = {
                btn_tambah,
                btn_update,
                btn_delete,
                btn_clear
        };
        for (JButton button : buttons) {
            button.addActionListener(this);
        }
        tb_tableData.addMouseListener(this);
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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(tb_tableData)){
            selectedID = bukuController.getClickedData(menu);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
