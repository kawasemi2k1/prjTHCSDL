package GUI;


import static com.sun.org.apache.xalan.internal.lib.ExsltDynamic.map;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;


public class ThongKeBrands extends javax.swing.JPanel {
    DefaultTableModel tbn = new DefaultTableModel();
    
   
    
     
    public ThongKeBrands() {
        initComponents();
        BoxTKBrand_Chay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Số lượng", "Doanh Thu" }));
        BoxTKBrand_Cham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Số lượng", "Doanh Thu" }));
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnBanchay = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        BoxTKBrand_Chay = new javax.swing.JComboBox<>();
        BtnBancham = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        BoxTKBrand_Cham = new javax.swing.JComboBox<>();

        setLayout(null);

        BtnBanchay.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        BtnBanchay.setText("Thống Kê");
        BtnBanchay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBanchayActionPerformed(evt);
            }
        });
        add(BtnBanchay);
        BtnBanchay.setBounds(210, 140, 100, 30);

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
        jScrollPane1.setBounds(32, 200, 780, 402);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("THỐNG KÊ THEO BRANDS");
        add(jLabel1);
        jLabel1.setBounds(250, 10, 390, 60);

        BoxTKBrand_Chay.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        BoxTKBrand_Chay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        BoxTKBrand_Chay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxTKBrand_ChayActionPerformed(evt);
            }
        });
        add(BoxTKBrand_Chay);
        BoxTKBrand_Chay.setBounds(210, 80, 100, 30);

        BtnBancham.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        BtnBancham.setText("Thống Kê");
        BtnBancham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBanchamActionPerformed(evt);
            }
        });
        add(BtnBancham);
        BtnBancham.setBounds(500, 140, 100, 30);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("TOP 5 BRANDS BÁN CHẬM");
        add(jLabel2);
        jLabel2.setBounds(600, 110, 200, 30);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("TOP 5 BRANDS BÁN CHẠY");
        add(jLabel3);
        jLabel3.setBounds(30, 100, 190, 50);

        BoxTKBrand_Cham.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        BoxTKBrand_Cham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        BoxTKBrand_Cham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxTKBrand_ChamActionPerformed(evt);
            }
        });
        add(BoxTKBrand_Cham);
        BoxTKBrand_Cham.setBounds(500, 80, 100, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBanchayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBanchayActionPerformed
        // TODO add your handling code here:
        try{
            Connect a = new Connect();
            Connection conn =a.getConnection();
            String sql_soluong = "SELECT TOP 5 With TIES pb.brand_id,pb.brand_name,pb.country,count(PPRO.product_id) as number_product,sum(quantity) as total from sales.order_items as ODI " +
            " inner join production.products as PPRO on ODI.product_id = PPRO.product_id " +
            " inner join production.brands as pb on PPRO.brand_id = pb.brand_id " +
            " group by PB.brand_id,pb.brand_name,pb.country order by total DESC ";
            String sql_doanhthu = "SELECT TOP 5 With TIES pb.brand_id,pb.brand_name,pb.country,count(PPRO.product_id)as number_product,sum(PPRO.price*quantity) as total from sales.order_items as ODI " +
            " inner join production.products as PPRO on ODI.product_id = PPRO.product_id " +
            " inner join production.brands as pb on PPRO.brand_id = pb.brand_id " +
            " group by PB.brand_id,pb.brand_name,pb.country order by total DESC ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql_soluong);
            Statement st1 = conn.createStatement();
            ResultSet rs1 = st1.executeQuery(sql_doanhthu);
            if(BoxTKBrand_Chay.getSelectedItem().toString().equals("Số lượng")){
                tbn.setRowCount(0);
            String []colsName = {"Brand_ID", "Brand_Name","Country","Number_of_Product","Quantity"};
            tbn.setColumnIdentifiers(colsName);
                while(rs.next()){
                    String value[] = new String[5];
                    value[0]=rs.getString("brand_id");
                    value[1]=rs.getString("brand_name");
                    value[2]=rs.getString("country");
                    value[3]=rs.getInt("number_product")+"";
                    value[4]=rs.getInt("total")+"";
                    tbn.addRow(value);
                    jTable1.setModel(tbn);       
                }  
            }else if(BoxTKBrand_Chay.getSelectedItem().toString().equals("Doanh Thu")){
                tbn.setRowCount(0);
            String []colsName = {"Brand_ID", "Brand_Name","Country","Number_of_Product","TotalPrice"};
            tbn.setColumnIdentifiers(colsName);
                while(rs1.next()){
                    String value[] = new String[5];
                    value[0]=rs1.getString("brand_id");
                    value[1]=rs1.getString("brand_name");
                    value[2]=rs1.getString("country");
                    value[3]=rs1.getInt("number_product")+"";
                    value[4]=rs1.getInt("total")+"";
                    tbn.addRow(value);
                    jTable1.setModel(tbn);       
                }  
            }
            
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
        
        
        
    }//GEN-LAST:event_BtnBanchayActionPerformed

    private void BtnBanchamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBanchamActionPerformed
        // TODO add your handling code here:
         try{
            Connect a = new Connect();
            Connection conn =a.getConnection();
            String sql_soluong = "SELECT TOP 5 With TIES pb.brand_id,pb.brand_name,pb.country,count(PPRO.product_id) as number_product ,sum(quantity) as total from sales.order_items as ODI " +
            " inner join production.products as PPRO on ODI.product_id = PPRO.product_id " +
            " inner join production.brands as pb on PPRO.brand_id = pb.brand_id " +
            " group by PB.brand_id,pb.brand_name,pb.country order by total ASC ";
            String sql_doanhthu = "SELECT TOP 5 With TIES pb.brand_id,pb.brand_name,pb.country,count(PPRO.product_id) as number_product,sum(PPRO.price*quantity) as total from sales.order_items as ODI " +
            " inner join production.products as PPRO on ODI.product_id = PPRO.product_id " +
            " inner join production.brands as pb on PPRO.brand_id = pb.brand_id " +
            " group by PB.brand_id,pb.brand_name,pb.country order by total ASC ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql_soluong);
            Statement st1 = conn.createStatement();
            ResultSet rs1 = st1.executeQuery(sql_doanhthu);
            if(BoxTKBrand_Cham.getSelectedItem().toString().equals("Số lượng")){
                tbn.setRowCount(0);
            String []colsName = {"Brand_ID", "Brand_Name","Country","Number_of_Product","Quantity"};
            tbn.setColumnIdentifiers(colsName);
                while(rs.next()){
                    String value[] = new String[5];
                    value[0]=rs.getString("brand_id");
                    value[1]=rs.getString("brand_name");
                    value[2]=rs.getString("country");
                    value[3]=rs.getInt("number_product")+"";
                    value[4]=rs.getInt("total")+"";
                    tbn.addRow(value);
                    jTable1.setModel(tbn);       
                }  
            }else if(BoxTKBrand_Cham.getSelectedItem().toString().equals("Doanh Thu")){
                tbn.setRowCount(0);
            String []colsName = {"Brand_ID", "Brand_Name","Country","Number_of_Product","TotalPrice"};
            tbn.setColumnIdentifiers(colsName);
                while(rs1.next()){
                    String value[] = new String[5];
                    value[0]=rs1.getString("brand_id");
                    value[1]=rs1.getString("brand_name");
                    value[2]=rs1.getString("country");
                    value[3]=rs1.getInt("number_product")+"";
                    value[4]=rs1.getDouble("total")+"";
                    tbn.addRow(value);
                    jTable1.setModel(tbn);       
                }  
            }
            
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
        
    }//GEN-LAST:event_BtnBanchamActionPerformed

    private void BoxTKBrand_ChayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxTKBrand_ChayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxTKBrand_ChayActionPerformed

    private void BoxTKBrand_ChamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxTKBrand_ChamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxTKBrand_ChamActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> BoxTKBrand_Cham;
    private javax.swing.JComboBox<String> BoxTKBrand_Chay;
    private javax.swing.JButton BtnBancham;
    private javax.swing.JButton BtnBanchay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
