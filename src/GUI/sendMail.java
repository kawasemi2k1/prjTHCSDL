/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author MyPC
 */
public class sendMail {
//    public void sendmail(String mailNguoiNhan) {
//        try {
//            Email email = new SimpleEmail();
//
//            // Cấu hình thông tin Email Server
//            email.setHostName("smtp.googlemail.com");
//            email.setSmtpPort(465);
//            email.setAuthenticator(new DefaultAuthenticator("floratiennuthiennhien0990@gmail.com", "sucmanhEnchantix")); //Nhớ nhập đúng với tài khoản thật nhé :))
//
//            // Với gmail cái này là bắt buộc.
//            email.setSSLOnConnect(true);
//
//            // Người gửi
//            email.setFrom("floratiennuthiennhien0990@gmail.com", "Công chúa phép thuật");
//
//            // Tiêu đề
//            email.setSubject("Ta sẽ trùng trị ngươi"); //Tiêu đề khi gửi email
//            Object rand = null;
//
//            // Nội dung email
//            String covert = String.valueOf(rand);
//            email.setMsg("Hãy xem đây, biến hình Winx\n EN CHAN TÍT\n"); //Nội dung email bạn muốn gửi.
//            // Người nhận
//            email.addTo(mailNguoiNhan); //Đia chỉ email người nhận
//            email.send(); //Thực hiện gửi.
//            System.err.println("Gửi email thành công ! Vui lòng kiểm tra email !");
//            System.out.println("\n");
//        } catch (Exception e) {
//            System.out.println("Gửi không thành công !");
//        }
//    }
    public void sendmail(String mail) {
        try {
            Email email = new SimpleEmail();

            // Cấu hình thông tin Email Server
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("duchuy01102001@gmail.com", "matKhau")); //Nhớ nhập đúng với tài khoản thật nhé :))

            // Với gmail cái này là bắt buộc.
            email.setSSLOnConnect(true);

            // Người gửi
            email.setFrom("duchuy01102001@gmail.com", "Công chúa phép thuật");

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
    
    public static void main(String[] args) {
        sendMail sm=new sendMail();
       // sm.sendmail();
       sm.sendmail("muoi07052001@gmail.com ");
        
    }
}
