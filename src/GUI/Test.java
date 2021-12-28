/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.sql.*;
/**
 *
 * @author ADMIN
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    static Connect kn = new Connect();
    public static void main(String[] args) {
        Connection cn = kn.getConnectDB();
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select * from sales.staffs";
        try{
            stm = cn.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                System.out.println("staff_id: "+rs.getString(1)+"\tName: "+rs.getString(2)+"\tEmail: "+rs.getString(3)+"\t  password: "+rs.getString(9));
            }
        } catch (Exception e) {
            System.out.println("Loi gi do roi"+e);
        
        }
    }
    
}
