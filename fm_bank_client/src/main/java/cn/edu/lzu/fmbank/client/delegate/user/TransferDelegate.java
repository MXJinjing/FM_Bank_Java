package cn.edu.lzu.fmbank.client.delegate.user;

import cn.edu.lzu.fmbank.client.util.ServerConnector;
import cn.edu.lzu.fmbank.commons.response.ServerResponse;
import cn.edu.lzu.fmbank.commons.response.TransferResult;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;


import java.io.IOException;

public class TransferDelegate {
    private static final TypeReference<ServerResponse<TransferResult>> typeReference = new TypeReference<ServerResponse<TransferResult>>(){};

    public static ServerResponse<TransferResult> transfer(String transferToBid, Double amount) throws IOException {
        return JSONObject.parseObject(ServerConnector.doPost("transfer",new String[]{transferToBid,String.valueOf(amount)}),typeReference);
    }
}
