package cn.edu.lzu.fmbank.client.frames;

import cn.edu.lzu.fmbank.client.delegate.user.LoginCheckDelegate;
import cn.edu.lzu.fmbank.commons.entity.User;
import cn.edu.lzu.fmbank.commons.response.LoginResult;
import cn.edu.lzu.fmbank.client.util.*;
import cn.edu.lzu.fmbank.commons.response.ResponseEnum;
import cn.edu.lzu.fmbank.commons.response.ServerResponse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class LoginForm {

	public static void login() throws IOException {
		// 新建一个JFrame
		JFrame frame = new JFrame("登录页面");
		frame.setSize(400,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(STool.getwX(300),STool.getwY(200));//设置窗口居中

		// 新建一个JPanel
		JPanel panel = new JPanel();
		frame.add(panel);
		panel.setLayout(null);

        //创建提示文字
        JLabel noticelJLabel = new JLabel("请输入用户名和密码登录");
        noticelJLabel.setBounds(60,10,180,25);
        panel.add(noticelJLabel);

        // 创建用户名标签
        JLabel userLabel = new JLabel("用户名:");

        userLabel.setBounds(10,50,80,25);
        panel.add(userLabel);

        // 输入用户名的区域
        JTextField userText = new JTextField(20);
        userText.setBounds(100,50,165,25);
        panel.add(userText);

        // 创建密码标签
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(10,80,80,25);
        panel.add(passwordLabel);

        //输入密码的区域
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,80,165,25);
        panel.add(passwordText);

        //创建登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.setBounds(60, 120, 80, 25);
        panel.add(loginButton);

        //监听登录
        loginButton.addActionListener(new ActionListener(){
            @Override
            //验证密码的环节
            public void actionPerformed(ActionEvent e) {
                String bid = userText.getText();
                String pw = passwordText.getText();

                try {
                    ServerResponse<LoginResult> response = LoginCheckDelegate.login(bid, pw);
                    if(response.getCode().equals(ResponseEnum.SUCCESS)) {
                        if (response.getBody().getLoginType().equals("admin")) {
                            frame.dispose();//关闭登陆页面
                            AdminFrame index = new AdminFrame();
                            index.load();
                            System.out.println("Enter Admin Pages");
                        } else  {
                            noticelJLabel.setText("登陆成功");
                            frame.dispose();//关闭登陆页面
                            UserFrame index = new UserFrame();
                            index.load();//打开主页//
                        }
                    } else {
                        noticelJLabel.setText("登陆失败:用户名或密码错误");
                    }
                } catch (SQLException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //创建取消按钮
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(160, 120, 80, 25);
        panel.add(cancelButton);
        cancelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

		frame.setVisible(true);
	}
}
