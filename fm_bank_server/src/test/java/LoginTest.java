import cn.edu.lzu.fmbank.commons.response.LoginResult;
import cn.edu.lzu.fmbank.commons.response.ServerResponse;
import cn.edu.lzu.fmbank.server.ServerApp;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class LoginTest {


    @Test
    public void loginTest_User() {
        Thread mainThread = new Thread(new ServerApp());
        mainThread.start();
        String text = "";
        try {
            Socket socket = new Socket("localhost", 8080);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer.println("{\"args\":[\"user\",\"user\"],\"methodName\":\"login\"}");
            text = bufferedReader.readLine();
            System.out.println(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ServerResponse<LoginResult> loginResultServerResponse = JSONObject.parseObject(text, new TypeReference<ServerResponse<LoginResult>>() {
        });
        Assert.assertEquals("user",loginResultServerResponse.getBody().getUser().getUsername());

    }

    @Test
    public void connectTest_Local() {
        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(8080);
                    Socket socket = serverSocket.accept();
                    PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    while(true){
                        String in = bufferedReader.readLine();
                        System.out.println("Server In: "+in);
                        writer.println(in);
                        System.out.println("Server Out");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        mainThread.start();

        try {
            Socket socket = new Socket("localhost", 8080);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer.println("{\"args\":[\"admin\",\"123456\"],\"methodName\":\"login\"}");
            String text = bufferedReader.readLine();
            System.out.println(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Socket socket = new Socket("localhost", 8080);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer.println("{\"args\":[\"admin\",\"123456\"],\"methodName\":\"login\"}");
            String text = bufferedReader.readLine();
            System.out.println(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
