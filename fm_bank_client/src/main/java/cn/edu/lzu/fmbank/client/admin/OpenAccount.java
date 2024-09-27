package cn.edu.lzu.fmbank.client.admin;

import cn.edu.lzu.fmbank.server.util.DataBaseLink;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class OpenAccount {
    public static void doit(int bid, String name, char sex, String id, String tel, String birth, String pw) throws SQLException {
        Connection con = new DataBaseLink().conSQL();
        Statement stmt = con.createStatement();

        String sql = ("insert into bankusers (bid,name,password,id,tel,sex,birth,balance) values('" + bid + "','" + name + "','" + pw + "','" + id + "','" + tel + "','" + sex + "','" + birth + "','"+2000+"')");
        int result = stmt.executeUpdate(sql);
        stmt.close();
    }
}
