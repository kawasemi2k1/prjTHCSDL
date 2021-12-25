package GUI;



import javax.mail.PasswordAuthentication;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.security.auth.kerberos.ServicePermission;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ThongKeProducts extends javax.swing.JPanel {
    DefaultTableModel tbn = new DefaultTableModel();
    sendMail sm=new sendMail();
    
    public ThongKeProducts() {
        initComponents();
        BoxBanchay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Số lượng", "Doanh Thu" }));
        BoxBancham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Số lượng", "Doanh Thu" }));
        
    }
     
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        TKbancham = new javax.swing.JLabel();
        BtnBanchay = new javax.swing.JButton();
        BtnBancham = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BoxBanchay = new javax.swing.JComboBox<>();
        BoxBancham = new javax.swing.JComboBox<>();

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bgtksp.png"))); // NOI18N

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        setLayout(null);

        TKbancham.setFont(new java.awt.Font("Sitka Text", 1, 36)); // NOI18N
        TKbancham.setForeground(new java.awt.Color(0, 0, 153));
        TKbancham.setText("THỐNG KÊ THEO SẢN PHẨM");
        add(TKbancham);
        TKbancham.setBounds(130, 20, 670, 100);

        BtnBanchay.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        BtnBanchay.setText("Thống kê");
        BtnBanchay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBanchayActionPerformed(evt);
            }
        });
        add(BtnBanchay);
        BtnBanchay.setBounds(170, 150, 90, 30);

        BtnBancham.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        BtnBancham.setText("Thống kê");
        BtnBancham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBanchamActionPerformed(evt);
            }
        });
        add(BtnBancham);
        BtnBancham.setBounds(430, 150, 90, 30);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("TOP 5 SP BÁN CHẠY");
        add(jLabel3);
        jLabel3.setBounds(20, 160, 160, 17);

        jButton3.setText("Thống kê");
        add(jButton3);
        jButton3.setBounds(170, 150, 90, 30);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("TOP 5 SP BÁN CHẬM");
        add(jLabel4);
        jLabel4.setBounds(270, 150, 150, 30);

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

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 210, 690, 402);

        BoxBanchay.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        BoxBanchay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        BoxBanchay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxBanchayActionPerformed(evt);
            }
        });
        add(BoxBanchay);
        BoxBanchay.setBounds(170, 100, 90, 30);

        BoxBancham.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        BoxBancham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        BoxBancham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxBanchamActionPerformed(evt);
            }
        });
        add(BoxBancham);
        BoxBancham.setBounds(430, 100, 90, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBanchamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBanchamActionPerformed
        // TODO add your handling code here:
            try{
                
            Connect a = new Connect();
            Connection conn =a.getConnection();
            String sql_soluong = "Select TOP 5 With TIES sales.order_items.product_id, sum(sales.order_items.quantity) as total "
                    + ",production.products.product_name,production.products.price from sales.order_items "
                    + "inner join production.products on sales.order_items.product_id = production.products.product_id "
                    + "group by sales.order_items.product_id,production.products.product_name,production.products.price "
                    + "order by total ASC";
            String sql_doanhthu = "Select TOP 5 With TIES sales.order_items.product_id, sum(sales.order_items.quantity)*production.products.price as total"
                    + ",production.products.product_name,production.products.price from sales.order_items "
                    + "inner join production.products on sales.order_items.product_id = production.products.product_id "
                    + "group by sales.order_items.product_id,production.products.product_name,production.products.price "
                    + "order by total ASC";
          
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql_soluong);
            Statement st1 = conn.createStatement();
            ResultSet rs1 = st1.executeQuery(sql_doanhthu);
            if(BoxBancham.getSelectedItem().toString().equals("Số lượng")){
                tbn.setRowCount(0);
            String []colsName = {"Product_ID", "Product_Name","Price","Quantity"};
            tbn.setColumnIdentifiers(colsName);
            while(rs.next()){
                    String value[] = new String[4];
                    value[0]=rs.getInt("product_id")+"";
                    value[1]=rs.getString("product_name");
                    value[2]=rs.getDouble("price")+"";
                    value[3]=rs.getInt("total")+"";
                    tbn.addRow(value);
                    jTable1.setModel(tbn);       
            }     
            } else if(BoxBancham.getSelectedItem().toString().equals("Doanh Thu")){
                tbn.setRowCount(0);
                    String []colsName = {"Product_ID", "Product_Name","Price","TotalPrice"};
            tbn.setColumnIdentifiers(colsName);
            while(rs1.next()){
                    String value[] = new String[4];
                    value[0]=rs1.getInt("product_id")+"";
                    value[1]=rs1.getString("product_name");
                    value[2]=rs1.getDouble("price")+"";
                    value[3]=rs1.getDouble("total")+"";
                    tbn.addRow(value);
                    jTable1.setModel(tbn);       
            } 
            }
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_BtnBanchamActionPerformed

    private void BtnBanchayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBanchayActionPerformed
        // TODO add your handling code here:
        try{
            Connect a = new Connect();
            Connection conn =a.getConnection();
            String sql_soluong = "Select TOP 5 With TIES sales.order_items.product_id, sum(sales.order_items.quantity) as total"
                    + ",production.products.product_name,production.products.price from sales.order_items "
                    + "inner join production.products on sales.order_items.product_id = production.products.product_id "
                    + "group by sales.order_items.product_id,production.products.product_name,production.products.price "
                    + "order by total DESC ";
            String sql_doanhthu = "Select TOP 5 With TIES sales.order_items.product_id, (sum(sales.order_items.quantity)*production.products.price) as total"
                    + ",production.products.product_name,production.products.price from sales.order_items "
                    + "inner join production.products on sales.order_items.product_id = production.products.product_id "
                    + "group by sales.order_items.product_id,production.products.product_name,production.products.price "
                    + "order by total DESC";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql_soluong);
            Statement st1 = conn.createStatement();
            ResultSet rs1 = st1.executeQuery(sql_doanhthu);
            if(BoxBanchay.getSelectedItem().toString().equals("Số lượng")){
                   tbn.setRowCount(0);
            String []colsName = {"Product_ID", "Product_Name","Price","Quantity"};
            tbn.setColumnIdentifiers(colsName);
            while(rs.next()){
                    String value[] = new String[4];
                    value[0]=rs.getInt("product_id")+"";
                    value[1]=rs.getString("product_name");
                    value[2]=rs.getDouble("price")+"";
                    value[3]=rs.getInt("total")+"";
                    tbn.addRow(value);
                    jTable1.setModel(tbn);       
            }     
            } else if(BoxBanchay.getSelectedItem().toString().equals("Doanh Thu")){
                    tbn.setRowCount(0);
                    String []colsName = {"Product_ID", "Product_Name","Price","TotalPrice"};
            tbn.setColumnIdentifiers(colsName);
            while(rs1.next()){
                    String value1[] = new String[4];
                    value1[0]=rs1.getInt("product_id")+"";
                    value1[1]=rs1.getString("product_name");
                    value1[2]=rs1.getDouble("price")+"";
                    value1[3]=rs1.getDouble("total")+"";
                    tbn.addRow(value1);
                    jTable1.setModel(tbn);       
            } 
            }   
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
         
    }//GEN-LAST:event_BtnBanchayActionPerformed

    private void BoxBanchayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxBanchayActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_BoxBanchayActionPerformed

    private void BoxBanchamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxBanchamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxBanchamActionPerformed
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> BoxBancham;
    private javax.swing.JComboBox<String> BoxBanchay;
    private javax.swing.JButton BtnBancham;
    private javax.swing.JButton BtnBanchay;
    private javax.swing.JLabel TKbancham;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.MenuBar menuBar1;
    // End of variables declaration//GEN-END:variables
}
