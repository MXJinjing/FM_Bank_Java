package cn.edu.lzu.fmbank.client.delegate.user;

import cn.edu.lzu.fmbank.client.util.ServerConnector;
import cn.edu.lzu.fmbank.commons.response.ServerResponse;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.IOException;

public class DestroyAccountDelegate {

    private static final TypeReference<ServerResponse<String>> typeReference = new TypeReference<ServerResponse<String>>(){};

    public static ServerResponse<String> destroyAccount() throws IOException {
        return JSONObject.parseObject(ServerConnector.doPost("destroyAccount",new String[]{}),typeReference);
    }
}
