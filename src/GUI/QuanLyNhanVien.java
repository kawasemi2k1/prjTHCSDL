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
public class QuanLyNhanVien extends javax.swing.JPanel {

    DefaultTableModel tbn = new DefaultTableModel();
    ValidateData validate = new ValidateData();
    static int old = -1;
    static String old1 = null;
    static String old2 = null;
    

    public QuanLyNhanVien() {
        initComponents();
        loadData();
       // loadComobox();
        //loadComobox1();
       // loadComobox2();
        txtStaffID.setEnabled(false);
        BoxSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Phone", "Email" }));
        cbActive.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1"}));
        cbManagerstate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1"}));
        cbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ"}));
        
    }
    public void loadComobox(){
        try{
            Connect a = new Connect();
            Connection conn = a.getConnectDB();
            PreparedStatement ps = conn.prepareStatement("Select Gender from tblStaff group by Gender");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cbGender.addItem(rs.getString("Gender"));           
            } 
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }
    public void loadComobox1(){
        // set combox active 
        try{
            Connect a = new Connect();
            Connection conn = a.getConnectDB();
            PreparedStatement ps = conn.prepareStatement("Select Active from tblStaff group by Active");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cbActive.addItem(rs.getInt("Active")+"");  
            } 
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }
    
    public void loadComobox2(){
        try{
            Connect a = new Connect();
            Connection conn = a.getConnectDB();
            PreparedStatement ps = conn.prepareStatement("Select ManagerState from tblStaff group by ManagerState");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cbManagerstate.addItem(rs.getInt("Managerstate")+"");  
            } 
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }
    

    public void loadData() {
        try {
            Connect a = new Connect();
            Connection conn = a.getConnectDB();
            int number;
            Vector row,column;
            column = new Vector();
            Statement st =conn.createStatement();
            ResultSet rs=st.executeQuery("Select * from sales.staffs");
            ResultSetMetaData metadata =rs.getMetaData();
            number = metadata.getColumnCount();
            
            for(int i=1;i<=number;i++){
                column.add(metadata.getColumnName(i));
            }
            tbn.setColumnIdentifiers(column);
            while(rs.next()){
                row = new Vector();
                for(int i=1;i<=number;i++){
                    row.addElement(rs.getString(i));
                }
                tbn.addRow(row);
                jTable1.setModel(tbn);  
            } 
           
            jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
             //nap du lieu tu jtable len textfield
               public void valueChanged (ListSelectionEvent e){
                   if(jTable1.getSelectedRow()>= 0){
                       txtStaffID.setText(jTable1.getValueAt(jTable1.getSelectedRow(),0)+ "");
                       old = Integer.parseInt(txtStaffID.getText());
                       //txtStaffID.setEnabled(true);
                       txtName.setText(jTable1.getValueAt(jTable1.getSelectedRow(),1)+ "");
                       txtEmail.setText(jTable1.getValueAt(jTable1.getSelectedRow(),2)+ "");
                       old1 = txtEmail.getText();
                       txtPhone.setText(jTable1.getValueAt(jTable1.getSelectedRow(),3)+ "");
                       old2 = txtPhone.getText();
                       cbActive.setSelectedItem(jTable1.getModel().getValueAt(jTable1.getSelectedRow(),4)+ "");
                       txtStoreid.setText(jTable1.getValueAt(jTable1.getSelectedRow(),5)+ "");
                       cbManagerstate.setSelectedItem(jTable1.getModel().getValueAt(jTable1.getSelectedRow(),6)+ "");
                       cbGender.setSelectedItem(jTable1.getModel().getValueAt(jTable1.getSelectedRow(),7)+ "");
                       txtpass.setText(jTable1.getValueAt(jTable1.getSelectedRow(),8)+ "");
                   } 
                }
           });
           
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtStaffID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jlabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtpass = new javax.swing.JTextField();
        cbActive = new javax.swing.JComboBox<>();
        txtStoreid = new javax.swing.JTextField();
        cbManagerstate = new javax.swing.JComboBox<>();
        txtTimkiem = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtName = new javax.swing.JTextField();
        cbGender = new javax.swing.JComboBox<>();
        btnSearch = new javax.swing.JButton();
        BoxSearch = new javax.swing.JComboBox<>();
        Btn_Reset = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("StaffID");

