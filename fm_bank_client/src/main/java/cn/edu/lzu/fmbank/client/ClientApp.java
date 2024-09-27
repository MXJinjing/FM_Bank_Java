package cn.edu.lzu.fmbank.client;

import cn.edu.lzu.fmbank.client.util.ServerConnector;
import cn.edu.lzu.fmbank.client.frames.LoginForm;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientApp implements Runnable{

    Socket socket = null;
    BufferedReader bufferedReader = null;
    PrintWriter writer = null;

    public  void run() {
        System.setProperty("sun.java2d.noddraw", "true");//神秘代码

        //Connect to Server
        try {

            //init Server connect
            Socket socket = new Socket("localhost",8080);
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ServerConnector.setWriter(writer);
            ServerConnector.setBufferedReader(bufferedReader);

            //start GUI
            LoginForm.login();

        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "连接服务器地址出错","ERROR",JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e2){
            JOptionPane.showMessageDialog(null, "无法连接至服务器","ERROR",JOptionPane.PLAIN_MESSAGE);
        }


    }

    public static void main(String[] args) {
        Thread mainThread = new Thread(new ClientApp());
        mainThread.start();
    }

}
