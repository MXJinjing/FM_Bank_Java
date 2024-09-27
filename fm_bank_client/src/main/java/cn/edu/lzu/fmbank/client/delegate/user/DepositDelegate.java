package cn.edu.lzu.fmbank.client.delegate.user;

import cn.edu.lzu.fmbank.client.util.ServerConnector;
import cn.edu.lzu.fmbank.commons.response.DepositResult;
import cn.edu.lzu.fmbank.commons.response.ServerResponse;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.IOException;

public class DepositDelegate {

    private static final TypeReference<ServerResponse<DepositResult>> typeReference = new TypeReference<ServerResponse<DepositResult>>(){};

    public static ServerResponse<DepositResult> deposit(Double in) throws IOException {
        return JSONObject.parseObject(ServerConnector.doPost("deposit",new String[]{String.valueOf(in)}),typeReference);
    }

}
