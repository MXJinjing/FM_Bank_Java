package cn.edu.lzu.fmbank.server.user.services;

import java.net.ServerSocket;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import cn.edu.lzu.fmbank.commons.entity.User;
import cn.edu.lzu.fmbank.commons.response.ResponseEnum;
import cn.edu.lzu.fmbank.commons.response.ServerResponse;

public class QueryInfoService extends BasicService{

    public QueryInfoService(String bid, Connection con) {
        super(bid, con);
    }

    public ServerResponse<User> queryUser() throws  SQLException{
        User user = new User();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from bankusers where bid='"+bid+"'");
        if(!rs.next()){
            return new ServerResponse<>(ResponseEnum.ERROR, ResponseEnum.ERROR_USER_NOT_EXISTS);
        }
        user.setBid(rs.getString("bid"));
        user.setUsername(rs.getString("username"));
        user.setId(rs.getString("id"));
        user.setSex(rs.getString("sex"));
        user.setTel(rs.getString("tel"));
        user.setBirth(rs.getDate("birth"));
        user.setBalance(rs.getDouble("balance"));
        return new ServerResponse<>(ResponseEnum.SUCCESS, ResponseEnum.SUCCESS, user);
    }

    public ServerResponse<String> queryBalance() throws SQLException {
        double balance = 0.0;
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from bankusers where bid='"+bid+"'");
        if(!rs.next()){
            return new ServerResponse<>(ResponseEnum.ERROR, ResponseEnum.ERROR_USER_NOT_EXISTS);
        }
        balance = rs.getDouble("balance");
        return new ServerResponse<>(ResponseEnum.SUCCESS, ResponseEnum.SUCCESS, String.valueOf(balance));
    }

    public ServerResponse<String> querySex() throws SQLException {
        String sex = "";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from bankusers where bid='"+bid+"'");
        if(!rs.next()){
            return new ServerResponse<>(ResponseEnum.ERROR, ResponseEnum.ERROR_USER_NOT_EXISTS);
        }
        sex = rs.getString("sex");
        return new ServerResponse<>(ResponseEnum.SUCCESS, ResponseEnum.SUCCESS, sex);
    }

    public ServerResponse<String> queryId() throws SQLException {
        String id = "";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from bankusers where bid='"+bid+"'");
        if(!rs.next()){
            return new ServerResponse<>(ResponseEnum.ERROR, ResponseEnum.ERROR_USER_NOT_EXISTS);
        }
        id = rs.getString("id");
        return new ServerResponse<>(ResponseEnum.SUCCESS, ResponseEnum.SUCCESS, id);
    }

    public ServerResponse<String> queryName() throws SQLException {
        String name = "";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from bankusers where bid='"+bid+"'");
        if(!rs.next()){
            return new ServerResponse<>(ResponseEnum.ERROR, ResponseEnum.ERROR_USER_NOT_EXISTS);
        }
        name = rs.getString("username");
        return new ServerResponse<>(ResponseEnum.SUCCESS, ResponseEnum.SUCCESS, name);
    }

    public ServerResponse<String> queryTel() throws SQLException {
        String tel = "";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from bankusers where bid='"+bid+"'");
        if(!rs.next()){
            return new ServerResponse<>(ResponseEnum.ERROR, ResponseEnum.ERROR_USER_NOT_EXISTS);
        }
        tel = rs.getString("tel");
        return new ServerResponse<>(ResponseEnum.SUCCESS, ResponseEnum.SUCCESS, tel);
    }

    public ServerResponse<String> queryBirth() throws SQLException {
        Date birth = null;
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from bankusers where bid='"+bid+"'");
        if(!rs.next()){
            return new ServerResponse<>(ResponseEnum.ERROR, ResponseEnum.ERROR_USER_NOT_EXISTS);
        }
        birth = rs.getDate("birth");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return new ServerResponse<>(ResponseEnum.SUCCESS, ResponseEnum.SUCCESS, simpleDateFormat.format(birth));
    }
}
