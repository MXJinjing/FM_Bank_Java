import cn.edu.lzu.fmbank.server.ServerApp;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

public class ChangeTest {

    Socket socket;
    BufferedReader bufferedReader;
    PrintWriter writer;

    @Before
    public void login_User() {
        Thread mainThread = new Thread(new ServerApp());
        mainThread.start();

        try {
            socket = new Socket("localhost", 8080);
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer.println("{\"args\":[\"user\",\"user\"],\"methodName\":\"login\"}");
            String text = bufferedReader.readLine();
            System.out.println(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void changeTel_User() throws IOException {
        String request = "{\"args\":[\"13328000000\"],\"methodName\":\"changeTel\"}";
        writer.println(request);
        System.out.println(bufferedReader.readLine());
    }
}
