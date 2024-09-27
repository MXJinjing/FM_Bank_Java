package cn.edu.lzu.fmbank.server.user.services;


import java.sql.*;
import cn.edu.lzu.fmbank.commons.entity.User;
import cn.edu.lzu.fmbank.commons.response.LoginResult;
import cn.edu.lzu.fmbank.commons.response.ResponseEnum;
import cn.edu.lzu.fmbank.commons.response.ServerResponse;

public class LoginCheckService extends BasicService
{
    public LoginCheckService(String bid, Connection con) {
        super(bid, con);
    }

    private final static String sql = "select * from bankusers where username = ? and password = ? ";

    public ServerResponse<LoginResult> login(String username, String pw)  {

        try {
            PreparedStatement pstmt= con.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,pw);
            ResultSet rs = pstmt.executeQuery();


            if(rs.next()){
                User user = new User();
                user.setBid(rs.getString("bid"));
                user.setUsername(rs.getString("username"));
                user.setId(rs.getString("id"));
                user.setBalance(rs.getDouble("balance"));
                user.setBirth(rs.getDate("birth"));
                user.setSex(rs.getString("sex"));
                user.setTel(rs.getString("tel"));
                if(user.getUsername().equals("admin")){
                    return new ServerResponse<>(ResponseEnum.SUCCESS,ResponseEnum.SUCCESS,new LoginResult("admin",user));
                }
                return new ServerResponse<>(ResponseEnum.SUCCESS,ResponseEnum.SUCCESS,new LoginResult("user",user));
            }
            return new ServerResponse<>(ResponseEnum.ERROR, ResponseEnum.ERROR_USER_PW_NOT_MATCH);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ServerResponse<>();
    }
}
