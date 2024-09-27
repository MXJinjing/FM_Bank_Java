package cn.edu.lzu.fmbank.client.delegate.user;

import cn.edu.lzu.fmbank.client.util.ServerConnector;
import cn.edu.lzu.fmbank.commons.response.DepositResult;
import cn.edu.lzu.fmbank.commons.response.LoginResult;
import cn.edu.lzu.fmbank.commons.response.ServerResponse;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.IOException;
import java.sql.SQLException;

public class LoginCheckDelegate {

    private static final TypeReference<ServerResponse<LoginResult>> typeReference = new TypeReference<ServerResponse<LoginResult>>(){};

    public static ServerResponse<LoginResult> login(String username, String pw) throws SQLException, IOException {
        return JSONObject.parseObject(ServerConnector.doPost("login",new String[]{username,pw}),typeReference);
    }
}
