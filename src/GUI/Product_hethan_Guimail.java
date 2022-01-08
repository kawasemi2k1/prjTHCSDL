package GUI;



import Utils.Connect;
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
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;




public class Product_hethan_Guimail extends javax.swing.JPanel {
    DefaultTableModel tbn = new DefaultTableModel();
    
    public Product_hethan_Guimail() {
        initComponents();
       
        
    }
     public void sendmail(String mail) {
        try {
            Email email = new SimpleEmail();

            // Cấu hình thông tin Email Server
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("phamthanhchung2001@gmail.com", "phamchung186")); //Nhớ nhập đúng với tài khoản thật nhé :))

            // Với gmail cái này là bắt buộc.
            email.setSSLOnConnect(true);

            // Người gửi
            email.setFrom("phamthanhchung2001@gmail.com", "Công chúa phép thuật");

            // Tiêu đề
            email.setSubject("Ta sẽ trùng trị ngươi"); //Tiêu đề khi gửi email

            // Nội dung email
            //String covert = String.valueOf(rand);
            email.setMsg("Hãy xem đây, biến hình Winx\\n EN CHAN TÍT\\n"); //Nội dung email bạn muốn gửi.
            // Người nhận
            email.addTo(mail); //Đia chỉ email người nhận
            email.send(); //Thực hiện gửi.
            System.err.println("Gửi email thành công ! Vui lòng kiểm tra email !");
            System.out.println("\n");
        } catch (Exception e) {
            System.out.println("Gửi không thành công !");
        }
    }
     
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        TKbancham = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        BtnHethan = new javax.swing.JButton();
        btnsend = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

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

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Mặt hàng sắp hết hạn");
        add(jLabel1);
        jLabel1.setBounds(50, 130, 140, 30);

        BtnHethan.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        BtnHethan.setText("Thống kê");
        BtnHethan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHethanActionPerformed(evt);
            }
        });
        add(BtnHethan);
        BtnHethan.setBounds(220, 130, 90, 30);

        btnsend.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnsend.setText("Send");
        btnsend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsendActionPerformed(evt);
            }
        });
        add(btnsend);
        btnsend.setBounds(600, 130, 73, 25);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Gửi Email");
        add(jLabel5);
        jLabel5.setBounds(490, 130, 100, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnHethanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHethanActionPerformed
        try{
            Connect a = new Connect();
            Connection conn =a.getConnectDB();
            String sql_hetdate = "Select SS.product_id,PPRO.price,PPRO.product_name ,"
                    + "SS.good_till from sales.stocks as SS inner join production.products as PPRO "
                    + "on SS.product_id = PPRO.product_id where( DATEDIFF(day,getdate(),SS.good_till) < 30)";
            Statement st = conn.createStatement();
            ResultSet rs =st.executeQuery(sql_hetdate);
            tbn.setRowCount(0);
            String []colsName = {"Product_ID", "Product_Name","Price","Good_Till_Date"};
            tbn.setColumnIdentifiers(colsName);
            while(rs.next()){
                    String value[] = new String[4];
                    value[0]=rs.getInt("product_id")+"";
                    value[1]=rs.getString("product_name");
                    value[2]=rs.getDouble("price")+"";
                    value[3]=rs.getDate("good_till")+"";
                    //value[4]="10%";
                    tbn.addRow(value);
                    jTable1.setModel(tbn);       
            } 
            
        }catch(Exception ex){
            System.out.println(ex.toString());
        }

    }//GEN-LAST:event_BtnHethanActionPerformed
  
    private void btnsendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsendActionPerformed
        // TODO add your handling code here:
     
//       String ToEmail = txtEmailsend.getText();
//        String FromEmailPassword = txtpass.getText();//You email Password from you want to send email
//        
//        
//        Properties properties = new Properties();
//        properties.put("mail.smtp.auth","true");
//        properties.put("mail.smtp.starttls.enable","true");
//        properties.put("mail.smtp.host","smtp.gmail.com");
//        properties.put("mail.smtp.port","587");
//        
//        Session session = Session.getInstance(properties,new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication(){
//                return new PasswordAuthentication(ToEmail,FromEmailPassword);
//            }
//        });
        
//        try{
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(txtEmailsend.getText()));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(txtemailto.getText()));
//            message.setSubject(txtsubject.getText());
//            message.setText(txtmessage.getText());
//            Transport.send(message);
//            JOptionPane.showMessageDialog(this, "Email sent");
//        }catch(Exception ex){
//            System.out.println(ex.toString());
//        }
System.out.println("11111111111111");              
sendmail("bovainghiengnang@gmail.com ");
                            
    }//GEN-LAST:event_btnsendActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnHethan;
    private javax.swing.JLabel TKbancham;
    private javax.swing.JButton btnsend;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.MenuBar menuBar1;
    // End of variables declaration//GEN-END:variables
}
