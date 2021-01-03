/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import DAO.LoaiSP;
import DAO.LoaiSPDAO;
import DAO.Nhacungcap;
import DAO.NhacungcapDAO;
import DAO.Sanpham;
import DAO.SanphamDAO;
import Tools.Dialog;
import Tools.Kiemthu;
import Tools.Tienich;
import java.awt.Font;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chu Ai Duc
 */
public class formSP extends javax.swing.JInternalFrame implements Kiemthu {

    SanphamDAO dao = new SanphamDAO();
    LoaiSPDAO daoLoai = new LoaiSPDAO();
    DefaultTableModel model;
    String tenfile = "";
    NhacungcapDAO daoncc = new NhacungcapDAO();
    Locale localeVN = new Locale("vi", "VN");
    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

    /**
     * Creates new form formSP
     */
    public formSP() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
        daoncc.reLoad();
        dao.reLoad();
        daoLoai.reLoad();
        addML();
        addNCC();
        loadData();
        checkNew();
        tblSP.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblSP.getTableHeader().setOpaque(false);
        tblSP.getTableHeader().setBackground(new java.awt.Color(32, 136, 203));
        tblSP.getTableHeader().setForeground(new java.awt.Color(255, 255, 255));
    }

    public void loadData() {
        try {
            dao.reLoad();
            ArrayList<Sanpham> list = dao.laydanhsach();
            model = (DefaultTableModel) tblSP.getModel();
            model.setRowCount(0);
            for (int i = 0; i < list.size(); i++) {
                model.addRow(new Object[]{list.get(i).getMasp(),
                    list.get(i).getTensp(),
                    currencyVN.format(list.get(i).getDongia()),
                    daoncc.find(list.get(i).getMancc()).getTenncc(),
                    daoLoai.find(list.get(i).getMaloai()).getTenloai(),
                    list.get(i).getSoluong()
                });
            }
        } catch (Exception e) {
            Dialog.ThongBao(this, "Ket noi Database That bai");
        }
    }

    public int update() {
        return dao.updateToDB(getModel());
    }

    public int delete() {
        String masp = txtMasp.getText();
        Sanpham newsp = dao.find(masp);
        return dao.deleteFromDB(newsp);
    }

    public void checkNew() {
        txtMasp.setText("");
        txtTensp.setText("");
        cboNCC.setSelectedIndex(0);
        txtDongia.setText("");
        txtsl.setText("");
        txtsearch.setText("");
        hinhSP.setText("Click chon hinh");
        hinhSP.setIcon(null);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        btnAdd.setEnabled(true);
        txtMasp.setEnabled(true);
    }

    public ArrayList<Sanpham> search() {
        ArrayList<Sanpham> ketquatimkiem = new ArrayList<>();
        String value = txtsearch.getText().trim().toUpperCase();
        dao.reLoad();
        ArrayList<Sanpham> ds = dao.laydanhsach();
        try {
            float giatri = Float.parseFloat(txtsearch.getText());
            if (giatri >= 0) {
                for (int i = 0; i < ds.size(); i++) {
                    if (ds.get(i).getDongia() >= giatri) {
                        ketquatimkiem.add(ds.get(i));
                    }
                }
            }
        } catch (Exception e) {
            for (int i = 0; i < ds.size(); i++) {

                if (ds.get(i).getMasp().toUpperCase().contains(value) | ds.get(i).getTensp().toUpperCase().contains(value) | ds.get(i).getMancc().toUpperCase().contains(value)) {

                    ketquatimkiem.add(ds.get(i));
                }
            }
        }
        return ketquatimkiem;
    }

    public Sanpham getModel() {
        daoncc.reLoad();
        String masp = txtMasp.getText();

        String tensp = txtTensp.getText();

        float dongia = Float.parseFloat(txtDongia.getText());

        String mancc = daoncc.laydanhsach().get(cboNCC.getSelectedIndex()).getMancc();
        System.out.println(mancc);
        int soluong = Integer.valueOf(txtsl.getText());
        System.out.println(soluong);
        int maloai = cboML.getSelectedIndex() + 1;
        System.out.println(maloai);

        System.out.println(tenfile);
        Sanpham sp = new Sanpham(masp, tensp, dongia, tenfile, mancc, maloai, soluong);
        System.out.println("ok sp");
        return sp;
    }

    public void addNCC() {
        daoncc.reLoad();
        for (Nhacungcap ncc : daoncc.laydanhsach()) {
            cboNCC.addItem(ncc.getTenncc());
        }
    }

    public void addML() {
        daoLoai.reLoad();
        for (LoaiSP loai : daoLoai.laydanhsach()) {
            cboML.addItem(loai.getTenloai());
        }
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
        hinhSP = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMasp = new javax.swing.JTextField();
        txtTensp = new javax.swing.JTextField();
        txtDongia = new javax.swing.JTextField();
        cboML = new javax.swing.JComboBox<>();
        cboNCC = new javax.swing.JComboBox<>();
        txtsl = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(860, 560));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(782, 495));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        hinhSP.setForeground(new java.awt.Color(255, 255, 255));
        hinhSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hinhSP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        hinhSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hinhSPMouseClicked(evt);
            }
        });
        jPanel1.add(hinhSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 205, 227));

        tblSP.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tblSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Tên sản phẩm", "Đơn giá", "Nhà cung cấp", "Loại sản phẩm", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSP.setFocusable(false);
        tblSP.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblSP.setRowHeight(30);
        tblSP.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblSP.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblSP.setShowVerticalLines(false);
        tblSP.getTableHeader().setReorderingAllowed(false);
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSP);
        if (tblSP.getColumnModel().getColumnCount() > 0) {
            tblSP.getColumnModel().getColumn(0).setPreferredWidth(25);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, 700, 180));

        jPanel2.setBackground(new java.awt.Color(0,0,0,0));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mã sản phẩm");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên sản phẩm");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nhà cung cấp");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Đơn giá");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Số lượng");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Loại");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 230, -1, -1));

        txtMasp.setBackground(new java.awt.Color(0,0,0,0)
        );
        txtMasp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMasp.setForeground(new java.awt.Color(255, 255, 102));
        txtMasp.setBorder(null);
        txtMasp.setCaretColor(new java.awt.Color(153, 255, 204));
        txtMasp.setOpaque(false);
        txtMasp.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel2.add(txtMasp, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 20, -1, -1));

        txtTensp.setBackground(new java.awt.Color(0,0,0,0)
        );
        txtTensp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTensp.setForeground(new java.awt.Color(255, 255, 102));
        txtTensp.setBorder(null);
        txtTensp.setCaretColor(new java.awt.Color(153, 255, 204));
        txtTensp.setOpaque(false);
        txtTensp.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel2.add(txtTensp, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 70, -1, -1));

        txtDongia.setBackground(new java.awt.Color(0,0,0,0)
        );
        txtDongia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDongia.setForeground(new java.awt.Color(255, 255, 102));
        txtDongia.setBorder(null);
        txtDongia.setCaretColor(new java.awt.Color(153, 255, 204));
        txtDongia.setOpaque(false);
        txtDongia.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel2.add(txtDongia, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 160, -1, -1));

        cboML.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel2.add(cboML, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, 130, 30));

        cboNCC.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel2.add(cboNCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 120, 200, 30));

        txtsl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtslKeyTyped(evt);
            }
        });
        jPanel2.add(txtsl, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 100, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Info.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(348, 118, 40, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 520, 280));

        jPanel3.setBackground(new java.awt.Color(0,0,0,0));

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

        btnUpdate.setBackground(new java.awt.Color(204, 204, 255));
        btnUpdate.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnUpdate.setLabel("Cập nhật");
        btnUpdate.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnUpdate.setPreferredSize(new java.awt.Dimension(80, 40));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

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

        txtsearch.setPreferredSize(new java.awt.Dimension(200, 35));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 740, 60));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 120, 200, 1));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 210, 200, 1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 70, 200, 1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundform.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 560));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if (checknull(new JTextField[]{txtMasp, txtTensp, txtDongia, txtsl})
                && checkloidinhdang(new JTextField[]{txtMasp, txtTensp, txtDongia, txtsl})) {
            if (Tienich.loggedAdmin()) {
                int x = dao.addToDB(getModel());
                if (x != 0) {
                    loadData();
                    checkNew();
                    btnAdd.setEnabled(false);
                    Dialog.ThongBao(this, "Thành công");
                } else {
                    Dialog.ThongBao(this, "Thất bại");
                }
            } else {
                Dialog.ThongBao(this, "Quyền Admin");
            }
        }


    }//GEN-LAST:event_btnAddActionPerformed

    private void hinhSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hinhSPMouseClicked
        // TODO add your handling code here:
        JFileChooser choose = new JFileChooser();
        int x = choose.showOpenDialog(this);
        if (x == JFileChooser.APPROVE_OPTION) {
            File taptin = choose.getSelectedFile();
            if (Tienich.saveLogo(taptin)) {
                hinhSP.setIcon(Tienich.readLogo(taptin.getName()));
                hinhSP.setToolTipText(taptin.getName());
                tenfile = taptin.getName();
                hinhSP.setText("");
            }
        }
    }//GEN-LAST:event_hinhSPMouseClicked

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked
        // TODO add your handling code here:
        String masp = (String) tblSP.getValueAt(tblSP.getSelectedRow(), 0);
        Sanpham newsp = dao.find(masp);
        txtMasp.setText(newsp.getMasp());
        txtTensp.setText(newsp.getTensp());
        cboML.setSelectedItem(daoLoai.find(newsp.getMaloai()).getTenloai());
        cboNCC.setSelectedItem(daoncc.find(newsp.getMancc()).getTenncc());
        txtDongia.setText(newsp.getDongia() + "");
        txtsl.setText(newsp.getSoluong() + "");
        hinhSP.setIcon(Tienich.readLogo(newsp.getHinh()));
        hinhSP.setToolTipText(newsp.getHinh());
        tenfile = newsp.getHinh();
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);
        btnAdd.setEnabled(false);
        txtMasp.setEnabled(false);
        hinhSP.setText("");
    }//GEN-LAST:event_tblSPMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        if (checknull(new JTextField[]{txtTensp, txtDongia, txtsl})
                && checkloidinhdang(new JTextField[]{txtTensp, txtDongia, txtsl})) {
            if (Tienich.loggedAdmin()) {
                int x = update();
                if (x != 0) {
                    loadData();
                    btnAdd.setEnabled(false);
                    checkNew();
                    Dialog.ThongBao(this, "thanh cong");
                } else {
                    Dialog.ThongBao(this, "that bai");
                }
            } else {
                Dialog.ThongBao(this, "Quyền Admin");
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if (Tienich.loggedAdmin()) {
            int x = delete();
            if (x != 0) {
                loadData();
                checkNew();
                Dialog.ThongBao(this, "thanh cong");
            } else {
                Dialog.ThongBao(this, "that bai");
            }
        } else {
            Dialog.ThongBao(this, "Quyền Admin");
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        checkNew();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        model = (DefaultTableModel) tblSP.getModel();
        model.setRowCount(0);
        for (Sanpham a : search()) {
            model.addRow(new Object[]{
                a.getMasp(),
                a.getTensp(),
                a.getDongia(),
                daoncc.find(a.getMancc()).getTenncc(),
                a.getSoluong()
            });
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtslKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtslKeyTyped
        // TODO add your handling code here:
        System.out.println(txtsl.getText());
    }//GEN-LAST:event_txtslKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        formNCC a=new formNCC();
        a.fillinfo(daoncc.laydanhsach().get(cboNCC.getSelectedIndex()).getMancc());
        a.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        a.setLocationRelativeTo(null);
        a.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboML;
    private javax.swing.JComboBox<String> cboNCC;
    private javax.swing.JLabel hinhSP;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable tblSP;
    private javax.swing.JTextField txtDongia;
    private javax.swing.JTextField txtMasp;
    private javax.swing.JTextField txtTensp;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txtsl;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setTen() {
        txtDongia.setName("txtDongia");
        txtMasp.setName("txtMasp");
        txtTensp.setName("txtTensp");
        txtsl.setName("txtsl");
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

    @Override
    public boolean checkloidinhdang(JTextField[] array) {
        setTen();
        dao.reLoad();
        for (JTextField field : array) {
            String text = field.getText();
            if (field.getName().equals("txtMasp")) {
                if (text.contains(" ")) {
                    field.requestFocus();
                    Dialog.ThongBao(this, "Mã sản phẩm không được có dấu cách");
                    return false;
                }
                if(dao.find(text)!=null){
                    field.requestFocus();
                    Dialog.ThongBao(this, "Mã sản phẩm không được trùng");
                    return false;
                }
            }
            if (field.getName().equals("txtsl") || field.getName().equals("txtDongia")) {
                try {
                    if (Float.parseFloat(text) < 0) {
                        Dialog.ThongBao(this, "Sai định dạng số");
                        return false;
                    }
                } catch (Exception e) {
                    Dialog.ThongBao(this, "Sai định dạng số");
                    return false;
                }
            }
        }
        return true;
    }
}
