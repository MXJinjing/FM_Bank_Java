package cn.edu.lzu.fmbank.server.util;

import java.sql.*;

public class DataBaseLink {

    public Connection conSQL() throws SQLException {
        String url = "jdbc:mysql://localhost/fmbank";
        String user = "root";
        String password = "1234";
        Connection con;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.err.println("Mysql JDBC Driver Not Found");
            return null;
        }

        return con;
    }
}
