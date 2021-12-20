/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class BanHang extends javax.swing.JPanel {
    DefaultTableModel tbnCustomer = new DefaultTableModel();
    DefaultTableModel tbnProduct = new DefaultTableModel();
    DefaultTableModel tbnBill = new DefaultTableModel();
    String customerID = null;
    String customerPhone = null;
    String customerEmail = null;
    String billID = null;
    double totalBillPrice = 0;
    static int store = 1; //default to 1, until Login function
    static int staff = 1; //default to 1, until Login function
    /**
     * Creates new form BanHang
     */
    public BanHang() {
        initComponents();
        loadDataCustomer();
        loadDataProduct();
        loadDataStaff();
        loadDataBill();
        jButton11.setEnabled(false);
        jButton5.setEnabled(false);
        jButton8.setEnabled(false);
        if(jTableBill.getRowCount() == 0) {
            jButton7.setEnabled(false);
            jButton9.setEnabled(false);
            jButton6.setEnabled(false);
        }
    }
    
    private void loadDataStaff() {
        try {
            Connect a = new Connect();
            Connection con = a.getConnectDB();
            PreparedStatement ps = con.prepareStatement("Select name from sales.staffs where staff_id = ?;");
            ps.setString(1, String.valueOf(staff));
            ResultSet rs = ps.executeQuery();
            rs.next();
            txtStaff.setText(rs.getString(1));
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    
    private void loadDataCustomer(){
        try{
            Connect a = new Connect();
            Connection con = a.getConnectDB();
            Vector row, column;
            column = new Vector();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from vRealCustomer");
            
            for(int i = 0; i < jTableCustomer.getColumnCount(); i++){
                column.add(jTableCustomer.getColumnName(i));
            }
            tbnCustomer.setColumnIdentifiers(column);
            
            while(rs.next()){
                row = new Vector();
                for(int i = 1; i <= jTableCustomer.getColumnCount(); i++){
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
                        txtCustomer.setText(jTableCustomer.getValueAt(jTableCustomer.getSelectedRow(), 1) + "");
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
            Vector row, column;
            column = new Vector();
            PreparedStatement ps = con.prepareStatement("select sales.stocks.product_id, "
                    + "production.products.product_name, "
                    + "sales.stocks.created_at, "
                    + "sales.stocks.good_till,"
                    + "production.categories.category_name, "
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
            
            for(int i = 0; i < jTableProduct.getColumnCount(); i++){
                column.add(jTableProduct.getColumnName(i));
            }
            tbnProduct.setColumnIdentifiers(column);
            
            while(rs.next()){
                row = new Vector();
                for(int i = 1; i <= jTableProduct.getColumnCount(); i++){
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
                        txtProduct.setText(jTableProduct.getValueAt(jTableProduct.getSelectedRow(), 1) + "");
                    }
                }
            }
            );
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }
    
    private void loadDataBill() {
        Vector column = new Vector();
        for(int i = 0; i < jTableBill.getColumnCount(); i++){
            column.add(jTableBill.getColumnName(i));
        }
        tbnBill.setColumnIdentifiers(column);
        tbnBill.setRowCount(0);
        jTableBill.setModel(tbnBill);
        jTableBill.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent lse) {
                    if (jTableBill.getSelectedRow() >= 0) {
                        jButton8.setEnabled(true);
                    } else {
                        jButton8.setEnabled(false);
                    }
                }
            }
        );
        jTableBill.getModel().addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    if(jTableBill.getRowCount() > 0) {
                        jButton7.setEnabled(true);
                        jButton9.setEnabled(true);
                        jButton6.setEnabled(true);
                    } else {
                        jButton7.setEnabled(false);
                        jButton9.setEnabled(false);
                        jButton6.setEnabled(false);
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
    
    private int SlotToInsert() {
        int missingSlot = 1;
        ArrayList<Integer> Slots = new ArrayList<Integer>();
        try{
            Connect a = new Connect();
            Connection con = a.getConnectDB();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select order_id from sales.orders order by order_id");
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCustomer = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jComboBoxCustomerSearch = new javax.swing.JComboBox<>();
        txtSearchCustomer = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtCustomer = new javax.swing.JTextField();
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
        txtTotalBillPrice = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtStaff = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
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
        jLabel11 = new javax.swing.JLabel();
        txtProduct = new javax.swing.JTextField();
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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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

        jLabel12.setText("Tên khách hàng");

        txtCustomer.setEditable(false);
        txtCustomer.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtSearchCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxCustomerSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCustomer)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton11)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel4.setText("Hóa đơn");

        jTableBill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên sản phẩm", "NSX", "HSD", "Đơn giá", "Số lượng", "Giảm giá", "Thành tiền", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTableBill);

        jButton7.setText("Thanh toán");
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

        jButton9.setText("Cập nhật đơn");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel7.setText("ID");

        jButton10.setText("Tìm đơn");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        txtCustomerName.setEditable(false);
        txtCustomerName.setBackground(new java.awt.Color(204, 204, 204));

        jLabel8.setText("Tên khách hàng");

        jButton12.setText("Xóa");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton6.setText("Xóa tất cả sản phẩm");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        txtTotalBillPrice.setEditable(false);
        txtTotalBillPrice.setBackground(new java.awt.Color(204, 204, 204));

        jLabel9.setText("Tổng");

        jLabel10.setText("Nhân viên bán hàng");

        txtStaff.setEditable(false);
        txtStaff.setBackground(new java.awt.Color(204, 204, 204));

        jButton13.setText("Đơn mới");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalBillPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBillID, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton10))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton13)))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addComponent(txtStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jButton12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton9)
                    .addComponent(jButton13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalBillPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("Sản phẩm");

        jTableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên", "NSX", "HSD", "Loại", "Hãng", "Xuất xứ", "Số lượng", "Đơn giá", "Giảm giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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

        jLabel11.setText("Tên sản phẩm");

        txtProduct.setEditable(false);
        txtProduct.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtSearchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxProductSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtProduct)))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addComponent(txtProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton5)
                        .addComponent(jLabel5))
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            Connect a = new Connect();
            Connection con = a.getConnectDB();
            String str = "Select * from vRealCustomer where ";
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
            
            for(int i = 0; i < jTableCustomer.getColumnCount(); i++){
                column.add(jTableCustomer.getColumnName(i));
            }
            tbnCustomer.setColumnIdentifiers(column);
            
            while(rs.next()){
                row = new Vector();
                for(int i = 1; i <= jTableCustomer.getColumnCount(); i++){
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
                    + "sales.stocks.created_at, "
                    + "sales.stocks.good_till,"
                    + "production.categories.category_name, "
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
            Vector row, column;
            column = new Vector();
            ResultSet rs = null;
            if(txtSearchProduct.getText().equals("")) {
                tbnProduct.setRowCount(0);
                loadDataProduct();
                return;
            } else if (jComboBoxProductSearch.getSelectedItem().toString().equals("Tên")) {
                ps = con.prepareStatement(str + "store_id = ? and production.products.product_name like ?");
                ps.setString(1, String.valueOf(store));
                ps.setString(2, txtSearchProduct.getText());
                rs = ps.executeQuery();
            } else if (jComboBoxProductSearch.getSelectedItem().toString().equals("ID")) {
                ps = con.prepareStatement(str + "store_id = ? and sales.stocks.product_id like ?");
                ps.setString(1, String.valueOf(store));
                ps.setString(1, txtSearchProduct.getText());
                rs = ps.executeQuery();
            } else if (jComboBoxProductSearch.getSelectedItem().toString().equals("Loại")) {
                ps = con.prepareStatement(str + "store_id = ? and production.categories.category_name like ?");
                ps.setString(1, String.valueOf(store));
                ps.setString(1, txtSearchProduct.getText());
                rs = ps.executeQuery();
            } else if (jComboBoxProductSearch.getSelectedItem().toString().equals("Hãng")) {
                ps = con.prepareStatement(str + "store_id = ? and production.brands.brand_name like ?");
                ps.setString(1, String.valueOf(store));
                ps.setString(1, txtSearchProduct.getText());
                rs = ps.executeQuery();
            } else if (jComboBoxProductSearch.getSelectedItem().toString().equals("Xuất xứ")) {
                ps = con.prepareStatement(str + "store_id = ? and production.brands.country like ?");
                ps.setString(1, String.valueOf(store));
                ps.setString(1, txtSearchProduct.getText());
                rs = ps.executeQuery();
            }
            
            tbnProduct.setRowCount(0);
            
            for(int i = 0; i < jTableProduct.getColumnCount(); i++){
                column.add(jTableProduct.getColumnName(i));
            }
            tbnProduct.setColumnIdentifiers(column);
            
            while(rs.next()){
                row = new Vector();
                for(int i = 1; i <= jTableProduct.getColumnCount(); i++){
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
        txtQuantity.setText("");
        txtSearchCustomer.setText("");
        txtSearchProduct.setText("");
        txtTotalBillPrice.setText("");
        loadDataCustomer();
        loadDataProduct();
        loadDataBill();
        loadDataStaff();
        totalBillPrice = 0;
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
            
            for(int i = 0; i < jTableCustomer.getColumnCount(); i++){
                column.add(jTableCustomer.getColumnName(i));
            }
            tbnCustomer.setColumnIdentifiers(column);
            
            while(rs.next()){
                row = new Vector();
                for(int i = 1; i <= jTableCustomer.getColumnCount(); i++){
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
        customerID = jTableCustomer.getValueAt(jTableCustomer.getSelectedRow(), 0) + "";
        customerPhone = jTableCustomer.getValueAt(jTableCustomer.getSelectedRow(), 4) + "";
        customerEmail = jTableCustomer.getValueAt(jTableCustomer.getSelectedRow(), 5) + "";
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        txtCustomerName.setText("");
        customerID = "";
        customerPhone = "";
        customerEmail = "";
    }//GEN-LAST:event_jButton12ActionPerformed

    private void txtSearchProductKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchProductKeyReleased
        try {
            Connect a = new Connect();
            Connection con = a.getConnectDB();
            String str = "select sales.stocks.product_id, "
                    + "production.products.product_name, "
                    + "sales.stocks.created_at, "
                    + "sales.stocks.good_till,"
                    + "production.categories.category_name, "
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
                ps = con.prepareStatement(str + "store_id = ? and production.products.product_name like ?");
                ps.setString(1, String.valueOf(store));
                str1 = "%" + txtSearchProduct.getText() + "%";
                ps.setString(2, str1);
                rs = ps.executeQuery();
            } else if (jComboBoxProductSearch.getSelectedItem().toString().equals("ID")) {
                ps = con.prepareStatement(str + "store_id = ? and sales.stocks.product_id like ?");
                ps.setString(1, String.valueOf(store));
                str1 = "%" + txtSearchProduct.getText() + "%";
                ps.setString(2, str1);
                rs = ps.executeQuery();
            } else if (jComboBoxProductSearch.getSelectedItem().toString().equals("Loại")) {
                ps = con.prepareStatement(str + "store_id = ? and production.categories.category_name like ?");
                ps.setString(1, String.valueOf(store));
                str1 = "%" + txtSearchProduct.getText() + "%";
                ps.setString(2, str1);
                rs = ps.executeQuery();
            } else if (jComboBoxProductSearch.getSelectedItem().toString().equals("Hãng")) {
                ps = con.prepareStatement(str + "store_id = ? and production.brands.brand_name like ?");
                ps.setString(1, String.valueOf(store));
                str1 = "%" + txtSearchProduct.getText() + "%";
                ps.setString(2, str1);
                rs = ps.executeQuery();
            } else if (jComboBoxProductSearch.getSelectedItem().toString().equals("Xuất xứ")) {
                ps = con.prepareStatement(str + "store_id = ? and production.brands.country like ?");
                ps.setString(1, String.valueOf(store));
                str1 = "%" + txtSearchProduct.getText() + "%";
                ps.setString(2, str1);
                rs = ps.executeQuery();
            }
            
            tbnProduct.setRowCount(0);
            
            for(int i = 0; i < jTableProduct.getColumnCount(); i++){
                column.add(jTableProduct.getColumnName(i));
            }
            tbnProduct.setColumnIdentifiers(column);
            
            while(rs.next()){
                row = new Vector();
                for(int i = 1; i <= jTableProduct.getColumnCount(); i++){
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
        if(!isInt(txtQuantity.getText()) || Integer.parseInt(txtQuantity.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Thiếu thông tin Số lượng.");
            return;
        }
        
        int countInStocks = 0;
        int buying = 0;
        buying += Integer.parseInt(txtQuantity.getText());
        int rowToDelete = -1;
        
        for(int i = 0; i < jTableBill.getRowCount(); i++) {
            if(jTableProduct.getValueAt(jTableProduct.getSelectedRow(), 1).equals(jTableBill.getValueAt(i, 1)) &&
                    jTableProduct.getValueAt(jTableProduct.getSelectedRow(), 2).equals(jTableBill.getValueAt(i, 2)) &&
                    jTableProduct.getValueAt(jTableProduct.getSelectedRow(), 3).equals(jTableBill.getValueAt(i, 3))) {
                buying += Integer.parseInt(jTableBill.getValueAt(i, 5) + "");
                rowToDelete = i;
                break;
            }
        }
        
        try {
            Connect a = new Connect();
            Connection con = a.getConnectDB();
            PreparedStatement ps = con.prepareStatement("select quantity from sales.stocks "
                    + "where product_id = ? and "
                    + "created_at = ? and "
                    + "good_till = ? and "
                    + "store_id = ?;");
            ps.setString(1, jTableProduct.getValueAt(jTableProduct.getSelectedRow(), 0) + "");
            ps.setString(2, jTableProduct.getValueAt(jTableProduct.getSelectedRow(), 2) + "");
            ps.setString(3, jTableProduct.getValueAt(jTableProduct.getSelectedRow(), 3) + "");
            ps.setString(4, String.valueOf(store));
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            countInStocks = rs.getInt(1);
            if(countInStocks < buying) {
                String str = "Hàng không đủ trong kho.";
                
                PreparedStatement ps1 = con.prepareStatement("select sales.stores.name, sum(quantity) from sales.stocks "
                        + "inner join sales.stores on sales.stores.store_id = sales.stocks.store_id "
                        + "where product_id = ? and "
                        + "sales.stocks.store_id != ? "
                        + "group by sales.stores.store_id, sales.stores.name;");
                ps1.setString(1, jTableProduct.getValueAt(jTableProduct.getSelectedRow(), 0) + "");
                ps1.setString(2, String.valueOf(store));
                ResultSet rs1 = ps1.executeQuery();
                
                if(rs1.next() == false) {
                    str = str + "\nKhông kho nào khách còn hàng.";
                } else {
                    str = str + "\nCòn hàng tại:\n";
                    do {
                        str = str + rs1.getString(1) + ": " + rs1.getString(2) + "\n";
                    } while(rs1.next());
                }
                
                JOptionPane.showMessageDialog(this, str);
                return;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        
        String productName = jTableProduct.getValueAt(jTableProduct.getSelectedRow(), 1) + "";
        double singlePrice = Double.parseDouble(jTableProduct.getValueAt(jTableProduct.getSelectedRow(), 8) + "");
        double productDiscount = Double.parseDouble(jTableProduct.getValueAt(jTableProduct.getSelectedRow(), 9) + "");
        double totalSinglePrice = singlePrice * (1.00 - productDiscount/100.00) * (double)buying;
               
        Vector row = new Vector();
        Vector column = new Vector();
        for(int j = 0; j < jTableBill.getColumnCount(); j++){
            column.add(jTableBill.getColumnName(j));
        }
        tbnBill.setColumnIdentifiers(column);
        if(rowToDelete > -1) tbnBill.removeRow(rowToDelete);
        row.add(jTableProduct.getValueAt(jTableProduct.getSelectedRow(), 0) + "");
        row.add(productName);
        row.add(jTableProduct.getValueAt(jTableProduct.getSelectedRow(), 2) + "");
        row.add(jTableProduct.getValueAt(jTableProduct.getSelectedRow(), 3) + "");
        row.add(String.valueOf(singlePrice));
        row.add(String.valueOf(buying));
        row.add(String.valueOf(productDiscount));
        row.add(String.valueOf(totalSinglePrice));
        row.add("Mới");
        tbnBill.addRow(row);
        jTableBill.setModel(tbnBill);
        
        totalBillPrice += totalSinglePrice;
        txtTotalBillPrice.setText(String.valueOf(totalBillPrice));
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        float removedItem = Float.parseFloat(jTableBill.getValueAt(jTableBill.getSelectedRow(), 7) + "");
        totalBillPrice -= removedItem;
        txtTotalBillPrice.setText(String.valueOf(totalBillPrice));
        
        tbnBill.removeRow(jTableBill.getSelectedRow());
        jTableBill.setModel(tbnBill);
        if(jTableBill.getRowCount() == 0) {
           txtTotalBillPrice.setText("");
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        tbnBill.setRowCount(0);
        jTableBill.setModel(tbnBill);
        txtTotalBillPrice.setText("");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int result1 = JOptionPane.showConfirmDialog(this ,"Xác nhận để tiếp tục.", "Làm ơn xác nhận",
           JOptionPane.YES_NO_OPTION,
           JOptionPane.QUESTION_MESSAGE);
        if(result1 == JOptionPane.YES_OPTION){
            String mess = JOptionPane.showInputDialog(this, "Tiền khách đưa:");
            if(!isDouble(mess)) {
                JOptionPane.showMessageDialog(this, "Không phải số, vui lòng bấm Thanh toán lại.");
                return;
            } else if (Double.parseDouble(mess) < Double.parseDouble(txtTotalBillPrice.getText())) {
                JOptionPane.showMessageDialog(this, "Số tiền này không đủ, vui lòng bấm Thanh toán lại.");
                return;
            } else {
                double change = Double.parseDouble(mess) - Double.parseDouble(txtTotalBillPrice.getText());
                String strChange = String.valueOf(change);
                JOptionPane.showMessageDialog(this, "Tiền thừa: " + strChange + "\nThanh toán thành công.");
            }
            
            int seed = 0;
            if(txtCustomerName.getText().equals("")){
                int result = JOptionPane.showConfirmDialog(this ,"Chưa rõ khách hàng.\nBạn có muốn tiếp tục?", "Làm ơn xác nhận",
                   JOptionPane.YES_NO_OPTION,
                   JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION){
                   customerID = "1";
                }else if (result == JOptionPane.NO_OPTION){
                   return;
                }else {
                   return;
                }
            }

            try {
                Connect a = new Connect();
                Connection con = a.getConnectDB();

                if(SlotToInsert() >= 0){
                    seed = SlotToInsert();
                    System.out.println("Seed: " + seed);
                    PreparedStatement ps1 = con.prepareStatement("DBCC CHECKIDENT ('sales.orders', RESEED, ?);");
                    ps1.setInt(1, seed);
                    ps1.execute();
                }

                if(!txtCustomerName.getText().equals("")) {
                    PreparedStatement ps = con.prepareStatement("select customer_id from sales.customers "
                            + "where "
                            + "phone like ? and "
                            + "email like ? ;");
                    ps.setString(1, customerPhone);
                    ps.setString(2, customerEmail);
                    ResultSet rs = ps.executeQuery();
                    rs.next();
                    customerID = rs.getString(1);
                }

                PreparedStatement ps1 = con.prepareStatement("insert into sales.orders values (?, ?, ?, ?);");
                ps1.setString(1, customerID);
                ps1.setString(2, String.valueOf(staff));
                ps1.setObject(3, new Date());
                ps1.setString(4, String.valueOf(totalBillPrice));
                int update = ps1.executeUpdate();

                int update1 = 0, update2  = 0;
                PreparedStatement ps2 = con.prepareStatement("insert into sales.order_items values (?, ?, ?, ?, ?, ?, ?, ?);");
                for(int i=0; i < jTableBill.getRowCount(); i++) {
                    ps2.setString(1, String.valueOf(seed + 1));
                    ps2.setString(2, jTableBill.getValueAt(i, 0) + "");
                    System.out.println(jTableBill.getValueAt(i, 2) + "");
                    System.out.println(jTableBill.getValueAt(i, 3) + "");
                    ps2.setString(3, jTableBill.getValueAt(i, 2) + "");
                    ps2.setString(4, jTableBill.getValueAt(i, 3) + "");
                    ps2.setString(5, String.valueOf(store));
                    ps2.setString(6, jTableBill.getValueAt(i, 5) + "");
                    ps2.setString(7, jTableBill.getValueAt(i, 7) + "");
                    ps2.setString(8, jTableBill.getValueAt(i, 6) + "");
                    update1 = ps2.executeUpdate();


                    PreparedStatement ps3 = con.prepareStatement("update sales.stocks set quantity = quantity - ? "
                            + "where product_id = ? "
                            + "and created_at = ? "
                            + "and good_till = ? "
                            + "and store_id = ?;");
                    ps3.setInt(1, Integer.parseInt(jTableBill.getValueAt(i, 5) + ""));
                    ps3.setString(2, jTableBill.getValueAt(i, 0) + "");
                    ps3.setString(3, jTableBill.getValueAt(i, 2) + "");
                    ps3.setString(4, jTableBill.getValueAt(i, 3) + "");
                    ps3.setString(5, String.valueOf(store));
                    update2 = ps3.executeUpdate();
                }
                tbnProduct.setRowCount(0);
                jTableProduct.setModel(tbnProduct);
                loadDataProduct();

                if(update > 0 && update1 > 0 && update2 > 0) {
                    JOptionPane.showMessageDialog(this, "Chốt đơn thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Chốt đơn không thành công");
                }
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }else if (result1 == JOptionPane.NO_OPTION){
           return;
        }else {
           return;
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        if(txtBillID.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Không có từ khóa.");
            return;
        } else {
            tbnBill.setRowCount(0);
            jTableBill.setModel(tbnBill);
            jButton9.setEnabled(true);
            try {
                Connect a = new Connect();
                Connection con = a.getConnectDB();
                PreparedStatement ps = con.prepareStatement("select distinct "
                        + "sales.order_items.product_id, "
                        + "production.products.product_name, "
                        + "sales.order_items.created_at, "
                        + "sales.order_items.good_till, "
                        + "sales.stocks.price, "
                        + "sales.order_items.quantity, "
                        + "sales.order_items.discount, "
                        + "sales.order_items.price "
                        + "from sales.orders "
                        + "inner join sales.order_items on sales.orders.order_id = sales.order_items.order_id "
                        + "inner join sales.stocks on sales.order_items.product_id = sales.stocks.product_id "
                            + "and sales.order_items.created_at = sales.stocks.created_at "
                            + "and sales.order_items.good_till = sales.stocks.good_till "
                        + "inner join production.products on production.products.product_id = sales.stocks.product_id "
                        + "where sales.order_items.store_id = ? and sales.orders.order_id = ?;");
                ps.setString(1, String.valueOf(store));
                ps.setString(2, txtBillID.getText());
                ResultSet rs = ps.executeQuery();
                Vector row, column;
                column = new Vector();
                for(int i = 0; i < jTableBill.getColumnCount(); i++){
                    column.add(jTableBill.getColumnName(i));
                }
                tbnBill.setColumnIdentifiers(column);
                while(rs.next()){
                    row = new Vector();
                    for(int i = 1; i <= jTableBill.getColumnCount() - 1; i++){
                        row.addElement(rs.getString(i));
                    }
                    row.add("Cũ");
                    tbnBill.addRow(row);
                    jTableBill.setModel(tbnBill);
                }
                if(jTableBill.getRowCount() == 0) {
                    txtTotalBillPrice.setText("");
                    txtCustomerName.setText("");
                    totalBillPrice = 0;
                    JOptionPane.showMessageDialog(this, "Không tìm thấy.");
                    return;
                }
                
                PreparedStatement ps1 = con.prepareStatement("select name from sales.orders "
                        + "inner join sales.customers on sales.orders.customer_id = sales.customers.customer_id "
                        + "where sales.orders.order_id = ?;");
                ps1.setString(1, txtBillID.getText());
                ResultSet rs1 = ps1.executeQuery();
                rs1.next();
                txtCustomerName.setText(rs1.getString(1));
                
                PreparedStatement ps2 = con.prepareStatement("select price from sales.orders "
                        + "where order_id = ?;");
                ps2.setString(1, txtBillID.getText());
                ResultSet rs2 = ps2.executeQuery();
                rs2.next();
                txtTotalBillPrice.setText(rs2.getString(1));
                totalBillPrice = Float.parseFloat(rs2.getString(1));
                
                billID = txtBillID.getText();
                
                PreparedStatement ps3 = con.prepareStatement("select sales.staffs.name from sales.orders "
                        + "inner join sales.staffs on sales.staffs.staff_id = sales.orders.staff_id "
                        + "where order_id = ?;");
                ps3.setString(1, txtBillID.getText());
                ResultSet rs3 = ps3.executeQuery();
                rs3.next();
                txtStaff.setText(rs3.getString(1));
            } catch(Exception ex) {
                System.out.println(ex.toString());
            }
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        
        int result = JOptionPane.showConfirmDialog(this ,"Xác nhận để tiếp tục.", "Làm ơn xác nhận",
           JOptionPane.YES_NO_OPTION,
           JOptionPane.QUESTION_MESSAGE);
        if(result == JOptionPane.YES_OPTION){
           try {
                Connect a = new Connect();
                Connection con = a.getConnectDB();
                PreparedStatement ps = con.prepareStatement("delete from sales.order_items where order_id = ?;");
                ps.setString(1, billID);
                int update = ps.executeUpdate();
                
                int update1 = 0, update2  = 0;
                PreparedStatement ps2 = con.prepareStatement("insert into sales.order_items values (?, ?, ?, ?, ?, ?, ?, ?);");
                for(int i=0; i < jTableBill.getRowCount(); i++) {
                    ps2.setString(1, billID);
                    ps2.setString(2, jTableBill.getValueAt(i, 0) + "");
                    System.out.println(jTableBill.getValueAt(i, 2) + "");
                    System.out.println(jTableBill.getValueAt(i, 3) + "");
                    ps2.setString(3, jTableBill.getValueAt(i, 2) + "");
                    ps2.setString(4, jTableBill.getValueAt(i, 3) + "");
                    ps2.setString(5, String.valueOf(store));
                    ps2.setString(6, jTableBill.getValueAt(i, 5) + "");
                    ps2.setString(7, jTableBill.getValueAt(i, 7) + "");
                    ps2.setString(8, jTableBill.getValueAt(i, 6) + "");
                    update1 = ps2.executeUpdate();

                    if(jTableBill.getValueAt(i, 8).toString().equals("Mới")) {
                        PreparedStatement ps3 = con.prepareStatement("update sales.stocks set quantity = quantity - ? "
                                + "where product_id = ? "
                                + "and created_at = ? "
                                + "and good_till = ? "
                                + "and store_id = ?;");
                        ps3.setInt(1, Integer.parseInt(jTableBill.getValueAt(i, 5) + ""));
                        ps3.setString(2, jTableBill.getValueAt(i, 0) + "");
                        ps3.setString(3, jTableBill.getValueAt(i, 2) + "");
                        ps3.setString(4, jTableBill.getValueAt(i, 3) + "");
                        ps3.setString(5, String.valueOf(store));
                        update2 = ps3.executeUpdate();
                    }
                }
                tbnProduct.setRowCount(0);
                jTableProduct.setModel(tbnProduct);
                loadDataProduct();
                
                if(update > 0 && update1 > 0) {
                    JOptionPane.showMessageDialog(this, "Cập nhật đơn thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật đơn không thành công");
                }
           } catch (Exception ex) {
               System.out.println(ex.toString());
           }
        }else if (result == JOptionPane.NO_OPTION){
           return;
        }else {
           return;
        }
        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        tbnBill.setRowCount(0);
        jTableBill.setModel(tbnBill);
        loadDataBill();
        loadDataStaff();
        txtCustomerName.setText("");
        txtTotalBillPrice.setText("");
        txtBillID.setText("");
    }//GEN-LAST:event_jButton13ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JTextField txtCustomer;
    private javax.swing.JTextField txtCustomerName;
    private javax.swing.JTextField txtProduct;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSearchCustomer;
    private javax.swing.JTextField txtSearchProduct;
    private javax.swing.JTextField txtStaff;
    private javax.swing.JTextField txtTotalBillPrice;
    // End of variables declaration//GEN-END:variables
}
