/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import DAO.Nhanvien;
import DAO.NhanvienDAO;
import Tools.Dialog;
import Tools.Kiemthu;
import Tools.Tienich;
import java.awt.Color;

import java.awt.Font;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chu Ai Duc
 */
public class formNV extends javax.swing.JInternalFrame implements Kiemthu {

    NhanvienDAO dao = new NhanvienDAO();

    DefaultTableModel model;

    /**
     * Creates new form formNV
     */
    public formNV() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        tblNV.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblNV.getTableHeader().setOpaque(false);
        tblNV.getTableHeader().setBackground(new java.awt.Color(32, 136, 203));
        tblNV.getTableHeader().setForeground(new java.awt.Color(255, 255, 255));
        loadData();
        checkNew();

    }

    public void checkNew() {
        txtManv.setText("");
        txtHoten.setText("");
        txtSDT.setText("");
        txtDiachi.setText("");
        txtsearch.setText("");
        cboGioitinh.setSelectedIndex(0);
        cboVaitro.setSelectedIndex(0);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        btnAdd.setEnabled(true);
        txtManv.setEnabled(true);
    }

    String gioitinh(boolean i) {
        if (i) {
            return "Nữ";
        } else {
            return "Nam";
        }
    }

    String vaitro(boolean i) {
        if (i) {
            return "Admin";
        } else {
            return "Nhân viên";
        }
    }

    public void loadData() {
        try {
            dao.reLoad();
            ArrayList<Nhanvien> list = dao.laydanhsach();
            model = (DefaultTableModel) tblNV.getModel();
            model.setRowCount(0);
            for (int i = 0; i < list.size(); i++) {
                model.addRow(new Object[]{list.get(i).getManv(),
                    list.get(i).getHoten(),
                    gioitinh(list.get(i).isGt()),
                    list.get(i).getDiachi(),
                    list.get(i).getSdt(),
                    vaitro(list.get(i).isVaitro())
                });
            }
        } catch (Exception e) {
            Dialog.ThongBao(this, "Ket noi Database That bai");
        }
    }

    public boolean getGTDB(int x) {
        if (x == 1) {
            return true;
        } else {
            return false;
        }
    }

    public int insert() {
        Tienich.writeQR(getModel().getManv(), getModel().getMatkhau());
        return dao.addToDB(getModel());

    }

    Nhanvien getModel() {
        String manv = txtManv.getText();
        String hoten = txtHoten.getText();
        String matkhau = "0000";
        String sdt = txtSDT.getText();
        String diachi = txtDiachi.getText();
        int gt = cboGioitinh.getSelectedIndex();
        int vaitro = cboVaitro.getSelectedIndex();
        Nhanvien nv = new Nhanvien(manv, hoten, matkhau, getGTDB(gt), sdt, diachi, getGTDB(vaitro), manv + ".png");
        return nv;
    }

    public int update() {
        dao.reLoad();
        Nhanvien a = getModel();
        a.setMatkhau(dao.find(a.getManv()).getMatkhau());
        System.out.println(a.getMatkhau());
        return dao.updateToDB(a);
    }

    public int delete() {
        String manv = txtManv.getText();
        Nhanvien newnv = dao.find(manv);
        return dao.deleteFromDB(newnv);
    }

    public ArrayList<Nhanvien> search() {
        dao.reLoad();
        ArrayList<Nhanvien> ketquatimkiem = new ArrayList<>();
        String value = txtsearch.getText().trim().toUpperCase();
        ArrayList<Nhanvien> ds = dao.laydanhsach();
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getManv().toUpperCase().contains(value) | ds.get(i).getHoten().toUpperCase().contains(value)
                    | ds.get(i).getDiachi().toUpperCase().contains(value)
                    | ds.get(i).getSdt().toUpperCase().contains(value)) {

                ketquatimkiem.add(ds.get(i));
            }
        }
        return ketquatimkiem;
    }

    public int returnIdx(boolean boo) {
        if (boo) {
            return 1;
        } else {
            return 0;
        }
    }

    public formNV(DefaultTableModel model, JButton btnAdd, JButton btnDelete, JButton btnNew, JButton btnSearch, JButton btnUpdate, JComboBox<String> cboGioitinh, JComboBox<String> cboVaitro, JLabel jLabel1, JLabel jLabel2, JLabel jLabel3, JLabel jLabel4, JLabel jLabel5, JLabel jLabel6, JLabel jLabel7, JLabel jLabel8, JPanel jPanel1, JPanel jPanel2, JScrollPane jScrollPane2, JTable tblNV, JTextField txtDiachi, JTextField txtHoten, JTextField txtManv, JTextField txtPassword, JTextField txtSDT, JTextField txtsearch) {
        this.model = model;
        this.btnAdd = btnAdd;
        this.btnDelete = btnDelete;
        this.btnNew = btnNew;
        this.btnSearch = btnSearch;
        this.btnUpdate = btnUpdate;
        this.cboGioitinh = cboGioitinh;
        this.cboVaitro = cboVaitro;
        this.jLabel1 = jLabel1;
        this.jLabel2 = jLabel2;
        this.jLabel3 = jLabel3;
        this.jLabel5 = jLabel5;
        this.jLabel6 = jLabel6;
        this.jLabel7 = jLabel7;
        this.jLabel8 = jLabel8;
        this.jPanel1 = jPanel1;
        this.jScrollPane2 = jScrollPane2;
        this.tblNV = tblNV;
        this.txtDiachi = txtDiachi;
        this.txtHoten = txtHoten;
        this.txtManv = txtManv;
        this.txtSDT = txtSDT;
        this.txtsearch = txtsearch;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtManv = new javax.swing.JTextField();
        txtHoten = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNV = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtDiachi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cboVaitro = new javax.swing.JComboBox<>();
        cboGioitinh = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnSearch2 = new javax.swing.JButton();
        btnInfo = new javax.swing.JButton();
        menu = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Quản lý nhân viên");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(782, 495));
        jPanel1.setPreferredSize(new java.awt.Dimension(782, 495));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Họ và tên");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mã nhân viên");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Giới tính");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Số điện thoại");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, -1, 20));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Địa chỉ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, -1));

        txtManv.setBackground(new java.awt.Color(0,0,0,0));
        txtManv.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txtManv.setForeground(new java.awt.Color(255, 255, 204));
        txtManv.setBorder(null);
        txtManv.setCaretColor(new java.awt.Color(255, 255, 255));
        txtManv.setOpaque(false);
        jPanel1.add(txtManv, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 200, 35));

        txtHoten.setBackground(new java.awt.Color(0,0,0,0));
        txtHoten.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txtHoten.setForeground(new java.awt.Color(255, 255, 204));
        txtHoten.setBorder(null);
        txtHoten.setCaretColor(new java.awt.Color(255, 255, 255));
        txtHoten.setOpaque(false);
        jPanel1.add(txtHoten, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 200, 35));

        txtSDT.setBackground(new java.awt.Color(0,0,0,0));
        txtSDT.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txtSDT.setForeground(new java.awt.Color(255, 255, 204));
        txtSDT.setBorder(null);
        txtSDT.setCaretColor(new java.awt.Color(255, 255, 255));
        txtSDT.setOpaque(false);
        jPanel1.add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 260, 35));

        tblNV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Họ và tên", "Giới tính", "Địa chỉ", "Điện thoại", "Vai trò"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNV.setFocusable(false);
        tblNV.setOpaque(false);
        tblNV.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblNV.setRowHeight(30);
        tblNV.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblNV.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblNV.setShowVerticalLines(false);
        tblNV.getTableHeader().setBackground(new java.awt.Color(32,136,203));
        tblNV.getTableHeader().setReorderingAllowed(false);
        tblNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNVMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNV);
        if (tblNV.getColumnModel().getColumnCount() > 0) {
            tblNV.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblNV.getColumnModel().getColumn(2).setPreferredWidth(40);
            tblNV.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblNV.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, 740, 190));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Danh sách nhân viên");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, -1));

        txtDiachi.setBackground(new java.awt.Color(0,0,0,0));
        txtDiachi.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txtDiachi.setForeground(new java.awt.Color(255, 255, 204));
        txtDiachi.setBorder(null);
        txtDiachi.setCaretColor(new java.awt.Color(255, 255, 255));
        txtDiachi.setOpaque(false);
        jPanel1.add(txtDiachi, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, 260, 35));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Vai trò");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, -1, -1));

        cboVaitro.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        cboVaitro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân viên", "Admin" }));
        jPanel1.add(cboVaitro, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 150, -1, -1));

        cboGioitinh.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        cboGioitinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        jPanel1.add(cboGioitinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 200, 1));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 110, 260, 1));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 200, 1));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, 260, 1));

        btnAdd.setBackground(new java.awt.Color(204, 204, 255));
        btnAdd.setText("Thêm");
        btnAdd.setBorder(null);
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnAdd.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnAdd.setPreferredSize(new java.awt.Dimension(80, 40));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, -1, -1));

        btnUpdate.setBackground(new java.awt.Color(204, 204, 255));
        btnUpdate.setBorder(null);
        btnUpdate.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnUpdate.setLabel("Cập nhật");
        btnUpdate.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnUpdate.setPreferredSize(new java.awt.Dimension(85, 40));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 101, -1));

        btnDelete.setBackground(new java.awt.Color(204, 204, 255));
        btnDelete.setText("Xóa");
        btnDelete.setBorder(null);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnDelete.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnDelete.setPreferredSize(new java.awt.Dimension(80, 40));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, 82, -1));

        btnNew.setBackground(new java.awt.Color(204, 204, 255));
        btnNew.setText("Mới");
        btnNew.setBorder(null);
        btnNew.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNew.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnNew.setPreferredSize(new java.awt.Dimension(80, 40));
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jPanel1.add(btnNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 200, -1, -1));

        txtsearch.setPreferredSize(new java.awt.Dimension(200, 35));
        jPanel1.add(txtsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 200, -1, -1));

        btnSearch.setBackground(new java.awt.Color(255, 204, 204));
        btnSearch.setText("Tìm");
        btnSearch.setBorder(null);
        btnSearch.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnSearch.setMargin(new java.awt.Insets(5, 10, 5, 10));
        btnSearch.setPreferredSize(new java.awt.Dimension(80, 40));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 200, 83, -1));

        btnSearch2.setBackground(new java.awt.Color(255, 204, 204));
        btnSearch2.setText("Reset");
        btnSearch2.setBorder(null);
        btnSearch2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnSearch2.setMargin(new java.awt.Insets(5, 10, 5, 10));
        btnSearch2.setPreferredSize(new java.awt.Dimension(80, 40));
        btnSearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 490, 80, -1));

        btnInfo.setBackground(new java.awt.Color(204, 204, 255));
        btnInfo.setText("Thông tin của bạn");
        btnInfo.setBorder(null);
        btnInfo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnInfo.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnInfo.setPreferredSize(new java.awt.Dimension(80, 40));
        btnInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoActionPerformed(evt);
            }
        });
        jPanel1.add(btnInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 160, -1));

        menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundform.png"))); // NOI18N
        jPanel1.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 560));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if (checknull(new JTextField[]{txtManv, txtHoten, txtSDT, txtDiachi})
                && checkloidinhdang(new JTextField[]{txtManv, txtHoten, txtSDT, txtDiachi})) {
            int x = insert();
            if (x != 0) {
                loadData();
                btnAdd.setEnabled(false);
                txtManv.setEnabled(false);
                Dialog.ThongBao(this, "thanh cong");
            } else {
                Dialog.ThongBao(this, "that bai");
            }
        }

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        checkNew();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        if (checknull(new JTextField[]{txtHoten, txtSDT, txtDiachi})
                && checkloidinhdang(new JTextField[]{ txtHoten, txtSDT, txtDiachi})) {
            int x = update();
            if (x != 0) {
                loadData();
                checkNew();
                Dialog.ThongBao(this, "thanh cong");
            } else {
                Dialog.ThongBao(this, "that bai");
            }
        }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNVMouseClicked
        // TODO add your handling code here:
        String manv = (String) tblNV.getValueAt(tblNV.getSelectedRow(), 0);
        Nhanvien newnv = dao.find(manv);
        txtManv.setText(newnv.getManv());
        txtHoten.setText(newnv.getHoten());
        txtSDT.setText(newnv.getSdt());
        txtDiachi.setText(newnv.getDiachi());
        cboGioitinh.setSelectedIndex(returnIdx(newnv.isGt()));
        cboVaitro.setSelectedIndex(returnIdx(newnv.isVaitro()));
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);
        btnAdd.setEnabled(false);
        txtManv.setEnabled(false);
    }//GEN-LAST:event_tblNVMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        dao.reLoad();
        if (checknull(new JTextField[]{txtManv})) {
            if(txtManv.getText().equals(Tienich.user.getManv())){
                Dialog.ThongBao(this, "Không xóa chính mình");
                return;
            }
            if(dao.find(txtManv.getText()).isVaitro()){
                Dialog.ThongBao(this, "Bạn không được xóa Admin");
                return;
            }
            int x = delete();
            if (x != 0) {
                loadData();
                checkNew();
                Dialog.ThongBao(this, "thanh cong");
            } else {
                Dialog.ThongBao(this, "that bai");
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        model = (DefaultTableModel) tblNV.getModel();
        model.setRowCount(0);
        for (Nhanvien a : search()) {
            model.addRow(new Object[]{
                a.getManv(),
                a.getHoten(),
                gioitinh(a.isGt()),
                a.getSdt(),
                a.getDiachi(),
                vaitro(a.isVaitro())
            });
        }

    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch2ActionPerformed
        // TODO add your handling code here:

        if (tblNV.getSelectedRow() >= 0) {

            dao.reLoad();
            String manv = (String) tblNV.getValueAt(tblNV.getSelectedRow(), 0);
            Nhanvien newnv = dao.find(manv);
            newnv.setMatkhau("0000");
            if (Dialog.XacNhan(this, "Bạn chắc chắn muốn reset mật khẩu của:\n" + newnv.getHoten() + " ?")) {
                int x = dao.updateToDB(newnv);
                if (x == 1) {
                    Tienich.writeQR(manv, "0000");
                    Dialog.ThongBao(this, "Reset mật khẩu thành công");
                } else {
                    Dialog.ThongBao(this, "Reset mậu khẩu thất bại");
                }

            }
        } else {
            Dialog.ThongBao(this, "Bạn chưa chọn nhân viên");
        }
    }//GEN-LAST:event_btnSearch2ActionPerformed

    private void btnInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoActionPerformed
        // TODO add your handling code here:
        JDesktopPane forms = this.getDesktopPane();
        this.dispose();
        formInfo form = new formInfo();
        forms.removeAll();
        forms.add(form);
        form.show();
    }//GEN-LAST:event_btnInfoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnInfo;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch2;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboGioitinh;
    private javax.swing.JComboBox<String> cboVaitro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel menu;
    private javax.swing.JTable tblNV;
    private javax.swing.JTextField txtDiachi;
    private javax.swing.JTextField txtHoten;
    private javax.swing.JTextField txtManv;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtsearch;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setTen() {
        txtDiachi.setName("txtDiachi");
        txtHoten.setName("txtHoten");
        txtManv.setName("txtManv");
        txtSDT.setName("txtSDT");
    }

    @Override
    public boolean checkloidinhdang(JTextField[] array) {
        setTen();
        dao.reLoad();
        String regexp = "([a-zA-Z\\u0080-\\u9fff]+\\s)+[a-zA-Z\\u0080-\\u9fff]+";
        String regexpPhone = "0[^0][0-9]{8}";
        for (JTextField field : array) {
            String text = field.getText();
            if (field.getName().equals("txtHoten")) {
                if (!text.matches(regexp)) {
                    field.requestFocus();
                    Dialog.ThongBao(this, "Không đúng định dạng!");
                    return false;
                }
            }
            if (field.getName().equals("txtSDT")) {
                if (!text.matches(regexpPhone)) {
                    field.requestFocus();
                    Dialog.ThongBao(this, "Số điện thoại không đúng định dạng");
                    return false;
                }
            }
            if (field.getName().equals("txtManv")) {
                if (text.contains(" ")) {
                    field.requestFocus();
                    Dialog.ThongBao(this, "Mã nhân viên không được có dấu cách");
                    return false;
                }
                if(dao.find(text)!=null){
                    field.requestFocus();
                    Dialog.ThongBao(this, "Mã nhân viên không được trùng");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean checknull(JTextField[] array) {
        for (JTextField field : array) {
            String text = field.getText();
            if (text.equals("")) {
                field.requestFocus();
                Dialog.ThongBao(this, "Không để trống!");
                return false;
            }
        }
        return true;
    }

}
