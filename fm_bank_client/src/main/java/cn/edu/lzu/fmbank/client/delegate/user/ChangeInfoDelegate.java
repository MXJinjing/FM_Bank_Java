package cn.edu.lzu.fmbank.client.delegate.user;

import cn.edu.lzu.fmbank.client.util.ServerConnector;
import cn.edu.lzu.fmbank.commons.response.ChangeResult;
import cn.edu.lzu.fmbank.commons.response.ServerResponse;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.IOException;

public class ChangeInfoDelegate {

    private static final TypeReference<ServerResponse<ChangeResult>> typeReference = new TypeReference<ServerResponse<ChangeResult>>(){};

    public static ServerResponse<ChangeResult> changeSex(String sex) throws IOException {
        return JSONObject.parseObject(ServerConnector.doPost("changeSex",new String[]{sex}),typeReference);
    }

    public static ServerResponse<ChangeResult> changeTel(String tel) throws IOException {
        return JSONObject.parseObject(ServerConnector.doPost("changeTel",new String[]{tel}),typeReference);
    }

    public static ServerResponse<ChangeResult> changeBirth(String birth) throws IOException {
        return JSONObject.parseObject(ServerConnector.doPost("changeBirth",new String[]{birth}),typeReference);
    }
}
