package cn.edu.lzu.fmbank.client.delegate.user;

import cn.edu.lzu.fmbank.client.util.ServerConnector;
import cn.edu.lzu.fmbank.commons.response.ServerResponse;
import cn.edu.lzu.fmbank.commons.response.TransferResult;
import cn.edu.lzu.fmbank.commons.response.WithdrawResult;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.IOException;

public class WithDrawDelegate {
    private static final TypeReference<ServerResponse<WithdrawResult>> typeReference = new TypeReference<ServerResponse<WithdrawResult>>(){};

    public static ServerResponse<WithdrawResult> withdraw(Double out) throws IOException{
        return JSONObject.parseObject(ServerConnector.doPost("withdraw",new String[]{String.valueOf(out)}),typeReference);
    }
}
