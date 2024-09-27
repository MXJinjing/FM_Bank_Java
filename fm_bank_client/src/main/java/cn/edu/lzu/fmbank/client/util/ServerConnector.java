package cn.edu.lzu.fmbank.client.util;

import cn.edu.lzu.fmbank.commons.request.BasicRequest;
import cn.edu.lzu.fmbank.commons.response.BasicResult;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnector<D extends BasicResult> {

    private static Socket socket = null;
    private static PrintWriter writer = null;
    private static BufferedReader bufferedReader = null;

    public static String doPost(String methodName, String[] args) throws IOException {
        BasicRequest basicRequest = new BasicRequest(methodName, args);
        String request = JSONObject.toJSONString(basicRequest);
        System.out.println("Client-SendRequest: " + request);
        // 写入服务器，并获取数据
        writer.println(request);
        String response = bufferedReader.readLine();
        System.out.println("Client-GetResponse: "+ response);
        return response;
    }

    public ServerConnector() {
    }

    public static BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public static void setBufferedReader(BufferedReader bufferedReader) {
        ServerConnector.bufferedReader = bufferedReader;
    }

    public static PrintWriter getWriter() {
        return writer;
    }

    public static void setWriter(PrintWriter writer) {
        ServerConnector.writer = writer;
    }

}