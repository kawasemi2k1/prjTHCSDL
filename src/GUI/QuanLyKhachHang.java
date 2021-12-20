/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.List;
import java.awt.Window;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ADMIN
 */
public class QuanLyKhachHang extends javax.swing.JPanel {
    DefaultTableModel tbn = new DefaultTableModel();
    Date date = new Date();
    
    /**
     * Creates new form QuanLyKhachHang
     */
    public QuanLyKhachHang() {
        initComponents();
        loadData();
        jDateChooser1.setDate(new Date());
    }

    public void loadData(){
        try{
            Connect a = new Connect();
            Connection con = a.getConnectDB();
            Vector row, column;
            column = new Vector();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from sales.customers where customer_id ! = 1;");
            
            for(int i = 0; i < jTable1.getColumnCount(); i++){
                column.add(jTable1.getColumnName(i));
            }
            tbn.setColumnIdentifiers(column);
            
            while(rs.next()){
                row = new Vector();
                for(int i = 1; i <= jTable1.getColumnCount(); i++){
                    row.addElement(rs.getString(i));
                }
                tbn.addRow(row);
                jTable1.setModel(tbn);
            }
            
            jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if(jTable1.getSelectedRow() >= 0) {
                        txtID.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 0) + "");
                        txtName.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1) + "");
                        String sDate1 = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();  
                        Date date1 = null; 
                        try {
                            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
                        } catch (ParseException ex) {
                            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        jDateChooser1.setDate(date1);
                        txtAddress.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 3) + "");
                        txtPhone.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 4) + "");
                        txtEmail.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 5) + "");
                    }
                }
            });
            
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }
    
    private boolean isDeleteAble(int ID) {
        ArrayList<Integer> Slots = new ArrayList<Integer>();
        try{
            Connect a = new Connect();
            Connection con = a.getConnectDB();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select sales.customers.customer_id from sales.customers "
                    + "left join sales.orders on sales.orders.customer_id = sales.customers.customer_id "
                    + "where order_id is null;");
            if(rs.next() == false) return false;
            else {
                do { 
                    Slots.add(rs.getInt(1));
                }while(rs.next());
                for(int i = 0; i < Slots.size(); i++){
                    if(ID == Slots.get(i)){
                        return true;
                    }
                }
            }
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
        return false;
    }
    
    private boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    
    private boolean isValidPhone(String phone)
    {
        String phoneRegex = "^[0-9]{10}$";
                              
        Pattern pat = Pattern.compile(phoneRegex);
        if (phone == null)
            return false;
        return pat.matcher(phone).matches();
    }
    
    private int SlotToInsert() {
        int missingSlot = 1;
        ArrayList<Integer> Slots = new ArrayList<Integer>();
        try{
            Connect a = new Connect();
            Connection con = a.getConnectDB();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select customer_id from sales.customers order by customer_id;");
            if(rs.next() == false) return 0;
            else {
                do { 
                    Slots.add(rs.getInt(1));
                }while(rs.next());
                for(int i = 0; i < Slots.size(); i++, missingSlot++){
                    if(missingSlot != Slots.get(i)){
                        return missingSlot - 1;
                    }
                }
            }
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
        return missingSlot - 1;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton4 = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jComboBoxSearch = new javax.swing.JComboBox<>();
        txtID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản lý khách hàng");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên", "Ngày Sinh", "Địa Chỉ", "SĐT", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Tên");

        jLabel3.setText("Ngày sinh");

        jLabel4.setText("Địa chỉ");

        jLabel5.setText("SĐT");

        jLabel6.setText("Email");

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cập Nhật");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Tìm kiếm");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jComboBoxSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Tên", "Ngày Sinh", "Địa Chỉ", "SĐT", "Email", " " }));

        txtID.setEditable(false);
        txtID.setBackground(new java.awt.Color(204, 204, 204));

        jLabel7.setText("ID");

        jButton5.setText("Làm mới");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Thoát");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAddress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addComponent(txtSearch))
                .addGap(215, 215, 215))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(117, 117, 117)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(jComboBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3)
                        .addComponent(jButton2)
                        .addComponent(jButton1)
                        .addComponent(jLabel3)
                        .addComponent(jLabel6)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if(txtName.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Thiếu tên.");
                return;
            } else if (txtAddress.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Thiếu địa chỉ.");
                return;
            } else if (txtPhone.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Thiếu SĐT.");
                return;
            } else if (txtEmail.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Thiếu Email.");
                return;
            } else if (date.getYear() - jDateChooser1.getDate().getYear() < 10 ) {
                JOptionPane.showMessageDialog(this, "Không đủ tuổi.");
                return;
            } else if (!isValidEmail(txtEmail.getText())) {
                JOptionPane.showMessageDialog(this, "Sai cú pháp Email.");
                return;
            } else if (!isValidPhone(txtPhone.getText())) {
                JOptionPane.showMessageDialog(this, "Sai cú pháp SĐT.");
                return;
            }
            
            Connect a = new Connect();
            Connection con = a.getConnectDB();
            
            int seed;
            if(SlotToInsert() >= 0){
                seed = SlotToInsert();
                System.out.println("Seed: " + seed);
                PreparedStatement ps1 = con.prepareStatement("DBCC CHECKIDENT ('sales.customers', RESEED, ?);");
                ps1.setInt(1, seed);
                ps1.execute();
            }
            
            PreparedStatement ps = con.prepareStatement("insert into sales.customers values (?, ?, ?, ?, ?)");
            ps.setString(1, txtName.getText());
            ps.setObject(2, jDateChooser1.getDate());
            ps.setString(3, txtAddress.getText());
            ps.setString(4, txtPhone.getText());
            ps.setString(5, txtEmail.getText());
            
            int check = ps.executeUpdate();
            if(check > 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công.");
                tbn.setRowCount(0);
                loadData();
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            if(ex.toString().contains("UQ1")) {
                JOptionPane.showMessageDialog(this, "Email này đã tồn tại trong hệ thống.");
            } else if (ex.toString().contains("UQ0")) {
                JOptionPane.showMessageDialog(this, "SĐT này đã tồn tại trong hệ thống.");
            } else if (ex.toString().contains("PK")) {
                JOptionPane.showMessageDialog(this, "Người này đã tồn tại trong hệ thống.");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            if(txtName.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Thiếu tên.");
                return;
            } else if (txtAddress.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Thiếu địa chỉ.");
                return;
            } else if (txtPhone.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Thiếu SĐT.");
                return;
            } else if (txtEmail.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Thiếu Email.");
                return;
            } else if (date.getYear() - jDateChooser1.getDate().getYear() < 10 ) {
                JOptionPane.showMessageDialog(this, "Không đủ tuổi.");
                return;
            } else if (!isValidEmail(txtEmail.getText())) {
                JOptionPane.showMessageDialog(this, "Sai cú pháp Email.");
                return;
            } else if (!isValidPhone(txtPhone.getText())) {
                JOptionPane.showMessageDialog(this, "Sai cú pháp SĐT.");
                return;
            }
            
            Connect a = new Connect();
            Connection con = a.getConnectDB();
            PreparedStatement ps = con.prepareStatement(
                    "update sales.customers set name = ?, "
                            + "birthday = ?, "
                            + "address = ?, "
                            + "phone = ?, "
                            + "email = ? "
                            + "where customer_id = ?");
            ps.setString(1, txtName.getText());
            ps.setObject(2, jDateChooser1.getDate());
            ps.setString(3, txtAddress.getText());
            ps.setString(4, txtPhone.getText());
            ps.setString(5, txtEmail.getText());
            ps.setString(6, jTable1.getValueAt(jTable1.getSelectedRow(), 0) + "");
            
            int check = ps.executeUpdate();
            if(check > 0) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công.");
                tbn.setRowCount(0);
                loadData();
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            if(ex.toString().contains("UQ1")) {
                JOptionPane.showMessageDialog(this, "Email này đã tồn tại trong hệ thống.");
            } else if (ex.toString().contains("UQ0")) {
                JOptionPane.showMessageDialog(this, "SĐT này đã tồn tại trong hệ thống.");
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(txtName.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Thiếu tên.");
            return;
        } else if (txtAddress.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Thiếu địa chỉ.");
            return;
        } else if (txtPhone.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Thiếu SĐT.");
            return;
        } else if (txtEmail.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Thiếu Email.");
            return;
        }
        
        try {
            Connect a = new Connect();
            Connection con = a.getConnectDB();
            
            if(isDeleteAble(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0) + ""))) {
                PreparedStatement ps = con.prepareStatement("delete from sales.customers where customer_id = ?");
                ps.setString(1, jTable1.getValueAt(jTable1.getSelectedRow(), 0) + "");

                int check = ps.executeUpdate();
                if(check > 0) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công.");
                    tbn.setRowCount(0);
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại.");
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không thể xóa.");
                return;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            Connect a = new Connect();
            Connection con = a.getConnectDB();
            String str = "Select * from sales.customers where ";
            PreparedStatement ps = null;
            Vector row, column;
            column = new Vector();
            ResultSet rs = null;
            String str1 = null;
            if(txtSearch.getText().equals("")) {
                tbn.setRowCount(0);
                loadData();
                return;
            } else if(jComboBoxSearch.getSelectedItem().toString().equals("ID")) {
                ps = con.prepareStatement(str + "customer_id like ?");
                ps.setString(1, txtSearch.getText());
                rs = ps.executeQuery();
            } else if (jComboBoxSearch.getSelectedItem().toString().equals("Tên")) {
                ps = con.prepareStatement(str + "name like ?");
                ps.setString(1, txtSearch.getText());
                rs = ps.executeQuery();
            } else if (jComboBoxSearch.getSelectedItem().toString().equals("Địa Chỉ")) {
                ps = con.prepareStatement(str + "address like ?");
                ps.setString(1, txtSearch.getText());
                rs = ps.executeQuery();
            } else if (jComboBoxSearch.getSelectedItem().toString().equals("SĐT")) {
                ps = con.prepareStatement(str + "phone like ?");
                ps.setString(1, txtSearch.getText());
            } else if (jComboBoxSearch.getSelectedItem().toString().equals("Email")) {
                ps = con.prepareStatement(str + "email like ?");
                ps.setString(1, txtSearch.getText());
                rs = ps.executeQuery();
            } else if (jComboBoxSearch.getSelectedItem().toString().equals("Ngày Sinh")) {
                ps = con.prepareStatement(str + "birthday = ?");
                ps.setString(1, txtSearch.getText());
                rs = ps.executeQuery();
            } 
            
            tbn.setRowCount(0);
            
            for(int i = 0; i < jTable1.getColumnCount(); i++){
                column.add(jTable1.getColumnName(i));
            }
            tbn.setColumnIdentifiers(column);
            
            while(rs.next()){
                row = new Vector();
                for(int i = 1; i <= jTable1.getColumnCount(); i++){
                    row.addElement(rs.getString(i));
                }
                tbn.addRow(row);
                jTable1.setModel(tbn);
            }
            
            if(tbn.getRowCount() == 0) JOptionPane.showMessageDialog(this, "Không tìm thấy.");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        tbn.setRowCount(0);
        txtID.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        loadData();
        jDateChooser1.setDate(new Date());
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        try {
            Connect a = new Connect();
            Connection con = a.getConnectDB();
            String str = "Select * from sales.customers where ";
            PreparedStatement ps = null;
            Vector row, column;
            column = new Vector();
            ResultSet rs = null;
            String str1 = null;
            if(jComboBoxSearch.getSelectedItem().toString().equals("ID")) {
                ps = con.prepareStatement(str + "customer_id like ?");
                str1 = "%" + txtSearch.getText() + "%";
                ps.setString(1, str1);
                rs = ps.executeQuery();
            } else if (jComboBoxSearch.getSelectedItem().toString().equals("Tên")) {
                ps = con.prepareStatement(str + "name like ?");
                str1 = "%" + txtSearch.getText() + "%";
                ps.setString(1, str1);
                rs = ps.executeQuery();
            } else if (jComboBoxSearch.getSelectedItem().toString().equals("Địa Chỉ")) {
                ps = con.prepareStatement(str + "address like ?");
                str1 = "%" + txtSearch.getText() + "%";
                ps.setString(1, str1);
                rs = ps.executeQuery();
            } else if (jComboBoxSearch.getSelectedItem().toString().equals("SĐT")) {
                ps = con.prepareStatement(str + "phone like ?");
                str1 = "%" + txtSearch.getText() + "%";
                ps.setString(1, str1);
                rs = ps.executeQuery();
            } else if (jComboBoxSearch.getSelectedItem().toString().equals("Email")) {
                ps = con.prepareStatement(str + "email like ?");
                str1 = "%" + txtSearch.getText() + "%";
                ps.setString(1, str1);
                rs = ps.executeQuery();
            } else if (jComboBoxSearch.getSelectedItem().toString().equals("Ngày Sinh")) {
                ps = con.prepareStatement(str + "birthday = ?");
                ps.setString(1, txtSearch.getText());
                rs = ps.executeQuery();
            } 
            
            tbn.setRowCount(0);
            
            for(int i = 0; i < jTable1.getColumnCount(); i++){
                column.add(jTable1.getColumnName(i));
            }
            tbn.setColumnIdentifiers(column);
            
            while(rs.next()){
                row = new Vector();
                for(int i = 1; i <= jTable1.getColumnCount(); i++){
                    row.addElement(rs.getString(i));
                }
                tbn.addRow(row);
                jTable1.setModel(tbn);
            }
            
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        JComponent comp = (JComponent) evt.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();
        ManagerMain main = new ManagerMain();
        main.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBoxSearch;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
