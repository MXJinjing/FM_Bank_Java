package cn.edu.lzu.fmbank.client.delegate.user;

import cn.edu.lzu.fmbank.client.util.ServerConnector;
import cn.edu.lzu.fmbank.commons.entity.User;
import cn.edu.lzu.fmbank.commons.response.LoginResult;
import cn.edu.lzu.fmbank.commons.response.ServerResponse;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.IOException;

public class QueryInfoDelegate {
    private static final TypeReference<ServerResponse<String>> typeReference = new TypeReference<ServerResponse<String>>(){};
    private static final TypeReference<ServerResponse<User>> typeReference2 = new TypeReference<ServerResponse<User>>(){};

    public static ServerResponse<String> queryBalance () throws IOException {
        return JSONObject.parseObject(ServerConnector.doPost("queryBalance",new String[]{}),typeReference);
    }

    public static ServerResponse<String> querySex() throws IOException {
        return JSONObject.parseObject(ServerConnector.doPost("querySex",new String[]{}),typeReference);
    }

    public static ServerResponse<String> queryId() throws IOException {
        return JSONObject.parseObject(ServerConnector.doPost("queryId",new String[]{}),typeReference);
    }

    public static ServerResponse<String> queryName() throws IOException {
        return JSONObject.parseObject(ServerConnector.doPost("queryName",new String[]{}),typeReference);
    }

    public static ServerResponse<String> queryTel() throws IOException {
        return JSONObject.parseObject(ServerConnector.doPost("queryTel",new String[]{}),typeReference);
    }

    public static ServerResponse<String> queryBirth() throws IOException {
        return JSONObject.parseObject(ServerConnector.doPost("queryBirth",new String[]{}),typeReference);
    }

    public static ServerResponse<User> queryUser () throws IOException {
        return JSONObject.parseObject(ServerConnector.doPost("queryUser",new String[]{}),typeReference2);
    }

}
