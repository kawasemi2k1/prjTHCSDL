/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Utils.ValidateData;
import java.awt.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
//import static jdk.nashorn.internal.runtime.regexp.RegExpFactory.validate;
//import static sun.security.util.KeyUtil.validate;
//import static sun.security.util.KeyUtil.validate;

/**
 *
 * @author ADMIN
 */
public class ThongKe_Staff_byTimeNew extends javax.swing.JPanel {

    DefaultTableModel tbn = new DefaultTableModel();
    ValidateData validate = new ValidateData();
    static int old = -1;
    static String old1 = null;
    static String old2 = null;
    int store = Integer.parseInt(Login.Store_ID);
    

//    public ThongKe_Staff_byTimeNew() {
//        initComponents();
//        loadData();
//       // loadComobox();
//        //loadComobox1();
//       // loadComobox2();
//        txtStaffID.setEnabled(false);
//        //txtStoreid.setText(store+"");
//        txtStoreid.setText(store+"");
//        txtStoreid.setEnabled(false);
//        BoxSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Phone", "Email" }));
//        cbActive.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Nghỉ"}));
//        cbManagerstate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản lý", "Nhân viên"}));
//        cbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ"}));
//        
//        
//    }
//    public void loadComobox(){
//        try{
//            Connect a = new Connect();
//            Connection conn = a.getConnectDB();
//            PreparedStatement ps = conn.prepareStatement("Select Gender from tblStaff group by Gender");
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()){
//                cbGender.addItem(rs.getString("Gender"));           
//            } 
//        }catch(Exception ex){
//            System.out.println(ex.toString());
//        }
//    }
//    public void loadComobox1(){
//        // set combox active 
//        try{
//            Connect a = new Connect();
//            Connection conn = a.getConnectDB();
//            PreparedStatement ps = conn.prepareStatement("Select Active from tblStaff group by Active");
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()){
//                cbActive.addItem(rs.getInt("Active")+"");  
//            } 
//        }catch(Exception ex){
//            System.out.println(ex.toString());
//        }
//    }
//    
//    public void loadComobox2(){
//        try{
//            Connect a = new Connect();
//            Connection conn = a.getConnectDB();
//            PreparedStatement ps = conn.prepareStatement("Select ManagerState from tblStaff group by ManagerState");
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()){
//                cbManagerstate.addItem(rs.getInt("Managerstate")+"");  
//            } 
//        }catch(Exception ex){
//            System.out.println(ex.toString());
//        }
//    }
//    
//
//    public void loadData() {
//        try {
//            Connect a = new Connect();
//            Connection conn = a.getConnectDB();
//            int number;
//            Vector row,column;
//            column = new Vector();
//            Statement st =conn.createStatement();
//            String query = " Select * from sales.staffs where store_id='"+store+"'";
//            ResultSet rs=st.executeQuery(query);
//            ResultSetMetaData metadata =rs.getMetaData();
//            number = metadata.getColumnCount();
//            
//            for(int i=1;i<=number;i++){
//                column.add(metadata.getColumnName(i));
//            }
//            tbn.setColumnIdentifiers(column);
//            while(rs.next()){
//                row = new Vector();
//                for(int i=1;i<=number;i++){
//                    if(i==5)//vị trí của column active
//                    {
//                        row.addElement(rs.getString(i).equals("1") ? "Hoạt động" : "Nghỉ");
//                    }else if(i == 7){
//                        row.addElement(rs.getString(i).equals("1") ? "Quản lý" : "Nhân viên");
//                    }else {
//                    row.addElement(rs.getString(i));
//                    }
//                    
//                }
//                tbn.addRow(row);
//                jTable1.setModel(tbn);  
//            } 
//           
//            jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
//             //nap du lieu tu jtable len textfield
//               public void valueChanged (ListSelectionEvent e){
//                   if(jTable1.getSelectedRow()>= 0){
//                        txtStaffID.setText(jTable1.getValueAt(jTable1.getSelectedRow(),0)+ "");
//                       old = Integer.parseInt(txtStaffID.getText());
//                       //txtStaffID.setEnabled(true);
//                       txtName.setText(jTable1.getValueAt(jTable1.getSelectedRow(),1)+ "");
//                       txtEmail.setText(jTable1.getValueAt(jTable1.getSelectedRow(),2)+ "");
//                       old1 = txtEmail.getText();
//                       txtPhone.setText(jTable1.getValueAt(jTable1.getSelectedRow(),3)+ "");
//                       old2 = txtPhone.getText();
//                       cbActive.setSelectedItem(jTable1.getModel().getValueAt(jTable1.getSelectedRow(),4).equals("Hoạt động") ? "Hoạt động" : "Nghỉ" + "");
//                       txtStoreid.setText(jTable1.getValueAt(jTable1.getSelectedRow(),5)+ "");
//                       cbManagerstate.setSelectedItem(jTable1.getModel().getValueAt(jTable1.getSelectedRow(),6).equals("Quản lý") ? "Quản lý" : "Nhân viên" +"");
//                       cbGender.setSelectedItem(jTable1.getModel().getValueAt(jTable1.getSelectedRow(),7)+ "");
//                       txtpass.setText(jTable1.getValueAt(jTable1.getSelectedRow(),8)+ "");
//                   } 
//                }
//           });
//           
//        } catch (Exception ex) {
//            System.out.println(ex.toString());
//        }
//    }
//    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtTimkiem = new javax.swing.JTextField();
        btnXoa = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnSearch = new javax.swing.JButton();
        Btn_Reset = new javax.swing.JButton();

        jPanel1.setLayout(null);

        txtTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimkiemActionPerformed(evt);
            }
        });
        txtTimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimkiemKeyReleased(evt);
            }
        });
        jPanel1.add(txtTimkiem);
        txtTimkiem.setBounds(160, 20, 250, 40);

        btnXoa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnXoa.setContentAreaFilled(false);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa);
        btnXoa.setBounds(1190, 380, 200, 110);

        btnThoat.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThoat.setContentAreaFilled(false);
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        jPanel1.add(btnThoat);
        btnThoat.setBounds(0, 690, 290, 110);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(60, 100, 720, 270);

        btnSearch.setBackground(new java.awt.Color(51, 51, 255));
        btnSearch.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Tìm kiếm");
        btnSearch.setBorderPainted(false);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch);
        btnSearch.setBounds(650, 30, 130, 40);

        Btn_Reset.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Btn_Reset.setContentAreaFilled(false);
        Btn_Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ResetActionPerformed(evt);
            }
        });
        jPanel1.add(Btn_Reset);
        Btn_Reset.setBounds(950, 390, 200, 90);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1544, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
  // Xoa
    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
       
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        JComponent comp = (JComponent) evt.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();
    }//GEN-LAST:event_btnThoatActionPerformed
   
    private void Btn_ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ResetActionPerformed
    
// TODO add your handling code here:
    }//GEN-LAST:event_Btn_ResetActionPerformed

    private void txtTimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimkiemKeyReleased
          
        
    }//GEN-LAST:event_txtTimkiemKeyReleased

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
         //DefaultTableModel defaultTableModel = new DefaultTableModel();
            
        
// TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimkiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Reset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnXoa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtTimkiem;
    // End of variables declaration//GEN-END:variables
}
