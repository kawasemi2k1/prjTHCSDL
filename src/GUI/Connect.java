/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author MANH.TD194616
 */
public class Connect {
    Connection con = null;
    public Connection getConnectDB(){
        try{
            String url = "jdbc:sqlserver://localhost:1433;databaseName=CSDL_Project";
            String user = "sa";
            String password = "123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, user, password);
            if(con != null){
                System.out.println("Connected Susseccful.");
            }
        } catch (Exception ex) {
            System.out.println("Connected fail susseccfully: " + ex.toString());
        }
        return con;
    }
}
