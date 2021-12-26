/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author MyPC
 */
public class ThongKe_Cat extends javax.swing.JPanel {

    /**
     * Creates new form ThongKe_Cat
     */
    Map<String, Double> map = new HashMap<>();
    Map<String, Integer> map2 = new HashMap<>();

    public ThongKe_Cat() {
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
            Connection conn = a.getConnection();
            String sql_doanhthu = "select pc.category_name as Ten, sum(so.price) as Tien from production.categories pc \n"
                    + "left join production.products pp on pc.category_id = pp.category_id\n"
                    + "left join sales.order_items sot on sot.product_id = pp.product_id\n"
                    + "left join sales.orders so on sot.order_id = so.order_id\n"
                    + "where so.created_date between (?) and (?) OR pp.product_id not in (select product_id from sales.order_items)\n"
                    + "group by pc.category_name";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql_doanhthu);
            java.sql.Date jdate1 = new java.sql.Date(tdate1.getTime());
            java.sql.Date jdate2 = new java.sql.Date(tdate2.getTime());
            ps.setDate(1, jdate1);
            ps.setDate(2, jdate2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("1");
                String name = rs.getString("Ten");
                Double tien = rs.getDouble("Tien");
                map.put(name, tien);
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public JFreeChart createChart() {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Thống kê doanh thu loại mặt hàng",
                "Tên mặt hàng", "Số tiền",
                createDataset(), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    private CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            dataset.addValue(value, "Số tiền", key);
        }
        return dataset;
    }

    public void initFrame() {

        /*
        ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new java.awt.Dimension(1412, 372));
        jcontent.add(chartPanel);
        jcontent.setVisible(true);
         */
        ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        JFrame frame = new JFrame();
        frame.add(chartPanel);
        frame.setTitle("Biểu đồ mặt hàng");
        frame.setSize(1000, 400);
        frame.setMaximumSize(getMaximumSize());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    public void createMap2() {
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
            Connection conn = a.getConnection();
            String sql_doanhthu = "select pc.category_name as Ten, count(sot.order_id) as So from production.categories pc \n"
                    + "left join production.products pp on pc.category_id = pp.category_id\n"
                    + "left join sales.order_items sot on sot.product_id = pp.product_id\n"
                    + "left join sales.orders so on sot.order_id = so.order_id\n"
                    + "where so.created_date between (?) and (?) OR pp.product_id not in (select product_id from sales.order_items)\n"
                    + "group by pc.category_name";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql_doanhthu);
            java.sql.Date jdate1 = new java.sql.Date(tdate1.getTime());
            java.sql.Date jdate2 = new java.sql.Date(tdate2.getTime());
            ps.setDate(1, jdate1);
            ps.setDate(2, jdate2);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData metadata = rs.getMetaData();
            Integer number = metadata.getColumnCount();
            System.out.println(number);
            while (rs.next()) {
                String name = rs.getString("Ten");
                Double so = rs.getDouble("So");
                map.put(name, so);
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public JFreeChart createChart2() {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Thống kê số lượng bán đợc của mặt hàng",
                "Tên mặt hàng", "Số lượng",
                createDataset(), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    private CategoryDataset createDataset2() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : map2.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            dataset.addValue(value, "Số lượng", key);
        }
        return dataset;
    }

    public void initFrame2() {

        /*
        ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new java.awt.Dimension(1412, 372));
        jcontent.add(chartPanel);
        jcontent.setVisible(true);
         */
        ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        JFrame frame = new JFrame();
        frame.add(chartPanel);
        frame.setTitle("Biểu đồ mặt hàng");
        frame.setSize(1000, 400);
        frame.setMaximumSize(getMaximumSize());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcontent = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        date1 = new com.toedter.calendar.JDateChooser();
        date2 = new com.toedter.calendar.JDateChooser();
        label1 = new java.awt.Label();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        javax.swing.GroupLayout jcontentLayout = new javax.swing.GroupLayout(jcontent);
        jcontent.setLayout(jcontentLayout);
        jcontentLayout.setHorizontalGroup(
            jcontentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jcontentLayout.setVerticalGroup(
            jcontentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 376, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Thống kê theo loại hàng");

        jButton1.setText("Thống kê theo doanh thu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        label1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label1.setText("Từ ngày");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Đến ngày");

        jButton2.setText("Thống kê theo số lượng");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jcontent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(373, 373, 373))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(168, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(jcontent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private javax.swing.JPanel jcontent;
    private java.awt.Label label1;
    // End of variables declaration//GEN-END:variables
}
