/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import DAO.Bill;
import DAO.BillDAO;
import Tools.Tienich;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chu Ai Duc
 */
public class formBill extends javax.swing.JFrame {

    /**
     * Creates new form formBill
     */
    ArrayList<Bill> list =new ArrayList<>();
    public static int mahd;
    public formBill() {
        initComponents();
        tblBill.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,19));
        tblBill.getTableHeader().setOpaque(false);
        tblBill.getTableHeader().setBackground(new java.awt.Color(32,136,203));
        tblBill.getTableHeader().setForeground(new java.awt.Color(255,255,255));
        lblHD.setText(mahd+"");
        filldata();
        this.setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblBill = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lblHD = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblBill.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblBill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Ngày in", "Tổng tiền", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBill.setFocusable(false);
        tblBill.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblBill.setRowHeight(30);
        tblBill.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblBill.setShowVerticalLines(false);
        tblBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBillMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBill);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        jLabel1.setText("Hóa đơn số:");

        lblHD.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        lblHD.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHD)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblHD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2){
            Bill newbill=list.get(tblBill.getSelectedRow());
            try {
                File newfile=new File("Bills",newbill.getFilename());
                Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+newfile.getAbsolutePath());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_tblBillMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(formBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formBill().setVisible(true);
            }
        });
    }
    private void filldata(){
        list=new BillDAO().getListBill(mahd);
        DefaultTableModel model=(DefaultTableModel)tblBill.getModel();
        model.setRowCount(0);
        for(Bill newbill : list){
            model.addRow(new Object[]{
                ngayin(newbill.getFilename()),
                Tienich.currencyVN.format(newbill.getTongtien()),
                newbill.getTrangthai()
            });
        }
    }
    public String ngayin(String filename){
            return filename.substring(filename.indexOf(" "),filename.indexOf("."));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHD;
    private javax.swing.JTable tblBill;
    // End of variables declaration//GEN-END:variables
}
