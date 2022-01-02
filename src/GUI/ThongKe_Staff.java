package GUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class ThongKe_Staff extends javax.swing.JPanel {
    
    Map<String, Double> map = new HashMap<>();
    Map<String, Double> map2 = new HashMap<>();
    
    public ThongKe_Staff() {
        this.map2 = new HashMap<>();
        initComponents();
    }
    
    public void createMap() {
        Date tdate1 = date1.getDate();
        Date tdate2 = date2.getDate();
        Date tdate = new Date();
        if (tdate1.after(tdate2)) {
            JOptionPane.showMessageDialog(this, "Ngày không hợp lệ");
            return;
        } else if (tdate1.after(tdate)) {
            JOptionPane.showMessageDialog(this, "Ngày không hợp lệ");
            return;
        }
        try {
            Connect a = new Connect();
            Connection conn = a.getConnectDB();
            String sql_doanhthu = "select top (5) ss.name as Ten, sum(so.price) as Tien from sales.staffs ss\n" +
                                "left join sales.orders so on so.staff_id = ss.staff_id\n" +
                                "where so.created_date between (?) and (?) or ss.staff_id not in (select staff_id from sales.orders)\n" +
                                "group by ss.name\n" +
                                "order by Tien desc";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql_doanhthu);
            java.sql.Date jdate1 = new java.sql.Date(tdate1.getTime());
            java.sql.Date jdate2 = new java.sql.Date(tdate2.getTime());
            ps.setDate(1, jdate1);
            ps.setDate(2, jdate2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Ten");
                Double tien = rs.getDouble("Tien");
                map.put(name, tien);
            }

        } catch (Exception ex) {
            System.out.println("Thong ke doanh thu "+ex.toString());
        }
    }
     public JFreeChart createChart() {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Top 5 nhân viên đạt doanh thu cao nhất",
                "Tên nhân viên", "Doanh thu",
                createDataset(), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    private CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            dataset.addValue(value, "Doanh thu", key);
        }
        return dataset;
    }
      public void initFrame() {
        ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));
        jcontent.removeAll();
        jcontent.add(chartPanel);
        jcontent.setVisible(true);
    }
      
    public void createMap2() {
        
        try {
            Date tdate1 = date1.getDate();
            Date tdate2 = date2.getDate();
            Date tdate = new Date();
            if (tdate1.after(tdate2)) {
                JOptionPane.showMessageDialog(this, "Ngày không hợp lệ");
                return;
            } else if (tdate1.after(tdate)) {
                JOptionPane.showMessageDialog(this, "Ngày không hợp lệ");
                return;
            }
            Connect a = new Connect();
            Connection conn = a.getConnectDB();
            String sql_doanhthu = "select top (5) ss.name as Ten, sum(so.price) as Tien from sales.staffs ss\n" +
                                "left join sales.orders so on so.staff_id = ss.staff_id\n" +
                                "where so.created_date between (?) and (?) or ss.staff_id not in (select staff_id from sales.orders)\n" +
                                "group by ss.name\n" +
                                "order by Tien asc";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql_doanhthu);
            java.sql.Date jdate1 = new java.sql.Date(tdate1.getTime());
            java.sql.Date jdate2 = new java.sql.Date(tdate2.getTime());
            ps.setDate(1, jdate1);
            ps.setDate(2, jdate2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Ten");
                Double tien = rs.getDouble("Tien");
                map2.put(name, tien);
            }

        } catch (Exception ex) {
            System.out.println("Thong ke doanh thu "+ex.toString());
        }
    }
    
        private CategoryDataset createDataset2() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Double> entry : map2.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            dataset.addValue(value, "Doanh thu", key);
        }
        return dataset;
    }
     public JFreeChart createChart2() {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Top 5 nhân viên đạt doanh thu thấp nhất",
                "Tên nhân viên", "Doanh thu",
                createDataset2(), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

       public void initFrame2() {
        ChartPanel chartPanel = new ChartPanel(createChart2());
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));
        jcontent.removeAll();
        jcontent.add(chartPanel);
        jcontent.setVisible(true);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        date1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        date2 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jcontent = new javax.swing.JTabbedPane();
        jButton2 = new javax.swing.JButton();

        jLabel1.setText("Từ ngày");

        jLabel2.setText("Đến ngày");

        jButton1.setText("Top 5 nhân viên có doanh thu cao nhất");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Top 5 nhân viên có doanh thu thấp nhất");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(190, 190, 190)
                        .addComponent(jLabel2)
                        .addGap(56, 56, 56)
                        .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jcontent, javax.swing.GroupLayout.PREFERRED_SIZE, 1291, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jButton1)
                        .addGap(126, 126, 126)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jcontent, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        createMap();
        initFrame();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        createMap2();
        initFrame2();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser date1;
    private com.toedter.calendar.JDateChooser date2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jcontent;
    // End of variables declaration//GEN-END:variables
}
