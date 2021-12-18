/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Component;
import java.awt.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
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
public class BanHang extends javax.swing.JPanel {
    DefaultTableModel tbnCustomer = new DefaultTableModel();
    DefaultTableModel tbnProduct = new DefaultTableModel();
    DefaultTableModel tbnBill = new DefaultTableModel();
    static int store = 1; //default to 1, until Login function
    static int staff = 1; //default to 1, until Login function
    /**
     * Creates new form BanHang
     */
    public BanHang() {
        initComponents();
        loadDataCustomer();
        loadDataProduct();
        txtDiscount.setText("0.00");
        jButton11.setEnabled(false);
        jButton5.setEnabled(false);
        jButton8.setEnabled(false);
        BillTableListener();
    }
    
    private void loadDataCustomer(){
        try{
            Connect a = new Connect();
            Connection con = a.getConnectDB();
            int number, number1;
            Vector row, column;
            column = new Vector();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from sales.customers where name != 'Vô danh'");
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();
            
            for(int i = 1; i <= number; i++){
                column.add(metadata.getColumnName(i));
            }
            tbnCustomer.setColumnIdentifiers(column);
            
            while(rs.next()){
                row = new Vector();
                for(int i = 1; i <= number; i++){
                    row.addElement(rs.getString(i));
                }
                tbnCustomer.addRow(row);
                jTableCustomer.setModel(tbnCustomer);
            }
            
            jTableCustomer.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent lse) {
                    if (jTableCustomer.getSelectedRow() >= 0) {
                        jButton11.setEnabled(true);

                    }
                }
            }
            );
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }
    
    private void loadDataProduct(){
        try{
            Connect a = new Connect();
            Connection con = a.getConnectDB();
            int number, number1;
            Vector row, row1, column, column1;
            column = new Vector();
            PreparedStatement ps = con.prepareStatement("select sales.stocks.product_id, "
                    + "production.products.product_name, "
                    + "production.categories.category_name, "
                    + "sales.stocks.created_at, "
                    + "sales.stocks.good_till,"
                    + "production.brands.brand_name, "
                    + "production.brands.country, "
                    + "sales.stocks.quantity, "
                    + "sales.stocks.price, "
                    + "sales.stocks.discount from sales.stocks\n" +
                "inner join production.products on sales.stocks.product_id = production.products.product_id\n" +
                "inner join production.categories on production.categories.category_id = production.products.category_id\n" +
                "inner join production.brands on production.brands.brand_id = production.products.brand_id "
                    + "where store_id = ?");
            ps.setString(1, String.valueOf(store));
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();
            
            for(int i = 1; i <= number; i++){
                column.add(metadata.getColumnName(i));
            }
            tbnProduct.setColumnIdentifiers(column);
            
            while(rs.next()){
                row = new Vector();
                for(int i = 1; i <= number; i++){
                    row.addElement(rs.getString(i));
                }
                tbnProduct.addRow(row);
                jTableProduct.setModel(tbnProduct);
            }
            
            jTableProduct.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent lse) {
                    if (jTableProduct.getSelectedRow() >= 0) {
                        jButton5.setEnabled(true);
                    }
                }
            }
            );
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }
    
    private void BillTableListener() {
        jTableBill.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent lse) {
                    if (jTableBill.getSelectedRow() >= 0) {
                        jButton8.setEnabled(true);
                    }
                }
            }
        );
    }
    
    public static boolean isDouble(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
           double i = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    public static boolean isInt(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCustomer = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jComboBoxCustomerSearch = new javax.swing.JComboBox<>();
        txtSearchCustomer = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableBill = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtBillID = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        txtCustomerName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProduct = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jComboBoxProductSearch = new javax.swing.JComboBox<>();
        txtSearchProduct = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 51, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bán Hàng");

        jLabel2.setText("Khách Hàng");

        jTableCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên", "Ngày Sinh", "Địa Chỉ", "SĐT", "Email"
            }
        ));
        jScrollPane1.setViewportView(jTableCustomer);

        jButton3.setText("Tìm kiếm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBoxCustomerSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên", "SĐT", "Email" }));

        txtSearchCustomer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchCustomerKeyReleased(evt);
            }
        });

        jButton11.setText("Xác nhận");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtSearchCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxCustomerSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton11))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCustomerSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel4.setText("Hóa đơn");

        jTableBill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Đơn giá", "Số lượng", "Giảm giá", "Thành tiền"
            }
        ));
        jScrollPane3.setViewportView(jTableBill);

        jButton7.setText("Chốt đơn");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Xóa sản phẩm");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Hủy đơn");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel7.setText("ID");

        jButton10.setText("Tìm đơn");

        txtCustomerName.setEditable(false);
        txtCustomerName.setBackground(new java.awt.Color(204, 204, 204));

        jLabel8.setText("Tên khách hàng");

        jButton12.setText("Xóa");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton6.setText("Xóa tát cả sản phẩm");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtBillID))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jButton8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton6))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton10)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jButton7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton9)))))
                        .addGap(0, 163, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtBillID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jButton12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton7)
                    .addComponent(jButton9)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel3.setText("Sản phẩm");

        jTableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên", "NSX", "HSD", "Loại", "Hãng", "Xuất xứ", "Đơn giá", "Giảm giá"
            }
        ));
        jScrollPane2.setViewportView(jTableProduct);

        jButton4.setText("Tìm kiếm");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jComboBoxProductSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Tên", "Loại", "Hãng", "Xuất xứ" }));

        txtSearchProduct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchProductKeyReleased(evt);
            }
        });

        jLabel5.setText("Số lượng");

        jButton5.setText("Thêm vào giỏ hàng");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel6.setText("Giảm giá (%)");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDiscount))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtQuantity))
                            .addComponent(txtSearchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jComboBoxProductSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton5)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxProductSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setText("Thoát");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Làm mới");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            Connect a = new Connect();
            Connection con = a.getConnectDB();
            String str = "Select * from sales.customers where ";
            PreparedStatement ps = null;
            int number;
            Vector row, column;
            column = new Vector();
            ResultSet rs = null;
            String str1 = null;
            if(txtSearchCustomer.getText().equals("")) {
                tbnCustomer.setRowCount(0);
                loadDataCustomer();
                return;
            } else if (jComboBoxCustomerSearch.getSelectedItem().toString().equals("Tên")) {
                ps = con.prepareStatement(str + "name like ?");
                ps.setString(1, txtSearchCustomer.getText());
                rs = ps.executeQuery();
            } else if (jComboBoxCustomerSearch.getSelectedItem().toString().equals("SĐT")) {
                ps = con.prepareStatement(str + "phone like ?");
                ps.setString(1, txtSearchCustomer.getText());
            } else if (jComboBoxCustomerSearch.getSelectedItem().toString().equals("Email")) {
                ps = con.prepareStatement(str + "email like ?");
                ps.setString(1, txtSearchCustomer.getText());
                rs = ps.executeQuery();
            }
            
            tbnCustomer.setRowCount(0);
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();
            
            for(int i = 1; i <= number; i++){
                column.add(metadata.getColumnName(i));
            }
            tbnCustomer.setColumnIdentifiers(column);
            
            while(rs.next()){
                row = new Vector();
                for(int i = 1; i <= number; i++){
                    row.addElement(rs.getString(i));
                }
                tbnCustomer.addRow(row);
                jTableCustomer.setModel(tbnCustomer);
            }
            
            if(tbnCustomer.getRowCount() == 0) JOptionPane.showMessageDialog(this, "You searched for nothing.");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            Connect a = new Connect();
            Connection con = a.getConnectDB();
            String str = "select sales.stocks.product_id, "
                    + "production.products.product_name, "
                    + "production.categories.category_name, "
                    + "sales.stocks.created_at, "
                    + "sales.stocks.good_till,"
                    + "production.brands.brand_name, "
                    + "production.brands.country, "
                    + "sales.stocks.quantity, "
                    + "sales.stocks.price, "
                    + "sales.stocks.discount from sales.stocks\n" +
                "inner join production.products on sales.stocks.product_id = production.products.product_id\n" +
                "inner join production.categories on production.categories.category_id = production.products.category_id\n" +
                "inner join production.brands on production.brands.brand_id = production.products.brand_id\n"
                    + "where ";
            PreparedStatement ps = null;
            int number;
            Vector row, column;
            column = new Vector();
            ResultSet rs = null;
            if(txtSearchProduct.getText().equals("")) {
                tbnProduct.setRowCount(0);
                loadDataProduct();
                return;
            } else if (jComboBoxProductSearch.getSelectedItem().toString().equals("Tên")) {
                ps = con.prepareStatement(str + "production.products.product_name like ?");
                ps.setString(1, txtSearchProduct.getText());
                rs = ps.executeQuery();
            } else if (jComboBoxProductSearch.getSelectedItem().toString().equals("ID")) {
                ps = con.prepareStatement(str + "sales.stocks.product_id like ?");
                ps.setString(1, txtSearchProduct.getText());
                rs = ps.executeQuery();
            } else if (jComboBoxProductSearch.getSelectedItem().toString().equals("Loại")) {
                ps = con.prepareStatement(str + "production.categories.category_name like ?");
                ps.setString(1, txtSearchProduct.getText());
                rs = ps.executeQuery();
            } else if (jComboBoxProductSearch.getSelectedItem().toString().equals("Hãng")) {
                ps = con.prepareStatement(str + "production.brands.brand_name like ?");
                ps.setString(1, txtSearchProduct.getText());
                rs = ps.executeQuery();
            } else if (jComboBoxProductSearch.getSelectedItem().toString().equals("Xuất xứ")) {
                ps = con.prepareStatement(str + "production.brands.country like ?");
                ps.setString(1, txtSearchProduct.getText());
                rs = ps.executeQuery();
            }
            
            tbnProduct.setRowCount(0);
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();
            
            for(int i = 1; i <= number; i++){
                column.add(metadata.getColumnName(i));
            }
            tbnProduct.setColumnIdentifiers(column);
            
            while(rs.next()){
                row = new Vector();
                for(int i = 1; i <= number; i++){
                    row.addElement(rs.getString(i));
                }
                tbnProduct.addRow(row);
                jTableProduct.setModel(tbnProduct);
            }
            
            if(tbnProduct.getRowCount() == 0) JOptionPane.showMessageDialog(this, "You searched for nothing.");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JComponent comp = (JComponent) evt.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();
        ManagerMain main = new ManagerMain();
        main.setVisible(true);        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        tbnCustomer.setRowCount(0);
        tbnProduct.setRowCount(0);
        tbnBill.setRowCount(0);
        jTableCustomer.setModel(tbnCustomer);
        jTableProduct.setModel(tbnProduct);
        jTableBill.setModel(tbnBill);
        txtBillID.setText("");
        txtCustomerName.setText("");
        txtDiscount.setText("");
        txtQuantity.setText("");
        txtSearchCustomer.setText("");
        txtSearchProduct.setText("");
        loadDataCustomer();
        loadDataProduct();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtSearchCustomerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchCustomerKeyReleased
        try {
            Connect a = new Connect();
            Connection con = a.getConnectDB();
            String str = "Select * from sales.customers where ";
            PreparedStatement ps = null;
            int number;
            Vector row, column;
            column = new Vector();
            ResultSet rs = null;
            String str1 = null;
            if (jComboBoxCustomerSearch.getSelectedItem().toString().equals("Tên")) {
                ps = con.prepareStatement(str + "name like ?");
                str1 = "%" + txtSearchCustomer.getText() + "%";
                ps.setString(1, str1);
                rs = ps.executeQuery();
            } else if (jComboBoxCustomerSearch.getSelectedItem().toString().equals("SĐT")) {
                ps = con.prepareStatement(str + "phone like ?");
                str1 = "%" + txtSearchCustomer.getText() + "%";
                ps.setString(1, str1);
                rs = ps.executeQuery();
            } else if (jComboBoxCustomerSearch.getSelectedItem().toString().equals("Email")) {
                ps = con.prepareStatement(str + "email like ?");
                str1 = "%" + txtSearchCustomer.getText() + "%";
                ps.setString(1, str1);
                rs = ps.executeQuery();
            } 
            
            tbnCustomer.setRowCount(0);
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();
            
            for(int i = 1; i <= number; i++){
                column.add(metadata.getColumnName(i));
            }
            tbnCustomer.setColumnIdentifiers(column);
            
            while(rs.next()){
                row = new Vector();
                for(int i = 1; i <= number; i++){
                    row.addElement(rs.getString(i));
                }
                tbnCustomer.addRow(row);
                jTableCustomer.setModel(tbnCustomer);
            }
            
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_txtSearchCustomerKeyReleased

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        txtCustomerName.setText(jTableCustomer.getValueAt(jTableCustomer.getSelectedRow(), 1) + "");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        txtCustomerName.setText("");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void txtSearchProductKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchProductKeyReleased
        try {
            Connect a = new Connect();
            Connection con = a.getConnectDB();
            String str = "select sales.stocks.product_id, "
                    + "production.products.product_name, "
                    + "production.categories.category_name, "
                    + "sales.stocks.created_at, "
                    + "sales.stocks.good_till,"
                    + "production.brands.brand_name, "
                    + "production.brands.country, "
                    + "sales.stocks.quantity, "
                    + "sales.stocks.price, "
                    + "sales.stocks.discount from sales.stocks\n" +
                "inner join production.products on sales.stocks.product_id = production.products.product_id\n" +
                "inner join production.categories on production.categories.category_id = production.products.category_id\n" +
                "inner join production.brands on production.brands.brand_id = production.products.brand_id\n"
                    + "where ";
            PreparedStatement ps = null;
            int number;
            Vector row, column;
            column = new Vector();
            ResultSet rs = null;
            String str1 = null;
            if (jComboBoxProductSearch.getSelectedItem().toString().equals("Tên")) {
                ps = con.prepareStatement(str + "production.products.product_name like ?");
                str1 = "%" + txtSearchProduct.getText() + "%";
                ps.setString(1, str1);
                rs = ps.executeQuery();
            } else if (jComboBoxProductSearch.getSelectedItem().toString().equals("ID")) {
                ps = con.prepareStatement(str + "sales.stocks.product_id like ?");
                str1 = "%" + txtSearchProduct.getText() + "%";
                ps.setString(1, str1);
                rs = ps.executeQuery();
            } else if (jComboBoxProductSearch.getSelectedItem().toString().equals("Loại")) {
                ps = con.prepareStatement(str + "production.categories.category_name like ?");
                str1 = "%" + txtSearchProduct.getText() + "%";
                ps.setString(1, str1);
                rs = ps.executeQuery();
            } else if (jComboBoxProductSearch.getSelectedItem().toString().equals("Hãng")) {
                ps = con.prepareStatement(str + "production.brands.brand_name like ?");
                str1 = "%" + txtSearchProduct.getText() + "%";
                ps.setString(1, str1);
                rs = ps.executeQuery();
            } else if (jComboBoxProductSearch.getSelectedItem().toString().equals("Xuất xứ")) {
                ps = con.prepareStatement(str + "production.brands.country like ?");
                str1 = "%" + txtSearchProduct.getText() + "%";
                ps.setString(1, str1);
                rs = ps.executeQuery();
            }
            
            tbnProduct.setRowCount(0);
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();
            
            for(int i = 1; i <= number; i++){
                column.add(metadata.getColumnName(i));
            }
            tbnProduct.setColumnIdentifiers(column);
            
            while(rs.next()){
                row = new Vector();
                for(int i = 1; i <= number; i++){
                    row.addElement(rs.getString(i));
                }
                tbnProduct.addRow(row);
                jTableProduct.setModel(tbnProduct);
            }
            
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_txtSearchProductKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(!isDouble(txtDiscount.getText()) || 
                Double.parseDouble(txtDiscount.getText()) < 0 ||
                Double.parseDouble(txtDiscount.getText()) > 100) {
            JOptionPane.showMessageDialog(this, "Lỗi ở Giảm giá.");
            return;
        }
        if(!isInt(txtQuantity.getText()) || Integer.parseInt(txtQuantity.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Lỗi ở Số lượng.");
            return;
        }
        
        String productName = jTableProduct.getValueAt(jTableProduct.getSelectedRow(), 1) + "";
        double singlePrice = Double.parseDouble(jTableProduct.getValueAt(jTableProduct.getSelectedRow(), 7) + "");
        double productDiscount = Double.parseDouble(jTableProduct.getValueAt(jTableProduct.getSelectedRow(), 8) + "");
        double eventDiscount = Double.parseDouble(txtDiscount.getText());
        int quantity = Integer.parseInt(txtQuantity.getText());
        double totalDiscount = productDiscount + eventDiscount - productDiscount*eventDiscount/100.00;
        System.out.println(totalDiscount);
        double totalSinglePrice = singlePrice * (1.00 - totalDiscount/100.00) * (double)quantity;
        System.out.println(totalSinglePrice);
        
        Vector row = new Vector();
        Vector column = new Vector();
        for(int i = 0; i < jTableBill.getColumnCount(); i++){
            column.add(jTableBill.getColumnName(i));
        }
        tbnBill.setColumnIdentifiers(column);
        row.add(productName);
        row.add(String.valueOf(singlePrice));
        row.add(String.valueOf(quantity));
        row.add(String.valueOf(totalDiscount));
        row.add(String.valueOf(totalSinglePrice));
        tbnBill.addRow(row);
        jTableBill.setModel(tbnBill);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        tbnBill.removeRow(jTableBill.getSelectedRow());
        jTableBill.setModel(tbnBill);
    }//GEN-LAST:event_jButton8ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBoxCustomerSearch;
    private javax.swing.JComboBox<String> jComboBoxProductSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableBill;
    private javax.swing.JTable jTableCustomer;
    private javax.swing.JTable jTableProduct;
    private javax.swing.JTextField txtBillID;
    private javax.swing.JTextField txtCustomerName;
    private javax.swing.JTextField txtDiscount;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSearchCustomer;
    private javax.swing.JTextField txtSearchProduct;
    // End of variables declaration//GEN-END:variables
}