        txtStaffID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStaffIDActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Name");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Gender");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Phone");

        txtPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Email");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Password");

        jlabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jlabel.setText("Active");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("StoreID");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("Managerstate");

        cbActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbActiveActionPerformed(evt);
            }
        });

        txtTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimkiemActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThoat.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

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

        cbGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGenderActionPerformed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        btnSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnSearchKeyReleased(evt);
            }
        });

        BoxSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        Btn_Reset.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Btn_Reset.setText("Reset");
        Btn_Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtStaffID)
                            .addComponent(txtPhone)
                            .addComponent(txtEmail)
                            .addComponent(txtName)
                            .addComponent(cbGender, 0, 144, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jlabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbActive, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtStoreid, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtpass)
                                    .addComponent(cbManagerstate, 0, 123, Short.MAX_VALUE))
                                .addGap(134, 134, 134)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(BoxSearch, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSearch))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(btnThem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnThoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Btn_Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)))))
                .addGap(134, 134, 134))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch))
                    .addComponent(txtStaffID, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbActive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStoreid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbManagerstate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoa)
                            .addComponent(btnThoat))
                        .addGap(27, 27, 27)
                        .addComponent(Btn_Reset)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtStaffIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStaffIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStaffIDActionPerformed

    private void txtPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneActionPerformed
  // Xoa
    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        try{
            Connect a = new Connect();
            Connection conn = a.getConnectDB();
            PreparedStatement comm =conn.prepareStatement(" Delete  from sales.staffs where staff_id=?");
            comm.setString(1,jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString() );
            if(JOptionPane.showConfirmDialog(this, "Delete this Staff ?","Confirm",JOptionPane.YES_NO_OPTION) 
                    == JOptionPane.YES_NO_OPTION){
                comm.executeUpdate();
                tbn.setRowCount(0);
                loadData();   
            } 
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void cbActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbActiveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbActiveActionPerformed

    private void cbGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbGenderActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try{
            Connect a = new Connect();
            Connection conn = a.getConnectDB();
            if(txtName.getText().equals("") ||txtEmail.getText().equals("") ||txtPhone.getText().equals("")
                    ||txtStoreid.getText().equals("") || txtpass.getText().equals("") ){
                JOptionPane.showMessageDialog(this, "Không được bỏ trống ");
            }else if( !validate.kiemTraTen(txtName.getText())){
                JOptionPane.showMessageDialog(this, "Tên không được chứa chữ số");      
            }else if (!validate.kiemTraEmail(txtEmail.getText())) {
            JOptionPane.showMessageDialog(this, "Không đúng định dạng email");      
            }else if (!validate.kiemTraSDT(txtPhone.getText()).equals("")) {
            JOptionPane.showMessageDialog(this, validate.kiemTraSDT(txtPhone.getText()));
            
            }else{
                StringBuffer sb = new StringBuffer();
               // String check_pk = " Select staff_id from sales.staffs where staff_id='"+txtStaffID.getText()+"'";
                Statement st = conn.createStatement();
                //ResultSet rs = st.executeQuery(check_pk);
                String check_phone = " Select phone from sales.staffs where phone='"+txtPhone.getText()+"'";
                Statement st1 = conn.createStatement();
                ResultSet rs1 = st1.executeQuery(check_phone);
                String check_mail = " Select email from sales.staffs where email='"+txtEmail.getText()+"'";
                Statement st2 = conn.createStatement();
                ResultSet rs2 = st2.executeQuery(check_mail);
//                if(rs.next()){
//                    JOptionPane.showMessageDialog(this,"StaffID đã tồn tại ");
//                
//                //if(sb.length()>0){
//                    //JOptionPane.showMessageDialog(this, sb.toString());   
//                }else 
                    if(rs1.next()){
                    JOptionPane.showMessageDialog(this, "Phone đã tồn tại");
                }else if(rs2.next()){
                    
                   JOptionPane.showMessageDialog(this,"Email đã tồn tại");
                }else{
                    
                    PreparedStatement ps = conn.prepareStatement(" insert into sales.staffs( name, email, phone, active, store_id, manager_state,gender,password) values(?,?,?,?,?,?,?,?)");
                    //ps.setString(1, txtStaffID.getText());
                    txtStaffID.setEnabled(false);
                    ps.setString(1, txtName.getText());
                    ps.setString(2, txtEmail.getText());
                    ps.setString(3, txtPhone.getText());
                    ps.setString(4, cbActive.getSelectedItem().toString());
                    ps.setString(5, txtStoreid.getText());
                    ps.setString(6, cbManagerstate.getSelectedItem().toString());
                    ps.setString(7, cbGender.getSelectedItem().toString());
                    ps.setString(8, txtpass.getText());
                    int check = ps.executeUpdate();
                    if (check > 0  ) {
                        JOptionPane.showMessageDialog(this, " Thêm thành công ");
                        tbn.setRowCount(0);
                        loadData();
                    }
                }   
            }
//            PreparedStatement ps =conn.prepareStatement("insert into tblStaff values(?,?,?,?,?,?,?,?,?)");
//            ps.setString(1,txtStaffID.getText());
//            ps.setString(2,txtName.getText());
//            ps.setString(3,txtEmail.getText());
//            ps.setString(4,txtPhone.getText());
//            ps.setString(5,cbActive.getSelectedItem().toString());
//            ps.setString(6,txtStoreid.getText());
//            ps.setString(7,cbManagerstate.getSelectedItem().toString());
//            ps.setString(8,cbGender.getSelectedItem().toString());
//            ps.setString(9,txtpass.getText());
//            
//            
//            int check = ps.executeUpdate();
//            if(check >0){
//                JOptionPane.showMessageDialog(this, " Thêm thành công ");
//                tbn.setRowCount(0);
//                loadData();
//            }  
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    
    }//GEN-LAST:event_btnThemActionPerformed
     // Sua 
    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try{
            Connect a = new Connect();
            Connection conn =a.getConnectDB();
        if(txtName.getText().equals("") ||txtEmail.getText().equals("") ||txtPhone.getText().equals("")
                    ||txtStoreid.getText().equals("") || txtpass.getText().equals("") ){
                JOptionPane.showMessageDialog(this, "Không được bỏ trống ");
            }else if( !validate.kiemTraTen(txtName.getText())){
                JOptionPane.showMessageDialog(this, "Tên không được chứa chữ số");      
            }else if (!validate.kiemTraEmail(txtEmail.getText())) {
            JOptionPane.showMessageDialog(this, "Không đúng định dạng email");      
            }else if (!validate.kiemTraSDT(txtPhone.getText()).equals("")) {
            JOptionPane.showMessageDialog(this, validate.kiemTraSDT(txtPhone.getText()));
            }else{
                int key =0;
                int key1 =0;
                int key2 =0;
//                if (key == 0) {
//                while (rs1.next()) {
//                    for (int i = 1; i <= number; i++) {
//                        if (rs1.getString(i) == txtEmail.getText()) {
//                            JOptionPane.showMessageDialog(this, "Email đã tồn tại ");
//                            ktra = 1;
//                            key = 1;
//                            break;
//                        }
//                    }
//                    if (ktra == 1) {
//                        break;
//                    }
//                }
//            }
            if(txtStaffID.getText().equals(old+"")){
                    //chưa đổi id, key !=1
                        key = 0;
                }else{
                JOptionPane.showMessageDialog(this, "Không được thay đổi StaffID");
                key = 1;
            }       
//                else {
//                           //đã đổi key
//                String check_pk = " Select staffID from tblStaff where staffID ='"+txtStaffID.getText()+"'";
//
//                Statement st = conn.createStatement(
//                ResultSet.TYPE_SCROLL_INSENSITIVE,
//                ResultSet.CONCUR_READ_ONLY
//                );
//                ResultSet rs = st.executeQuery(check_pk);
//                if(rs.next()){
//                    JOptionPane.showMessageDialog(this,"StaffID đã tồn tại ");
//                    key = 1;  
//                }
//                //int number = rs.last() ? rs.getRow() : 0;
//               // System.out.println(number); // number != 0 thì đã tồn tại. = 0 chưa tồn tại, đỏi key ở đây
//               }
             
            if(txtEmail.getText().equals(old1)){
                    //chưa đổi id, key !=1
                        key1 = 0;
            }       
            else {
                           //đã đổi key
                String check_mail = " Select email from sales.staffs where email ='"+txtEmail.getText()+"'";
                Statement st = conn.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
                );
                ResultSet rs1 = st.executeQuery(check_mail);
                if(rs1.next()){
                    JOptionPane.showMessageDialog(this,"Email đã tồn tại ");
                    key1 = 1;  
                }   
            } 
            if(txtPhone.getText().equals(old2)){
                    //chưa đổi id, key !=1
                        key2 = 0;
            }else {
                           //đã đổi key
                String check_phone = " Select phone from sales.staffs where phone ='"+txtPhone.getText()+"'";
                Statement st = conn.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
                );
                ResultSet rs2 = st.executeQuery(check_phone);
                if(rs2.next()){
                    JOptionPane.showMessageDialog(this,"Phone đã tồn tại ");
                    key2 = 1;  
                }
                
            }       
                    if(key ==0 && key1 ==0 && key2==0){
        
            PreparedStatement comm =conn.prepareStatement(" Update sales.staffs set name=?,email=?,phone=?,active=?,store_id=?,manager_state=?,gender=?,password=? where staff_id=?");
            //txtStaffID.setEnabled(true);
            comm.setString(9,txtStaffID.getText());
            txtStaffID.setEnabled(true);
            comm.setString(1, txtName.getText());
            comm.setString(2, txtEmail.getText());
            comm.setString(3, txtPhone.getText());
            comm.setString(4, cbActive.getSelectedItem().toString());
            comm.setString(5, txtStoreid.getText());
            comm.setString(6, cbManagerstate.getSelectedItem().toString());
            comm.setString(7, cbGender.getSelectedItem().toString());
            comm.setString(8, txtpass.getText());
            comm.executeUpdate();
            tbn.setRowCount(0);
            loadData();
            JOptionPane.showMessageDialog(this, "Sửa thành công ");
                }
        }  
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
        
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        JComponent comp = (JComponent) evt.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();
        ManagerMain main = new ManagerMain();
        main.setVisible(true);
    }//GEN-LAST:event_btnThoatActionPerformed

    private void txtTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimkiemActionPerformed

    }//GEN-LAST:event_txtTimkiemActionPerformed
   
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        if(txtTimkiem.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Hãy nhập dữ liệu tìm kiếm ");
            tbn.setRowCount(0);
            loadData();
        }else{
            try{
                //DefaultTableModel defaultTableModel = new DefaultTableModel();
            Connect a = new Connect();
            Connection conn = a.getConnectDB();
            StringBuffer sb = new StringBuffer();
            PreparedStatement ps = null;
            //String search_pk = "Select * from tblStaff where (staffID='"+txtTimkiem.getText()+"') or (Phone='"+txtTimkiem.getText()+"') or (Name like N'"+txtTimkiem.getText()+"') ";
            String sql = "Select * from sales.staffs where ";
            String str1 = null;
            Statement st = conn.createStatement();
            ResultSet rs = null;
            if(BoxSearch.getSelectedItem().toString().equals("ID")){
                ps = conn.prepareStatement(sql + "staff_id = ? ");
                str1 = txtTimkiem.getText();
                ps.setString(1, str1);
                rs = ps.executeQuery();   
            }else if(BoxSearch.getSelectedItem().toString().equals("Phone")){
                ps = conn.prepareStatement(sql + "phone = ? ");
                str1 = txtTimkiem.getText();
                ps.setString(1, str1);
                rs = ps.executeQuery();  
            }else if(BoxSearch.getSelectedItem().toString().equals("Email")){
                ps = conn.prepareStatement(sql + "email like ? ");
                str1 = "%" + txtTimkiem.getText() + "%";
                ps.setString(1, str1);
                rs = ps.executeQuery();  
            }
            
            while(rs.next()){
                sb.append("Tìm kiếm thành công ");
                tbn.setRowCount(0);
                if(sb.length()>0){
                    JOptionPane.showMessageDialog(this, sb.toString()); 
                    String []colsName = {"StaffID", "Name","Email","Phone","Active","StoreID","ManagerState","Gender","Password"};
                    tbn.setColumnIdentifiers(colsName);
                    
                    String value[] = new String[9];
                    value[0]=rs.getInt("staff_id")+"";
                    value[1]=rs.getString("name");
                    value[2]=rs.getString("email");
                    value[3]=rs.getString("phone");
                    value[4]=rs.getInt("active")+"";
                    value[5]=rs.getInt("store_id")+"";
                    value[6]=rs.getInt("manager_state")+"";
                    value[7]=rs.getString("gender");
                    value[8]=rs.getString("password");
                    tbn.addRow(value);
                    jTable1.setModel(tbn);       
                }      
            }  
            if(!rs.next()){
                JOptionPane.showMessageDialog(this,"Không tìm thấy.Tìm kiếm thất bại ");
            }
            }catch(Exception ex){
                System.out.println(  ex.toString());
            }   
        }
     
        
    }//GEN-LAST:event_btnSearchActionPerformed

    private void Btn_ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ResetActionPerformed
        tbn.setRowCount(0);
        txtStaffID.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtStoreid.setText("");
        txtpass.setText("");
        loadData();
// TODO add your handling code here:
    }//GEN-LAST:event_Btn_ResetActionPerformed

    private void btnSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSearchKeyReleased
        // TODO add your handling code here:
         
     
    }//GEN-LAST:event_btnSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> BoxSearch;
    private javax.swing.JButton Btn_Reset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbActive;
    private javax.swing.JComboBox<String> cbGender;
    private javax.swing.JComboBox<String> cbManagerstate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jlabel;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtStaffID;
    private javax.swing.JTextField txtStoreid;
    private javax.swing.JTextField txtTimkiem;
    private javax.swing.JTextField txtpass;
    // End of variables declaration//GEN-END:variables
}
