package cn.edu.lzu.fmbank.server;

import cn.edu.lzu.fmbank.server.util.ClientHandler;
import cn.edu.lzu.fmbank.server.util.DataBaseLink;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerApp implements Runnable{
    private static volatile int clientCount = 0;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    private static Connection con;

    static {
        try {
            con = new DataBaseLink().conSQL();
        } catch (SQLException e) {
            System.out.println("Cannot connect to mysql server");
        }
    }

    public  void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server started on port 8080");

            while(true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket.getInetAddress().getHostAddress());
                clientCount++;

                if(clientCount == 1) {
                    threadPool = Executors.newFixedThreadPool(10);
                }

                // 执行线程
                threadPool.submit(new ClientHandler(socket, clientCount, con));

                if(clientCount == 0) {
                    threadPool.shutdown();
                }
                System.out.println(clientCount);
            }
        } catch (IOException e) {
            System.out.println("Cannot use port 8080");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("Cannot connect to mysql server");
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args)  {
        Thread mainThread = new Thread(new ServerApp());
        mainThread.start();
    }


}
