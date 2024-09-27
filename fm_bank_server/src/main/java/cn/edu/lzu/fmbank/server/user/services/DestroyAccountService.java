package cn.edu.lzu.fmbank.server.user.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import cn.edu.lzu.fmbank.commons.response.ResponseEnum;
import cn.edu.lzu.fmbank.commons.response.ServerResponse;

public class DestroyAccountService extends BasicService{


    public DestroyAccountService(String bid, Connection con) {
        super(bid, con);
    }

    public ServerResponse<String> destroyAccount() throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from bankusers where bid='" + bid + "'");
        if(!rs.next()){
            return new ServerResponse<>(ResponseEnum.ERROR, ResponseEnum.ERROR_USER_NOT_EXISTS);
        }
        String sql = ("delete from bankusers where bid='"+bid+"'");
        stmt.executeUpdate(sql);
        stmt.close();
        return new ServerResponse<>(ResponseEnum.SUCCESS, ResponseEnum.SUCCESS,bid);
    }
}
