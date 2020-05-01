package com.laptrinhjavaweb.DB;
import java.sql.*;

public class DBConnection {   
    public static Connection CreateConnection(){
        Connection conn = null;        
        String DB_URL = "jdbc:mysql://localhost:3306/review_anime";
        String USER_NAME = "root";
        String PASSWORD = "123456";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
            System.out.println("connect sucessfully!");
        }catch (Exception e){
            System.out.println("connect failure");
            e.printStackTrace();
        }
        return conn;
    }
}
