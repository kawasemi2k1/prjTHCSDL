package GUI;

import java.awt.Toolkit;
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


public class ThongKe_Customer extends javax.swing.JPanel {
    Map<String, Double> map = new HashMap<>();
    
    public ThongKe_Customer() {
        this.map = new HashMap<>();
        initComponents();
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xsize = (int) tk.getScreenSize().getWidth();
        int ysize = (int) tk.getScreenSize().getHeight();
        this.setSize(xsize, ysize);
        createMap();
        initFrame();
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
            String sql_doanhthu = "select top (5) sc.name as Ten, sum(so.price) as Tien from sales.customers sc\n" +
                                "left join sales.orders so on so.customer_id = sc.customer_id\n" +
                                "where sc.customer_id != 1 and so.created_date between (?) and (?) or sc.customer_id not in (select staff_id from sales.orders) \n" +
                                "group by sc.name\n" +
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
            System.out.println("Thong ke khach hang "+ex.toString());
        }
    }
     public JFreeChart createChart() {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Top 5 khách hàng quen thuộc",
                "Tên khách hàng", "Doanh thu",
                createDataset(), PlotOrientation.HORIZONTAL, false, false, false);
        return barChart;
    }

    private CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            dataset.addValue(value, "", key);
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        date1 = new com.toedter.calendar.JDateChooser();
        date2 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jcontent = new javax.swing.JTabbedPane();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setLayout(null);
        jPanel1.add(date1);
        date1.setBounds(620, 140, 160, 50);
        jPanel1.add(date2);
        date2.setBounds(1110, 140, 200, 40);

        jButton1.setText("Top 5 khách hàng quen thuộc");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(911, 210, 170, 70);
        jPanel1.add(jcontent);
        jcontent.setBounds(400, 310, 1140, 474);

        jButton2.setText("Thoát");
        jPanel1.add(jButton2);
        jButton2.setBounds(0, 660, 360, 120);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/THỐNG KÊ khách hàng.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, -10, 1560, 840);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1542, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        createMap();
        initFrame();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser date1;
    private com.toedter.calendar.JDateChooser date2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jcontent;
    // End of variables declaration//GEN-END:variables
}
