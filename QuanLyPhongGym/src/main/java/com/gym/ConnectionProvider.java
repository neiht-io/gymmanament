package com.gym;
import java.sql.*;

public class ConnectionProvider {
    public static Connection getCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/quanlyphonggym?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(url, "root", "");
            return con;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
