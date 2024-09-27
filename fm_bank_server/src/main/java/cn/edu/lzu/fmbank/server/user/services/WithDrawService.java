package cn.edu.lzu.fmbank.server.user.services;


import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import cn.edu.lzu.fmbank.commons.response.ResponseEnum;
import cn.edu.lzu.fmbank.commons.response.ServerResponse;
import cn.edu.lzu.fmbank.commons.response.WithdrawResult;


public class WithDrawService extends BasicService{

    public WithDrawService(String bid, Connection con) {
        super(bid, con);
    }

    public ServerResponse<WithdrawResult> withdraw(double out) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from bankusers where bid='" + bid + "'");
        double balance = 0.0;
        while (rs.next()){
            balance = rs.getDouble(8);
        }
        if(balance < out){
            //JOptionPane.showMessageDialog(null, "余额不足！取款失败", "确认", JOptionPane.PLAIN_MESSAGE);
            return new ServerResponse<>(ResponseEnum.ERROR,ResponseEnum.ERROR_BALANCE_NOT_ENOUGH);
        }
        String sql = ("update bankusers set balance=balance-'"+out+"' where bid='"+bid+"'");
        int result = stmt.executeUpdate(sql);
        return new ServerResponse<>(ResponseEnum.SUCCESS,ResponseEnum.SUCCESS,new WithdrawResult(bid,String.valueOf(out)));
    }
}