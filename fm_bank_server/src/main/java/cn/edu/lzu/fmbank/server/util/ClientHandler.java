package cn.edu.lzu.fmbank.server.util;

import cn.edu.lzu.fmbank.commons.request.BasicRequest;
import cn.edu.lzu.fmbank.commons.response.ServerResponse;
import cn.edu.lzu.fmbank.server.controller.MyController;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private int clientCount;

    public ClientHandler(Socket socket, int clientCount, Connection con) throws SQLException {
        this.socket = socket;
        this.clientCount = clientCount;
    }

    @Override
    public void run() {

        BufferedReader in = null;
        PrintWriter writer = null;
        MyController controller = null;
        try {
            Connection con = new DataBaseLink().conSQL();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
            controller = new MyController("none",con);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        String clientRequest = "";
        while (true) {
            try {
                clientRequest = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("client"+clientCount+": "+clientRequest);
            BasicRequest request = JSONObject.parseObject(clientRequest,BasicRequest.class);
            ServerResponse response = null;
            try {
                response = controller.map(request);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String responseString = JSONObject.toJSONString(response);
            writer.println(responseString);
            System.out.println("sever response:" + responseString);
        }
    }
}