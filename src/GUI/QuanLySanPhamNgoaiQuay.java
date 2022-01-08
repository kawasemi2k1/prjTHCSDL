package GUI;
import Utils.ValidateData;
import java.awt.Color;
import java.awt.Window;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class QuanLySanPhamNgoaiQuay extends javax.swing.JPanel {

    final String header[] = {"Tên sản phẩm", "Ngày sản xuất", "Hạn sử dụng", "Giá", "Giảm giá (%)", "Số lượng"};
    final DefaultTableModel tb = new DefaultTableModel(header,0);
    Date date = new Date();
    Connect cn = new Connect();
    Connection conn = null;
    ResultSet rs;
    String Store_ID = Login.Store_ID;
    public QuanLySanPhamNgoaiQuay() {
        initComponents();
        loadBang();
        dc_ngaySX.setDate(new Date());
        dc_hanSD.setDate(new Date());
        
    }
  
    public void loadBang(){
        try {
            label_TenCuaHang.setText(GetStoreName(Store_ID)); 
            conn = cn.getConnectDB();
            int number;
            Vector row;
            String sql = "select product_name, created_at, good_till, S.price, discount, quantity  from sales.stocks S\n" +
                        " inner join production.products P on S.product_id = P.product_id\n" +
                        " where store_id = " + Store_ID;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();
            number = metaData.getColumnCount();
            tb.setRowCount(0);
             while (rs.next()) {
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.addElement(rs.getString(i));
                }
                tb.addRow(row);
                tbl_sanPhamBan.setModel(tb);
            }
             
            st.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
        }
        tbl_sanPhamBan.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if(tbl_sanPhamBan.getSelectedRow() >= 0){
                    txt_TenSP.setText(tbl_sanPhamBan.getValueAt(tbl_sanPhamBan.getSelectedRow(), 0)+ "");
                    
                    String sDate1 = tbl_sanPhamBan.getValueAt(tbl_sanPhamBan.getSelectedRow(), 1).toString();  
                    Date date1 = null; 
                    try {
                        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
                    } catch (ParseException ex) {
                        Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dc_ngaySX.setDate(date1);
                   
                    String sDate2 = tbl_sanPhamBan.getValueAt(tbl_sanPhamBan.getSelectedRow(), 2).toString();  
                        Date date2 = null; 
                        try {
                            date2 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate2);
                        } catch (ParseException ex) {
                            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        dc_hanSD.setDate(date2);
                        
                    txt_gia.setText(tbl_sanPhamBan.getValueAt(tbl_sanPhamBan.getSelectedRow(), 3)+ "");
                    txt_giamGia.setText(tbl_sanPhamBan.getValueAt(tbl_sanPhamBan.getSelectedRow(), 4)+ "");
                    txt_soLuong.setText(tbl_sanPhamBan.getValueAt(tbl_sanPhamBan.getSelectedRow(), 5)+ "");   
                }
            }
        });
    }
    
    private void Reset(){
        txt_gia.setText("");
        txt_giamGia.setText("");
        dc_hanSD.setDate(new Date());
        dc_ngaySX.setDate(new Date());
        txt_TenSP.setText("");
        txt_soLuong.setText("");
        loadBang();
        txt_TenSP.setEnabled(true);
        btn_them.setEnabled(true);
        dc_hanSD.setEnabled(true);
        dc_ngaySX.setEnabled(true);
        txt_TenSP.setBackground(Color.white);
        txt_search.setText("");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_TenSP = new javax.swing.JTextField();
        txt_gia = new javax.swing.JTextField();
        txt_giamGia = new javax.swing.JTextField();
        txt_soLuong = new javax.swing.JTextField();
        btn_them = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        btn_search = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        jComboBoxSearch = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_sanPhamBan = new javax.swing.JTable();
        dc_ngaySX = new com.toedter.calendar.JDateChooser();
        dc_hanSD = new com.toedter.calendar.JDateChooser();
        label_TenCuaHang = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1920, 1020));
        setLayout(null);

        txt_TenSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_TenSPKeyTyped(evt);
            }
        });
        add(txt_TenSP);
        txt_TenSP.setBounds(510, 170, 186, 22);
        add(txt_gia);
        txt_gia.setBounds(510, 300, 233, 22);
        add(txt_giamGia);
        txt_giamGia.setBounds(510, 340, 233, 22);
        add(txt_soLuong);
        txt_soLuong.setBounds(510, 380, 233, 22);

        btn_them.setContentAreaFilled(false);
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });
        add(btn_them);
        btn_them.setBounds(930, 160, 200, 100);

        btn_xoa.setContentAreaFilled(false);
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });
        add(btn_xoa);
        btn_xoa.setBounds(930, 290, 200, 110);

        btn_sua.setContentAreaFilled(false);
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });
        add(btn_sua);
        btn_sua.setBounds(1180, 160, 220, 100);

        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(jButton4);
        jButton4.setBounds(0, 690, 290, 110);

        btn_reset.setContentAreaFilled(false);
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });
        add(btn_reset);
        btn_reset.setBounds(1190, 290, 200, 110);

        btn_search.setBackground(new java.awt.Color(51, 51, 255));
        btn_search.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_search.setForeground(new java.awt.Color(255, 255, 255));
        btn_search.setText("Tìm kiếm");
        btn_search.setBorderPainted(false);
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });
        add(btn_search);
        btn_search.setBounds(800, 440, 100, 40);

        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });
        add(txt_search);
        txt_search.setBounds(500, 440, 290, 40);

        jComboBoxSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên sản phẩm", "Giảm Giá", "Ngày sản xuất", "Hạn sử dụng" }));
        add(jComboBoxSearch);
        jComboBoxSearch.setBounds(360, 440, 110, 40);

        tbl_sanPhamBan.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_sanPhamBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_sanPhamBanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_sanPhamBan);

        add(jScrollPane1);
        jScrollPane1.setBounds(350, 510, 1140, 260);
        add(dc_ngaySX);
        dc_ngaySX.setBounds(510, 210, 190, 22);
        add(dc_hanSD);
        dc_hanSD.setBounds(510, 260, 186, 22);

        label_TenCuaHang.setBackground(new java.awt.Color(204, 255, 51));
        add(label_TenCuaHang);
        label_TenCuaHang.setBounds(90, 10, 167, 33);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Frame Quản lý sản phảm bày bán.png"))); // NOI18N
        jLabel8.setText("jLabel8");
        add(jLabel8);
        jLabel8.setBounds(0, 0, 1540, 810);
    }// </editor-fold>//GEN-END:initComponents
    public String GetProduct_id(String product_name) {
        String product_id = "";
        try {
            Connect a = new Connect();
            Connection conn = a.getConnectDB();
            int number;
            Vector row, column;
            column = new Vector();
            Statement st = conn.createStatement();
            String sql = "select product_id from production.products\n" + "where product_name = (?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, product_name);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();

            for (int i = 1; i <= number; i++) {
                column.add(metadata.getColumnName(i));
            }
            while (rs.next()) {
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.addElement(rs.getString(i));
                    product_id = rs.getString(i);
                }
            }
            System.out.println(product_id);

        } catch (Exception ex) {
            System.out.println("Loi o product" + ex.toString());
        }
        System.out.println(product_id);
        return product_id;
    }
    public String GetStoreName(String store_id) {
        String store_name = "";
        try {
            Connect a = new Connect();
            Connection conn = a.getConnectDB();
            int number;
            Vector row, column;
            column = new Vector();
            Statement st = conn.createStatement();
            String sql = "select name from sales.stores\n" + "where store_id = (?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, store_id);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();

            for (int i = 1; i <= number; i++) {
                column.add(metadata.getColumnName(i));
            }
            while (rs.next()) {
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.addElement(rs.getString(i));
                    store_name = rs.getString(i);
                }
            }
            System.out.println(store_name);

        } catch (Exception ex) {
            System.out.println("Loi o store" + ex.toString());
        }
        System.out.println(store_name);
        return store_name;
    }
    
    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        conn = cn.getConnectDB();
        try {
              if(txt_TenSP.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Thiếu tên sản phẩm");
                return;
            }  else if (txt_gia.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Thiếu giá");
                return;
            } else if (txt_soLuong.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Thiếu số lượng");
                return;
            } else if (dc_hanSD.getDate().compareTo(dc_ngaySX.getDate())< 0) {
                JOptionPane.showMessageDialog(this, "Ngày sản xuất không thể sau hạn sử dụng");
                return;
            }
            else {
                    Connect a = new Connect();
                    Connection con = a.getConnectDB();
                    System.out.println(dc_hanSD.getDate().compareTo(dc_ngaySX.getDate()));
                    if(txt_giamGia.getText().equals("")) txt_giamGia.setText("0");
                    
                    PreparedStatement ps = con.prepareStatement("insert into sales.stocks values (?, ?, ?, ?, ?, ?, ?)");
                    ps.setString(1, GetProduct_id(txt_TenSP.getText()));
                    ps.setObject(2, dc_ngaySX.getDate());
                    ps.setObject(3, dc_hanSD.getDate());
                    ps.setString(4, Store_ID);
                    ps.setString(5, txt_gia.getText());
                    ps.setString(6, txt_giamGia.getText());
                    ps.setString(7, txt_soLuong.getText());

                    int check = ps.executeUpdate();
                    System.out.println("check: "+check);
                    if(check > 0) {
                        JOptionPane.showMessageDialog(this, "Thêm thành công.");
                        tb.setRowCount(0);
                        loadBang();
                        Reset();
                    }
                    con.close();
                    ps.close();
                }
                conn.close();
                rs.close();
                    
        } catch (Exception e) {
            if (e.toString().contains("PK")) {
                JOptionPane.showMessageDialog(this, "Đã tồn tại đồng thời sản phẩm này cùng ngày sản xuất và hạn sử dụng ở quầy. Hãy kiểm tra lại!");
                return;
            }
            if (e.toString().contains("product_id")) {
                 JOptionPane.showMessageDialog(this, "Không tồn tại sản phẩm này");
                 txt_TenSP.setBackground(Color.red);
                return;
            }
        }
    }//GEN-LAST:event_btn_themActionPerformed

    private void tbl_sanPhamBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_sanPhamBanMouseClicked
        // TODO add your handling code here
        int x = tbl_sanPhamBan.getSelectedRow();
        if(x >= 0) {
            txt_TenSP.setText(tbl_sanPhamBan.getValueAt(x, 0)+ "");
            String sDate1 = tbl_sanPhamBan.getValueAt(tbl_sanPhamBan.getSelectedRow(), 1).toString();  
                        Date date1 = null; 
                        try {
                            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
                        } catch (ParseException ex) {
                            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        dc_ngaySX.setDate(date1);
                        
                        
            String sDate2 = tbl_sanPhamBan.getValueAt(tbl_sanPhamBan.getSelectedRow(), 2).toString();  
                        Date date2 = null; 
                        try {
                            date2 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate2);
                        } catch (ParseException ex) {
                            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        dc_hanSD.setDate(date2);            
            txt_gia.setText(tbl_sanPhamBan.getValueAt(x, 3)+ "");
            txt_giamGia.setText(tbl_sanPhamBan.getValueAt(x, 4)+ "");
            txt_soLuong.setText(tbl_sanPhamBan.getValueAt(x, 5)+ "");
            txt_TenSP.setEnabled(false);
            dc_hanSD.setEnabled(false);
            dc_ngaySX.setEnabled(false);
            btn_them.setEnabled(false);
        }
    }//GEN-LAST:event_tbl_sanPhamBanMouseClicked

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:
        conn = cn.getConnectDB();
        try {
            if (txt_gia.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Thiếu giá");
                return;
            } else if (txt_soLuong.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Thiếu số lượng");
                return;
            }
            else {
                if(txt_giamGia.getText().equals("")) txt_giamGia.setText("0");
                Connect a = new Connect();
                Connection con = a.getConnectDB();
                PreparedStatement ps = con.prepareStatement(
                        "Update sales.stocks set price =  ? , discount = ?, quantity =  ? "
                        + "  where product_id =  ?  and created_at = ? and good_till = ? and store_id =  ?  ");
                ps.setString(1, txt_gia.getText());
                ps.setString(2, txt_giamGia.getText());
                ps.setString(3, txt_soLuong.getText());
                ps.setString(4, GetProduct_id(txt_TenSP.getText()));
                ps.setObject(5, dc_ngaySX.getDate());
                ps.setObject(6, dc_hanSD.getDate());
                ps.setString(7, Store_ID);
              
                int check = ps.executeUpdate();
                if(check > 0) {
                    JOptionPane.showMessageDialog(this, "Sửa thành công.");
                    Reset();
                    tb.setRowCount(0);
                    loadBang();
                }     
                ps.close();
            }
            conn.close();
            rs.close();
           
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
        Reset();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        // TODO add your handling code here:
        conn = cn.getConnectDB();
        try {
            int chk = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa không?", "Thông báo!", JOptionPane.YES_NO_OPTION );
            if(chk == JOptionPane.YES_OPTION){
                Connect a = new Connect();
                Connection con = a.getConnectDB();
                PreparedStatement ps = con.prepareStatement(
                "Delete from sales.stocks where product_id =  ? and created_at = ? and good_till = ? and store_id = ? " );
                ps.setString(1, GetProduct_id(txt_TenSP.getText()));
                ps.setObject(2, dc_ngaySX.getDate());
                ps.setObject(3, dc_hanSD.getDate());
                ps.setString(4, Store_ID);
                
                int check = ps.executeUpdate();
                if(check > 0) {
                    JOptionPane.showMessageDialog(this, "Đã xóa");
                    tb.setRowCount(0);
                    loadBang();
                    Reset();
                }     
                ps.close();
            
            conn.close();
            rs.close(); 
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        JComponent comp = (JComponent) evt.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();
        ManagerMain main = new ManagerMain();
        main.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txt_TenSPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TenSPKeyTyped
        // TODO add your handling code here:
        txt_TenSP.setBackground(Color.white);
    }//GEN-LAST:event_txt_TenSPKeyTyped

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        // TODO add your handling code here:
        try {
            Connect a = new Connect();
            Connection con = a.getConnectDB();
            String sql = "select product_name, created_at, good_till, S.price, discount, quantity  from sales.stocks S\n" +
                        " inner join production.products P on S.product_id = P.product_id \n" +
                        "where store_id =  " + Store_ID + " and ";
            PreparedStatement ps = null;
            int number;
            Vector row, column;
            column = new Vector();
            ResultSet rs = null;
            String str1 = null;
            
            if(jComboBoxSearch.getSelectedItem().toString().equals("Tên sản phẩm")) {
                ps = con.prepareStatement(sql + "product_name like ?");
                str1 = "%" + txt_search.getText() + "%";
                ps.setString(1, str1);
                rs = ps.executeQuery();
            } else if (jComboBoxSearch.getSelectedItem().toString().equals("Giảm Giá")) {
                ps = con.prepareStatement(sql + "discount like ?");
                str1 = "%" + txt_search.getText() + "%";
                ps.setString(1, str1);
                rs = ps.executeQuery();
            } else if (jComboBoxSearch.getSelectedItem().toString().equals("Ngày sản xuất")) {
                ps = con.prepareStatement(sql + "created_at like ?");
                str1 = "%" + txt_search.getText() + "%";
                ps.setString(1, str1);
                rs = ps.executeQuery();
            } else if (jComboBoxSearch.getSelectedItem().toString().equals("Hạn sử dụng")) {
                ps = con.prepareStatement(sql + "good_till like ?");
                str1 = "%" + txt_search.getText() + "%";
                ps.setString(1, str1);
                rs = ps.executeQuery();
            } 
            
            tb.setRowCount(0);
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();
            
            for(int i = 1; i <= number; i++){
                column.add(metadata.getColumnName(i));
            }
            tb.setColumnIdentifiers(column);
            
            while(rs.next()){
                row = new Vector();
                for(int i = 1; i <= number; i++){
                    row.addElement(rs.getString(i));
                }
                tb.addRow(row);
                tbl_sanPhamBan.setModel(tb);
            }
            
        } catch (Exception ex) {
            System.out.println("Loi o tim kiem " + ex.toString());
        }
    }//GEN-LAST:event_txt_searchKeyReleased

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        // TODO add your handling code here:
        try {
            Connect a = new Connect();
            Connection con = a.getConnectDB();
            String sql = "select product_name, created_at, good_till, S.price, discount, quantity  from sales.stocks S\n" +
                        "inner join production.products P on S.product_id = P.product_id\n" +
                        "where store_id =  " + Store_ID + " and ";
            PreparedStatement ps = null;
            int number;
            Vector row, column;
            column = new Vector();
            ResultSet rs = null;
            String str1 = null;
            
            if(jComboBoxSearch.getSelectedItem().toString().equals("Tên sản phẩm")) {
                ps = con.prepareStatement(sql + "product_name = ?");
                ps.setString(1, txt_search.getText());
                rs = ps.executeQuery();
            } else if (jComboBoxSearch.getSelectedItem().toString().equals("Giảm Giá")) {
                ps = con.prepareStatement(sql + "discount = ?");
                ps.setString(1, txt_search.getText());
                rs = ps.executeQuery();
            } else if (jComboBoxSearch.getSelectedItem().toString().equals("Ngày sản xuất")) {
                ps = con.prepareStatement(sql + "created_at = ?");
                ps.setString(1, txt_search.getText());
                rs = ps.executeQuery();
            } else if (jComboBoxSearch.getSelectedItem().toString().equals("Hạn sử dụng")) {
                ps = con.prepareStatement(sql + "good_till = ?");
                ps.setString(1, txt_search.getText());
                rs = ps.executeQuery();
            } 
            
            tb.setRowCount(0);
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();
            
            for(int i = 1; i <= number; i++){
                column.add(metadata.getColumnName(i));
            }
            tb.setColumnIdentifiers(column);
            
            while(rs.next()){
                row = new Vector();
                for(int i = 1; i <= number; i++){
                    row.addElement(rs.getString(i));
                }
                tb.addRow(row);
                tbl_sanPhamBan.setModel(tb);
            }
            
        } catch (Exception ex) {
            System.out.println("Loi o tim kiem " + ex.toString());
        }
    }//GEN-LAST:event_btn_searchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_xoa;
    private com.toedter.calendar.JDateChooser dc_hanSD;
    private com.toedter.calendar.JDateChooser dc_ngaySX;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBoxSearch;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_TenCuaHang;
    private javax.swing.JTable tbl_sanPhamBan;
    private javax.swing.JTextField txt_TenSP;
    private javax.swing.JTextField txt_gia;
    private javax.swing.JTextField txt_giamGia;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_soLuong;
    // End of variables declaration//GEN-END:variables
}
