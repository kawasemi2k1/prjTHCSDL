/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Window;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class QuanLySanPhamChuoiCuaHang extends javax.swing.JPanel {

    DefaultTableModel tbn = new DefaultTableModel();

    public QuanLySanPhamChuoiCuaHang() {
        initComponents();
        loadData();
        txt_id.setEnabled(false);
        btn_xoa.setEnabled(false);
     
    }

    public void loadData() {
        try {
            Connect a = new Connect();
            Connection conn = a.getConnectDB();
            int number;
            Vector row, column;
            column = new Vector();
            Statement st = conn.createStatement();
            String sql = "select product_id, product_name, price, brand_name, category_name, country from production.products\n"
                    + "inner join production.brands on production.products.brand_id = production.brands.brand_id\n"
                    + "inner join production.categories on production.categories.category_id = production.products.category_id";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();

            for (int i = 1; i <= number; i++) {
                column.add(metadata.getColumnName(i));
            }
            tbn.setColumnIdentifiers(column);

            while (rs.next()) {
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.addElement(rs.getString(i));
                }
                tbn.addRow(row);
                tbl_sp.setModel(tbn);
            }

            tbl_sp.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent lse) {
                    if (tbl_sp.getSelectedRow() >= 0) {
                        txt_id.setText(tbl_sp.getValueAt(tbl_sp.getSelectedRow(), 0) + "");
                        txt_name.setText(tbl_sp.getValueAt(tbl_sp.getSelectedRow(), 1) + "");
                        txt_brand.setText(tbl_sp.getValueAt(tbl_sp.getSelectedRow(), 3) + "");
                        txt_cat.setText(tbl_sp.getValueAt(tbl_sp.getSelectedRow(), 4) + "");
                        txt_country.setText(tbl_sp.getValueAt(tbl_sp.getSelectedRow(), 5) + "");
                        txt_price.setText(tbl_sp.getValueAt(tbl_sp.getSelectedRow(), 2) + "");
                        btn_them.setEnabled(false);
                        btn_xoa.setEnabled(true);
                        
                    }
                }
            }
            );
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public String Lay_brand_id(String brand_name) {
        String brand_id = "";
        try {
            Connect a = new Connect();
            Connection conn = a.getConnectDB();
            int number;
            Vector row, column;
            column = new Vector();
            Statement st = conn.createStatement();
            System.out.println(brand_name);
            String sql = "select brand_id from production.brands\n" + "where brand_name like (?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, brand_name);
            ResultSet rs = ps.executeQuery();
            //ResultSet rs = st.executeQuery(sql);
            //System.out.println("Here ");
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();

            for (int i = 1; i <= number; i++) {
                column.add(metadata.getColumnName(i));
            }
            //tbn.setColumnIdentifiers(column);

            while (rs.next()) {
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.addElement(rs.getString(i));
                    brand_id = rs.getString(i);
                }
                //tbn.addRow(row);
                //tbl_sp.setModel(tbn);
            }
            System.out.println(brand_id);

        } catch (Exception ex) {
            System.out.println("Loi o brand " + ex.toString());
        }
        System.out.println(brand_id);
        return brand_id;
    }

    public String Lay_cat_id(String cat_name) {
        String cat_id = "";
        try {
            Connect a = new Connect();
            Connection conn = a.getConnectDB();
            int number;
            Vector row, column;
            column = new Vector();
            Statement st = conn.createStatement();
            String sql = "select category_id from production.categories\n" + "where category_name like (?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, cat_name);
            ResultSet rs = ps.executeQuery();

            // ResultSet rs = st.executeQuery(sql);
            //System.out.println("Hare too ");
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();

            for (int i = 1; i <= number; i++) {
                column.add(metadata.getColumnName(i));
            }
            //tbn.setColumnIdentifiers(column);

            while (rs.next()) {
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.addElement(rs.getString(i));
                    cat_id = rs.getString(i);
                }

                //tbn.addRow(row);
                //tbl_sp.setModel(tbn);
            }
            System.out.println(cat_id);

        } catch (Exception ex) {
            System.out.println("Loi o cat" + ex.toString());
        }
        System.out.println(cat_id);
        return cat_id;
    }

    public String Lay_product_id(String product_name) {
        String product_id = "";
        try {
            Connect a = new Connect();
            Connection conn = a.getConnectDB();
            int number;
            Vector row, column;
            column = new Vector();
            Statement st = conn.createStatement();
            String sql = "select product_id from production.products\n" + "where product_name like (?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, product_name);
            ResultSet rs = ps.executeQuery();

            // ResultSet rs = st.executeQuery(sql);
            //System.out.println("Hare too ");
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();

            for (int i = 1; i <= number; i++) {
                column.add(metadata.getColumnName(i));
            }
            //tbn.setColumnIdentifiers(column);

            while (rs.next()) {
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.addElement(rs.getString(i));
                    product_id = rs.getString(i);
                }

                //tbn.addRow(row);
                //tbl_sp.setModel(tbn);
            }
            System.out.println(product_id);

        } catch (Exception ex) {
            System.out.println("Loi o product" + ex.toString());
        }
        System.out.println(product_id);
        return product_id;
    }

    public String Lay_brand_id_Tu_Product_Id(String product_id) {
        String brand_id = "";
        try {
            Connect a = new Connect();
            Connection conn = a.getConnectDB();
            int number;
            Vector row, column;
            column = new Vector();
            Statement st = conn.createStatement();
            //System.out.println(brand_name);
            String sql = "select brand_id from production.products\n" + "where product_id like (?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, product_id);
            ResultSet rs = ps.executeQuery();;
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();

            for (int i = 1; i <= number; i++) {
                column.add(metadata.getColumnName(i));
            }
            //tbn.setColumnIdentifiers(column);

            while (rs.next()) {
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.addElement(rs.getString(i));
                    brand_id = rs.getString(i);
                }
                //tbn.addRow(row);
                //tbl_sp.setModel(tbn);
            }
            //System.out.println(brand_id);

        } catch (Exception ex) {
            System.out.println("Loi o brand " + ex.toString());
        }
        System.out.println(brand_id);
        return brand_id;
    }

    public String Lay_cat_id_Tu_Product_Id(String product_id) {
        String cat_id = "";
        try {
            Connect a = new Connect();
            Connection conn = a.getConnectDB();
            int number;
            Vector row, column;
            column = new Vector();
            Statement st = conn.createStatement();
            //System.out.println(brand_name);
            String sql = "select brand_id from production.categories\n" + "where product_id like (?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, product_id);
            ResultSet rs = ps.executeQuery();;
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();

            for (int i = 1; i <= number; i++) {
                column.add(metadata.getColumnName(i));
            }
            //tbn.setColumnIdentifiers(column);

            while (rs.next()) {
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.addElement(rs.getString(i));
                    cat_id = rs.getString(i);
                }
                //tbn.addRow(row);
                //tbl_sp.setModel(tbn);
            }
            //System.out.println(brand_id);

        } catch (Exception ex) {
            System.out.println("Loi o brand " + ex.toString());
        }
        System.out.println(cat_id);
        return cat_id;
    }

    public boolean kiemTra_brand_con_khong(String brand_id) {
        String product_id = "";
        try {
            Connect a = new Connect();
            Connection conn = a.getConnectDB();
            int number;
            Vector row, column;
            column = new Vector();
            Statement st = conn.createStatement();

            String sql = "select product_id from production.products\n" + "where brand_id like (?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, brand_id);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();

            for (int i = 1; i <= number; i++) {
                column.add(metadata.getColumnName(i));
            }
            //tbn.setColumnIdentifiers(column);

            while (rs.next()) {
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.addElement(rs.getString(i));
                    product_id = rs.getString(i);
                    if (product_id.length() > 0) {
                        return true;
                    }
                }
                //tbn.addRow(row);
                //tbl_sp.setModel(tbn);
            }
            System.out.println(product_id);

        } catch (Exception ex) {
            System.out.println("Loi o brand khi sua " + ex.toString());
        }
        return false;
    }

    public boolean kiemTra_cat_con_khong(String cat_id) {
        String product_id = "";
        try {
            Connect a = new Connect();
            Connection conn = a.getConnectDB();
            int number;
            Vector row, column;
            column = new Vector();
            Statement st = conn.createStatement();
            System.out.println(cat_id);
            String sql = "select product_id from production.products\n" + "where category_id like (?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, cat_id);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();

            for (int i = 1; i <= number; i++) {
                column.add(metadata.getColumnName(i));
            }
            //tbn.setColumnIdentifiers(column);

            while (rs.next()) {
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.addElement(rs.getString(i));
                    product_id = rs.getString(i);
                    if (product_id.length() > 0) {
                        return true;
                    }
                }
                //tbn.addRow(row);
                //tbl_sp.setModel(tbn);
            }
            System.out.println(product_id);

        } catch (Exception ex) {
            System.out.println("Loi o cat khi sua " + ex.toString());
        }
        return false;
    }

    public void reset() {
        btn_them.setEnabled(true);
        btn_xoa.setEnabled(false);
        txt_brand.setText("");
        txt_cat.setText("");
        txt_country.setText("");
        txt_id.setText("");
        txt_name.setText("");
        txt_price.setText("");
        tbn.setRowCount(0);
        loadData();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_price = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_brand = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        btn_them = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txt_cat = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_country = new javax.swing.JTextField();
        btn_thoat = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        jComboBoxSearch = new javax.swing.JComboBox<>();
        txt_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_sp = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(5, 5));
        setPreferredSize(new java.awt.Dimension(1072, 368));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 352, Short.MAX_VALUE)
        );

        jPanel2.setMinimumSize(new java.awt.Dimension(5, 5));
        jPanel2.setPreferredSize(new java.awt.Dimension(1920, 1030));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1906, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 603, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Quản lý các loại sản phẩm");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("Price");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setText("Brand");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("ID");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Name");

        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_sua.setText("Sửa");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        btn_xoa.setText("Xóa");
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("Category");

        txt_cat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_catActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setText("Country");

        btn_thoat.setText("Thoát");
        btn_thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thoatActionPerformed(evt);
            }
        });

        btn_reset.setText("Reset");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        jComboBoxSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Name", "Brand", "Category", "Country" }));

        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });

        btn_search.setText("Tìm kiếm");

        tbl_sp.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_sp);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(592, 592, 592)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap(150, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_id, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                    .addComponent(txt_name)
                                    .addComponent(txt_cat))
                                .addGap(100, 100, 100)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_country, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel5))
                                        .addGap(33, 33, 33)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_price, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                                            .addComponent(txt_brand))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(205, 205, 205)
                                        .addComponent(jComboBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(264, 264, 264)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btn_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(btn_them, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                                    .addComponent(btn_sua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(80, 80, 80)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(btn_xoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(btn_thoat, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1906, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(7, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addGap(352, 352, 352))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txt_cat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(txt_brand, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addComponent(txt_country, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_thoat, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(9, 9, 9)
                        .addComponent(btn_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_catActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_catActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_catActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed

        if (txt_name.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Name không được trống");
            return;
        } else if (txt_cat.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Category không được trống");
            return;
        } else if (txt_brand.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Brand không được trống");
            return;
        } else if (txt_price.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Price không được trống");
            return;
        } else if (txt_country.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Country không được trống");
            return;
        }
        try {
            Connect a = new Connect();
            Connection conn = a.getConnectDB();

            String brand_id = null;
            String cat_id = null;
            String product_id = null;

            brand_id = Lay_brand_id(txt_brand.getText());
            cat_id = Lay_cat_id(txt_cat.getText());
            product_id = Lay_product_id(txt_name.getText());

            String brand_id_copy = brand_id;
            String cat_id_copy = cat_id;

            System.out.println("Day la brand : " + brand_id + " va chieu dai: " + brand_id.length());
            System.out.println("Day la cat : " + cat_id + " va chieu dai: " + cat_id.length());
            System.out.println("Day la product : " + product_id + " va chieu dai: " + product_id.length());

            int chk_brand = 1;
            int chk_cat = 1;
            int chk_product = 1;

            if (brand_id.length() == 0) {
                String sql_brand = "insert into production.brands(brand_name,country) Values(?,?)";
                PreparedStatement ps_brand = conn.prepareStatement(sql_brand);
                ps_brand.setString(1, txt_brand.getText());
                ps_brand.setString(2, txt_country.getText());
                chk_brand = ps_brand.executeUpdate();
                brand_id = Lay_brand_id(txt_brand.getText());
            }

            if (cat_id.length() == 0) {
                String sql_cat = "insert into production.categories(category_name) Values(?)";
                PreparedStatement ps_cat = conn.prepareStatement(sql_cat);
                ps_cat.setString(1, txt_cat.getText());
                chk_cat = ps_cat.executeUpdate();
                cat_id = Lay_cat_id(txt_cat.getText());

            }

            if (product_id.length() == 0) {
                String sql_product = "insert into production.products(product_name,brand_id, category_id,price) Values(?,?,?,?)";
                PreparedStatement ps_product = conn.prepareStatement(sql_product);
                ps_product.setString(1, txt_name.getText());
                ps_product.setString(2, brand_id);
                ps_product.setString(3, cat_id);
                ps_product.setString(4, txt_price.getText());
                chk_product = ps_product.executeUpdate();
            } else {
                System.out.println("Da co mat hang nay");
            }

            //String sql_product = "insert into production.products(product_name,brand_id, category_id,price) Values(?,?,?,?)";
            //String sql_cat = "insert into production.categories(category_name) Values(?)";
            //String sql_brand = "insert into production.brands(brand_name,country) Values(?,?)";
            // PreparedStatement ps_product = conn.prepareStatement(sql_product);
            //PreparedStatement ps_cat = conn.prepareStatement(sql_cat);
            //PreparedStatement ps_brand = conn.prepareStatement(sql_brand);
            //ps_cat.setString(1, txt_cat.getText());
            // ps_brand.setString(1, txt_brand.getText());
            // ps_brand.setString(2, txt_country.getText());
            //int chk_cat = ps_cat.executeUpdate();
            //int chk_brand = ps_brand.executeUpdate();
            //System.out.println("424");
            //brand_id = Lay_brand_id(txt_brand.getText());
            //cat_id = Lay_cat_id(txt_cat.getText());
            //ps_product.setString(1, txt_name.getText());
            // ps_product.setString(2, brand_id);
            // ps_product.setString(3, cat_id);
            // ps_product.setString(4, txt_price.getText());
            // int chk_product = ps_product.executeUpdate();
            //System.out.println("429");
            if (chk_cat > 0 && chk_brand > 0 && chk_product > 0) {
                JOptionPane.showMessageDialog(this, "Them Thanh cong");
                tbn.setRowCount(0);
                loadData();
            }
        } catch (Exception ex) {
            System.out.println("O btn them " + ex.toString());
        }
        reset();
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:
        if (txt_name.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Name không được trống");
            return;
        } else if (txt_cat.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Category không được trống");
            return;
        } else if (txt_brand.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Brand không được trống");
            return;
        } else if (txt_price.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Price không được trống");
            return;
        } else if (txt_country.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Country không được trống");
            return;
        }

        try {
            Connect a = new Connect();
            Connection conn = a.getConnectDB();

            String brand_id = null;
            String cat_id = null;
            String product_id = null;
            //String brand_name_copy;
            //String cat_name_copy;

            brand_id = Lay_brand_id(txt_brand.getText());
            cat_id = Lay_cat_id(txt_cat.getText());
            product_id = txt_id.getText();

            String brand_id_copy = Lay_brand_id_Tu_Product_Id(product_id);
            String cat_id_copy = Lay_cat_id_Tu_Product_Id(product_id);

            System.out.println("Day la brand : " + brand_id + " va chieu dai: " + brand_id.length());
            System.out.println("Day la cat : " + cat_id + " va chieu dai: " + cat_id.length());
            System.out.println("Day la product : " + product_id + " va chieu dai: " + product_id.length());

            int chk_brand = 1;
            int chk_cat = 1;
            int chk_product = 1;

            if (brand_id.length() == 0) {
                String sql_brand = "insert into production.brands(brand_name,country) Values(?,?)";
                PreparedStatement ps_brand = conn.prepareStatement(sql_brand);
                ps_brand.setString(1, txt_brand.getText());
                ps_brand.setString(2, txt_country.getText());
                chk_brand = ps_brand.executeUpdate();
                brand_id = Lay_brand_id(txt_brand.getText());
            }

            if (cat_id.length() == 0) {
                String sql_cat = "insert into production.categories(category_name) Values(?)";
                PreparedStatement ps_cat = conn.prepareStatement(sql_cat);
                ps_cat.setString(1, txt_cat.getText());
                chk_cat = ps_cat.executeUpdate();
                cat_id = Lay_cat_id(txt_cat.getText());
            }

            PreparedStatement comm = conn.prepareStatement("Update production.products set product_name = ?, brand_id = ?, category_id = ?, price = ? where product_id = ?");
            comm.setString(1, txt_name.getText());
            comm.setString(3, cat_id);
            comm.setString(2, brand_id);
            comm.setString(4, txt_price.getText());
            comm.setString(5, txt_id.getText());
            comm.executeUpdate();

            //brand_id = Lay_brand_id(brand_name_copy);
            // System.out.println("Name: " + brand_name_copy);
            System.out.println("ID: " + brand_id_copy);
            if (!kiemTra_brand_con_khong(brand_id_copy)) {
                //xoa brand ra khoi bang brand
                System.out.println("Da thuc thi");
                PreparedStatement con_brand = conn.prepareStatement("Delete production.brands where brand_id = ?");
                con_brand.setString(1, brand_id_copy);
                con_brand.executeUpdate();
                tbn.setRowCount(0);
                loadData();
            }

            //cat_id = Lay_cat_id(cat_name_copy);
            if (!kiemTra_cat_con_khong(cat_id_copy)) {
                //xoa cat ra khoi bang brand
                PreparedStatement con_cat = conn.prepareStatement("Delete production.categories where category_id = ?");
                con_cat.setString(1, cat_id_copy);
                con_cat.executeUpdate();
                tbn.setRowCount(0);
                loadData();
            }

            JOptionPane.showMessageDialog(this, "Sua thanh cong");
            tbn.setRowCount(0);
            loadData();

        } catch (Exception ex) {
            System.out.println("O btn sua " + ex.toString());
        }
        reset();
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        // TODO add your handling code here:

        String product_id = txt_id.getText();
        String brand_id_copy = Lay_brand_id_Tu_Product_Id(product_id);
        String cat_id_copy = Lay_cat_id_Tu_Product_Id(product_id);
        try {
            Connect a = new Connect();
            Connection conn = a.getConnectDB();
            PreparedStatement comm = conn.prepareStatement("Delete production.products where product_id = ?");
            comm.setString(1, txt_id.getText());
            if (JOptionPane.showConfirmDialog(this, "Are you want to delete ?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                comm.executeUpdate();
                tbn.setRowCount(0);
                loadData();
                if (!kiemTra_brand_con_khong(brand_id_copy)) {
                    //xoa brand ra khoi bang brand
                    System.out.println("Da thuc thi");
                    PreparedStatement con_brand = conn.prepareStatement("Delete production.brands where brand_id = ?");
                    con_brand.setString(1, brand_id_copy);
                    con_brand.executeUpdate();
                    tbn.setRowCount(0);
                    loadData();
                }

                //cat_id = Lay_cat_id(cat_name_copy);
                if (!kiemTra_cat_con_khong(cat_id_copy)) {
                    //xoa cat ra khoi bang brand
                    PreparedStatement con_cat = conn.prepareStatement("Delete production.categories where category_id = ?");
                    con_cat.setString(1, cat_id_copy);
                    con_cat.executeUpdate();
                    tbn.setRowCount(0);
                    loadData();
                }
            }
        } catch (Exception ex) {
            System.out.println("Loi o xoa: " + ex.toString());
        }
        reset();
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_thoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thoatActionPerformed
        // TODO add your handling code here:
        JComponent comp = (JComponent) evt.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();
        ManagerMain main = new ManagerMain();
        main.setVisible(true);
    }//GEN-LAST:event_btn_thoatActionPerformed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        // TODO add your handling code here:
        
        try {
            Connect a = new Connect();
            Connection con = a.getConnectDB();
            String sql = "select product_id, product_name, price, brand_name, category_name, country from production.products\n"
                    + "inner join production.brands on production.products.brand_id = production.brands.brand_id\n"
                    + "inner join production.categories on production.categories.category_id = production.products.category_id where ";
            PreparedStatement ps = null;
            int number;
            Vector row, column;
            column = new Vector();
            ResultSet rs = null;
            String str1 = null;
            
            if(jComboBoxSearch.getSelectedItem().toString().equals("ID")) {
                ps = con.prepareStatement(sql + "product_id like ?");
                str1 = "%" + txt_search.getText() + "%";
                ps.setString(1, str1);
                rs = ps.executeQuery();
            } else if (jComboBoxSearch.getSelectedItem().toString().equals("Name")) {
                ps = con.prepareStatement(sql + "product_name like ?");
                str1 = "%" + txt_search.getText() + "%";
                ps.setString(1, str1);
                rs = ps.executeQuery();
            } else if (jComboBoxSearch.getSelectedItem().toString().equals("Brand")) {
                ps = con.prepareStatement(sql + "brand_name like ?");
                str1 = "%" + txt_search.getText() + "%";
                ps.setString(1, str1);
                rs = ps.executeQuery();
            } else if (jComboBoxSearch.getSelectedItem().toString().equals("Category")) {
                ps = con.prepareStatement(sql + "category_name like ?");
                ps.setString(1, txt_search.getText());
                rs = ps.executeQuery();
            } else if (jComboBoxSearch.getSelectedItem().toString().equals("Country")) {
                ps = con.prepareStatement(sql + "country like ?");
                str1 = "%" + txt_search.getText() + "%";
                ps.setString(1, str1);
                rs = ps.executeQuery();
            } 
            
            tbn.setRowCount(0);
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();
            
            for(int i = 1; i <= number; i++){
                column.add(metadata.getColumnName(i));
            }
            tbn.setColumnIdentifiers(column);
            
            while(rs.next()){
                row = new Vector();
                for(int i = 1; i <= number; i++){
                    row.addElement(rs.getString(i));
                }
                tbn.addRow(row);
                tbl_sp.setModel(tbn);
            }
            
        } catch (Exception ex) {
            System.out.println("Loi o tim kiem " + ex.toString());
        }
    }//GEN-LAST:event_txt_searchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_thoat;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JComboBox<String> jComboBoxSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_sp;
    private javax.swing.JTextField txt_brand;
    private javax.swing.JTextField txt_cat;
    private javax.swing.JTextField txt_country;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_price;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
