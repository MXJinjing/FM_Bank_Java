package cn.edu.lzu.fmbank.server.user.services;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import cn.edu.lzu.fmbank.commons.response.DepositResult;
import cn.edu.lzu.fmbank.commons.response.ResponseEnum;
import cn.edu.lzu.fmbank.commons.response.ServerResponse;

public class DepositService extends BasicService{

    public DepositService(String bid, Connection con) {
        super(bid, con);
    }

    public ServerResponse<DepositResult> deposit(double in) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from bankusers where bid='"+bid+"'");
        double balance = 0.0;
        if(!rs.next()){
            return new ServerResponse<>(ResponseEnum.ERROR,ResponseEnum.ERROR_USER_NOT_EXISTS);
        }
        while (rs.next()){
            balance = rs.getDouble(8);
        }
        if(balance+in > 10000000){
            return new ServerResponse<>(ResponseEnum.ERROR,ResponseEnum.ERROR_BALANCE_TO_LIMIT);
        }
        String sql = ("update bankusers set balance=balance+'"+in+"' where bid='"+bid+"'");
        int result = stmt.executeUpdate(sql);
        return new ServerResponse<>(ResponseEnum.SUCCESS,ResponseEnum.SUCCESS,new DepositResult(this.bid,String.valueOf(in)));
    }
}
