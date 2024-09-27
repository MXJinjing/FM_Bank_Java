package cn.edu.lzu.fmbank.server.user.services;

import cn.edu.lzu.fmbank.commons.response.ResponseEnum;
import cn.edu.lzu.fmbank.commons.response.ServerResponse;
import cn.edu.lzu.fmbank.commons.response.TransferResult;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransferService extends BasicService {

    public TransferService(String bid, Connection con) {
        super(bid, con);
    }

    public ServerResponse<TransferResult> transfer(String transferToBid, double amount) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from bankusers where bid='" + transferToBid + "'");
        String id = "";
        while(resultSet.next()) {
            id = resultSet.getString(1);}
        if(id.equals("")) {
            return new ServerResponse<>(ResponseEnum.ERROR,ResponseEnum.ERROR_USER_NOT_EXISTS);
        }

        ResultSet resultSet1 = stmt.executeQuery("select * from bankusers where bid='" + bid + "'");
        double balance = 0.0;
        while (resultSet1.next()) {
            balance = resultSet1.getDouble(8);
        }
        if (balance < amount) {
            return new ServerResponse<>(ResponseEnum.ERROR,ResponseEnum.ERROR_BALANCE_NOT_ENOUGH);
        }
        else {
            String sql_out = ("update bankusers set balance=balance-'" + amount + "' where bid='" + bid + "'");
            stmt.executeUpdate(sql_out);
            String sql_in = ("update bankusers set balance=balance+'" + amount + "' where bid='" + transferToBid + "'");
            stmt.executeUpdate(sql_in);
            return new ServerResponse<>(ResponseEnum.SUCCESS, ResponseEnum.SUCCESS, new TransferResult(bid,transferToBid,String.valueOf(amount)));
        }
    }
}
