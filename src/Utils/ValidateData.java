/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/**
 *
 * @author ADMIN
 */
public class ValidateData {
    public boolean kiemTraEmail(String email) {
        String dinhDangEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        boolean ktEmail = email.matches(dinhDangEmail);
        return ktEmail;
        // String dinhDangEmail nó giống như là format chuẩn cho email mình nhập vào, nếu sai cái
        // dịnh dạng này thì là lỗi.
        // Hàm matches dùng để xác định xem chuỗi email của mình có khớp với định dạng mình đã quy 
        // định trước hay không. hàm này trả về true , false nên mình khởi tạo 1 biến ktEmail rồi gán
        // cho nó. false thì báo lỗi.
    }
    
    public boolean kiemTraTen(String ten){
        // Đây là hàm kiểm tra xem tên có số hay không. Ko chứa thì trả về True, có chứa thì trả về false
        // ví dụ "Nguyễn Thị Thúy" trả về True, "Nguyễn 1 Thúy" trả về False
        for (int i = 0; i < 10; i++) {
            if(ten.contains(i+"")){
                return false;
            }
        }
        return true;
    }
    public String kiemTraSDT(String sdt){
        sdt = sdt.trim();
        sdt = sdt.replace(".", "");
        sdt = sdt.replace(",", "");
        sdt = sdt.replace(" ", "");
        sdt = sdt.replaceAll("\\s+", "");
        try{
            if(!sdt.startsWith(0+"")){
                return "Số điện thoại phải bắt đầu bằng số 0";
                
            } else if(sdt.length() != 10 && sdt.length() != 11){
                return "Số điện thoại phải có 10 hoặc 11 chữ số";
            }
            long sdt_long = Long.parseLong(sdt);
        } catch(NumberFormatException ex){
            return "Số điện thoại không được chứa chữ cái";
        } catch(Exception ex){
            return "Sai dinh dang" + ex.getMessage();
        }
        return "";
    }
    
    public String md5(String str) {
        String result = "";
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            BigInteger bigInteger = new BigInteger(1, digest.digest());
            result = bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
}
    public String ChuanHoaChuoi (String s){
        // Đây là hàm chuẩn hóa chuối. Ví dụ: " nGUYỄN     THỊ thúy   " -> "Nguyễn Thị Thúy"
        // chuỗi "    " -> "" . Chuẩn hóa tên, email nhập vào, xong mới so xem nó bằng rỗng ko để đưa ra thông báo nhập vào ở thêm, sửa.
        s = s.trim();
        String chuanHoa = "";
        String a = "";
        String arr[] = s.split(" ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
            if (!arr[i].isEmpty()) {
                a = Character.toString(arr[i].charAt(0)).toUpperCase().concat(arr[i].substring(1).toLowerCase());
                chuanHoa = chuanHoa.concat(a);
                chuanHoa = chuanHoa.concat(" ");
            }
        }
        chuanHoa = chuanHoa.trim();
        if(chuanHoa.equals(" ")) chuanHoa = "";
        System.out.println("Chuỗi sau khi chuẩn hóa: " + chuanHoa);
        return chuanHoa;
        
    }
    
    public String DangTienTe (String number){
        // Đây là hàm định dạng tiền tệ. Ví dụ: 123456789.25 -> 123,456,789.25
        int x = number.length();
        StringBuilder sb = new StringBuilder(number);
        if(number.contains(".")){
            while (x > 6) {
                sb.insert(x - 6, ",");
                x = x - 3;
            }
        
        } else {
            while (x > 3) {
                sb.insert(x - 3, ",");
                x = x - 3;
            }
        }
        System.out.println("Dạng tiền tệ: " + sb);
        return sb.toString();
    }
    
    public String ReverseDangTienTe (String number) {
        return number.replace(",", "");
    }
    
    public boolean containsDigit(String s) {
        String regex = "(.)*(\\d)(.)*";      
        Pattern pattern = Pattern.compile(regex);
        String msg = s;
        boolean containsNumber = pattern.matcher(msg).matches();
        return containsNumber;
    }     
}
