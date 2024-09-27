package cn.edu.lzu.fmbank.server.user.services;

import cn.edu.lzu.fmbank.commons.response.ChangeResult;
import cn.edu.lzu.fmbank.commons.response.ResponseEnum;
import cn.edu.lzu.fmbank.commons.response.ServerResponse;
import cn.edu.lzu.fmbank.server.util.CheckUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class ChangeInfoService extends  BasicService{

    private static final String selectAllSql = "select * from bankusers where bid = ?";
    private static final String changeSexSql  =  "update bankusers set sex = ? where bid = ? ";
    private static final String changeTelSql  =  "update bankusers set tel = ? where bid = ? ";
    private static final String changeBirthSql = "update bankusers set birth = ? where bid = ? ";

    public ChangeInfoService(String bid, Connection con) {
        super(bid, con);
    }

    public ServerResponse<ChangeResult> changeSex(String sex) throws SQLException {
        if(!CheckUtil.checkUserExistence(con,bid)){
            return new ServerResponse<>(ResponseEnum.ERROR, ResponseEnum.ERROR_USER_NOT_EXISTS);
        }
        if(!CheckUtil.checkSexValid(sex)){
            return new ServerResponse<>(ResponseEnum.ERROR, ResponseEnum.ERROR_SEX_NOT_VALID);
        }
        PreparedStatement pstmt = con.prepareStatement(changeSexSql);
        pstmt.setString(1,sex);
        pstmt.setString(2,bid);
        pstmt.executeUpdate();
        pstmt.close();
        return new ServerResponse<>(ResponseEnum.SUCCESS, ResponseEnum.SUCCESS, new ChangeResult(bid, "sex", sex));
    }

    public ServerResponse<ChangeResult> changeTel(String tel) throws SQLException {
        if(!CheckUtil.checkUserExistence(con,bid)){
            return new ServerResponse<>(ResponseEnum.ERROR, ResponseEnum.ERROR_USER_NOT_EXISTS);
        }
        if(!CheckUtil.checkTelValid(tel)){
            return new ServerResponse<>(ResponseEnum.ERROR, ResponseEnum.ERROR_TEL_NOT_VALID);
        }
        PreparedStatement pstmt = con.prepareStatement(changeTelSql);
        pstmt.setString(1,tel);
        pstmt.setString(2,bid);
        pstmt.executeUpdate();
        pstmt.close();
        return new ServerResponse<>(ResponseEnum.SUCCESS, ResponseEnum.SUCCESS,new ChangeResult(bid,"tel",tel));
    }

    public ServerResponse<ChangeResult> changeBirth(String birth) throws SQLException {
        if(!CheckUtil.checkUserExistence(con,bid)){
            return new ServerResponse<>(ResponseEnum.ERROR, ResponseEnum.ERROR_USER_NOT_EXISTS);
        }
        if(!CheckUtil.checkDateValid(birth)){
            return new ServerResponse<>(ResponseEnum.ERROR, ResponseEnum.ERROR_DATE_NOT_VALID);
        }
        Statement stmt = con.createStatement();
        PreparedStatement pstmt = con.prepareStatement(changeBirthSql);
        pstmt.setString(1,birth);
        pstmt.setString(2,bid);
        pstmt.executeUpdate();
        pstmt.close();
        return new ServerResponse<>(ResponseEnum.SUCCESS, ResponseEnum.SUCCESS,new ChangeResult(bid,"birth",birth));
    }
}
