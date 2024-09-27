package cn.edu.lzu.fmbank.client.frames;

import cn.edu.lzu.fmbank.client.delegate.user.*;
import cn.edu.lzu.fmbank.client.util.STool;
import cn.edu.lzu.fmbank.commons.entity.User;
import cn.edu.lzu.fmbank.commons.response.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class UserFrame extends JFrame{



	/**
	 * 	用户操作界面的窗口
	 */
	private static final long serialVersionUID = 2630453835390812777L;

	private Font font = new Font("仿宋", Font.PLAIN, 30);
	private Font font2 = new Font("仿宋", Font.PLAIN, 18);

	private JPanel indexPanel = new JPanel();
	private JPanel showBalancePanel = new JPanel();
	private JPanel withdrawPanel = new JPanel();
	private JPanel withdrawConfirmPanel = new JPanel();
	private JPanel depositPanel = new JPanel();
	private JPanel depositConfirmPanel = new JPanel();
	private JPanel transferPanel = new JPanel();
	private JPanel transferConfirmPanel = new JPanel();
	private JPanel informationPanel = new JPanel();

	private JLabel welcome = new JLabel();
	private JLabel balanceLabel = new JLabel();
	private JLabel withdrawLabel = new JLabel("请输入想要取出的金额（元）");
	private JLabel withdrawNotice = new JLabel();
	private JLabel withdrawConfirmLabel = new JLabel();
	private JLabel depositLabel = new JLabel("请确定您存入的金额（元）");
	private JLabel depositNotice = new JLabel();
	private JLabel depositConfirmLabel = new JLabel();
	private JLabel transferLabel = new JLabel("请输入想要转账的目标账户和金额（元）");
	private JLabel transferNotice = new JLabel();
	private JLabel transferTarget = new JLabel("转账目标");
	private JLabel transferAmmount = new JLabel("转账金额");
	private JLabel transferConfirmLabel = new JLabel();
	private JLabel transferConfirmLabel2 = new JLabel();
	private JLabel cardIDLabel = new JLabel("学号");
	private JLabel userNameLabel= new JLabel("用户名");
	private JLabel userIDLabel = new JLabel("用户账号ID");
	private JLabel genderLabel = new JLabel("性别");
	private JLabel phoneLabel = new JLabel("手机号");
	private JLabel birthdayLabel = new JLabel("出生日期");
	private JLabel informationNotice = new JLabel();

	
	private JButton button1 = new JButton("查询余额");
	private JButton button2 = new JButton("取钱");
	private JButton button3 = new JButton("存钱");
	private JButton button4 = new JButton("转账");
	private JButton button5 = new JButton("个人信息");
	private JButton button6 = new JButton("删除账户");
	
	private JButton backButton = new JButton("返回");
	private JButton backButtonBelow = new JButton("返回菜单");
	private JButton withdrawButton = new JButton("确认取款");
	private JButton depositButton = new JButton("确认存款");
	private JButton transferButton = new JButton("确认转账");
	private JButton edit1 = new JButton("改");
	private JButton edit2 = new JButton("改");
	private JButton edit3 = new JButton("改");
	private JButton save1 = new JButton("保存");
	private JButton save2 = new JButton("保存");
	private JButton save3 = new JButton("保存");
	
	private JTextField withdrawText = new JTextField(20);
	private JTextField depositText = new JTextField(20);
	private JTextField transferAmmountText = new JTextField(20);
	private JTextField transferTargetText = new JTextField(20);
	private JTextField cardIDText = new JTextField(12);
	private JTextField userNameText = new JTextField(20);
	private JTextField userIDText = new JTextField(20);
	private JTextField phoneText = new JTextField(11);
	private JTextField genderText = new JTextField(1);
	private JTextField birthdayText = new JTextField(10);

	DecimalFormat df = new DecimalFormat("0.00");

	//构建函数
	public UserFrame() throws IOException, SQLException {
		super("用户操作系统");
//		setIconImage(ImageIO.read(new File("icon.jpg")));
		setSize(800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(STool.getwX(800), STool.getwY(500));//设置窗口居中

		addListeners();
		setVisible(true);

		//初始化user
	}

	public static void main(String[] args) throws IOException, SQLException {
		System.setProperty("sun.java2d.noddraw", "true");//神秘代码
		UserFrame u = new UserFrame();
		u.load();
	}
	
	//进入用户主界面
	public void load() {
		try{
			System.out.println("正在加载用户主界面");
			index();
		}catch (Exception e) {
			System.out.println("界面加载异常:"+e);
		}
	}

	

	
	//显示主菜单的功能
	private void index() throws SQLException, IOException {
		add(indexPanel);
		indexPanel.setLayout(null);

		// 欢迎语句位置
		welcome.setBounds(0, 150, 800, 40);
		// 设置居中
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		// 设置字体
		welcome.setFont(font);
		indexPanel.add(welcome);
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎用户 ");
		String name = QueryInfoDelegate.queryName().getBody();
		sb.append(name);
		welcome.setText(sb.toString());

		// 创建查询余额按钮
		button1.setBounds(120, 300, 100, 30);
		indexPanel.add(button1);

		// 创建取钱按钮
		button2.setBounds(240, 300, 100, 30);
		indexPanel.add(button2);

		// 创建存钱按钮
		button3.setBounds(360, 300, 100, 30);
		indexPanel.add(button3);

		// 创建转账按钮
		button4.setBounds(480, 300, 100, 30);
		indexPanel.add(button4);

		// 创建更改账户信息按钮
		button5.setBounds(600, 300, 100, 30);
		indexPanel.add(button5);
		
		//注销用户按钮
		button6.setBounds(360,350,100,30);
		indexPanel.add(button6);
	}

	// 显示余额的界面
	private void showBalance() throws SQLException, IOException {
		add(showBalancePanel);
		showBalancePanel.setLayout(null);

		// 余额显示区域
		balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		balanceLabel.setBounds(0, 150, 800, 40);
		balanceLabel.setFont(font);
		StringBuffer sb = null;
		try {
			sb = new StringBuffer();
			sb.append("账户余额: ");
			double balance = Double.parseDouble(QueryInfoDelegate.queryBalance().getBody());
			sb.append(balance);
			sb.append("元");
		} catch(NullPointerException ex2){
			JOptionPane.showMessageDialog(null, "连接丢失","ERROR",JOptionPane.PLAIN_MESSAGE);
		}
		balanceLabel.setText(sb.toString());// 显示余额
		showBalancePanel.add(balanceLabel);

		// 底下的返回按钮
		backButtonBelow.setBounds(350, 320, 100, 50);
		showBalancePanel.add(backButtonBelow);

	}

	//显示取款的界面
	private void withdraw() throws SQLException, IOException {
		add(withdrawPanel);
		withdrawPanel.setLayout(null);

		// 显示标题文本
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("请输入想要取出的金额（元） 账户余额：");
			double balance = Double.parseDouble(QueryInfoDelegate.queryBalance().getBody());
			sb.append(balance);
			sb.append("元");
			withdrawLabel.setText(sb.toString());
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "连接丢失","ERROR",JOptionPane.PLAIN_MESSAGE);
			// 返回按钮
			backButton.setBounds(40, 20, 80, 40);
			withdrawPanel.add(backButton);
			return;
		}
		withdrawLabel.setBounds(50, 120, 700, 30);
		withdrawLabel.setHorizontalAlignment(SwingConstants.CENTER);
		withdrawLabel.setFont(font2);
		withdrawPanel.add(withdrawLabel);

		//输入取出存款数目的区域
		withdrawText.setText("");//清除之前输入的文本
		withdrawText.setBounds(200, 180, 400, 50);
		withdrawText.setHorizontalAlignment(SwingConstants.CENTER);
		withdrawText.setFont(font);
		withdrawPanel.add(withdrawText);

		//提示文本
		withdrawNotice.setBounds(200,250,400,30);
		withdrawNotice.setText("");
		withdrawNotice.setHorizontalAlignment(SwingConstants.CENTER);
		withdrawNotice.setFont(font2);
		withdrawPanel.add(withdrawNotice);

		// 返回按钮
		backButton.setBounds(40, 20, 80, 40);
		withdrawPanel.add(backButton);

		// 确认取款按钮
		withdrawButton.setBounds(350, 320, 100, 50);
		withdrawButton.setEnabled(false);
		withdrawPanel.add(withdrawButton);
	}

	//显示取款完成后的确认界面
	private void withdrawConfirm(double wb) throws SQLException, IOException {
		add(withdrawConfirmPanel);
		withdrawConfirmPanel.setLayout(null);

		//确认后显示文字
		withdrawConfirmLabel.setBounds(0,150,800,30);
		withdrawConfirmLabel.setFont(font);
		withdrawConfirmLabel.setHorizontalAlignment(SwingConstants.CENTER);

		StringBuffer ct = new StringBuffer();
		ct.append("已成功取走");
		ct.append(df.format(wb));
		ct.append("元，账户余额");
		double balance = Double.parseDouble(QueryInfoDelegate.queryBalance().getBody());
		ct.append(balance);
		ct.append("元");

		withdrawConfirmLabel.setText(ct.toString());
		withdrawConfirmPanel.add(withdrawConfirmLabel);

		// 底下的返回按钮
		backButtonBelow.setBounds(350, 320, 100, 50);
		withdrawConfirmPanel.add(backButtonBelow);
	}

	//显示存款的界面
	private void deposit() {
		add(depositPanel);
		depositPanel.setLayout(null);

		// 显示标题文本
		depositLabel.setBounds(50, 120, 700, 30);
		depositLabel.setHorizontalAlignment(SwingConstants.CENTER);
		depositLabel.setFont(font2);
		depositPanel.add(depositLabel);

		//输入取出存款数目的区域
		depositText.setText("");//清除之前输入的文本
		depositText.setBounds(200, 180, 400, 50);
		depositText.setHorizontalAlignment(SwingConstants.CENTER);
		depositText.setFont(font);
		depositPanel.add(depositText);

		//提示文本
		depositNotice.setBounds(200,250,400,30);
		depositNotice.setText("");
		depositNotice.setHorizontalAlignment(SwingConstants.CENTER);
		depositNotice.setFont(font2);
		depositPanel.add(depositNotice);

		// 返回按钮
		backButton.setBounds(40, 20, 80, 40);
		depositPanel.add(backButton);

		// 确认存款按钮
		depositButton.setBounds(350, 320, 100, 50);
		depositButton.setEnabled(false);
		depositPanel.add(depositButton);
	}

	//显示存款完成后的确认界面
	private void depositConfirm(double db) throws SQLException, IOException {
		add(depositConfirmPanel);
		depositConfirmPanel.setLayout(null);

		//确认后显示文字
		depositConfirmLabel.setBounds(0,150,800,30);
		depositConfirmLabel.setFont(font);
		depositConfirmLabel.setHorizontalAlignment(SwingConstants.CENTER);


		try{
			double balance = Double.parseDouble(QueryInfoDelegate.queryBalance().getBody());
			StringBuffer ct = new StringBuffer();
			ct.append("已成功存入");
			ct.append(df.format(db));
			ct.append("元，账户余额");
			ct.append(balance);
			ct.append("元");
			depositConfirmLabel.setText(ct.toString());
		}catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "连接丢失","ERROR",JOptionPane.PLAIN_MESSAGE);
		}



		depositConfirmPanel.add(depositConfirmLabel);

		// 底下的返回按钮
		backButtonBelow.setBounds(350, 320, 100, 50);
		depositConfirmPanel.add(backButtonBelow);
	}

	//转账界面
	private void transfer() {
		add(transferPanel);
		transferPanel.setLayout(null);

		// 显示标题文本
		transferLabel.setBounds(0,80,800,30);
		transferLabel.setHorizontalAlignment(SwingConstants.CENTER);
		transferLabel.setFont(font2);
		transferPanel.add(transferLabel);

		// 显示输入提示和框
		transferTarget.setBounds(200,140,100,30);
		transferTarget.setFont(font2);
		transferPanel.add(transferTarget);

		transferTargetText.setBounds(300,140,300,30);
		transferTargetText.setFont(font2);
		transferTargetText.setHorizontalAlignment(SwingConstants.CENTER);
		transferTargetText.setText("");
		transferPanel.add(transferTargetText);

		transferAmmount.setBounds(200,210,100,30);
		transferAmmount.setFont(font2);
		transferPanel.add(transferAmmount);

		transferAmmountText.setBounds(300,200,300,50);
		transferAmmountText.setFont(font);
		transferAmmountText.setHorizontalAlignment(SwingConstants.CENTER);
		transferAmmountText.setText("");
		transferPanel.add(transferAmmountText);

		transferNotice.setBounds(0,270,800,30);
		transferNotice.setText("");
		transferNotice.setFont(font2);
		transferNotice.setHorizontalAlignment(SwingConstants.CENTER);
		transferPanel.add(transferNotice);

		// 确认转账按钮
		transferButton.setBounds(350, 320, 100, 50);
		transferButton.setEnabled(false);
		transferPanel.add(transferButton);

		// 返回按钮
		backButton.setBounds(40,20,80,40);
		transferPanel.add(backButton);
	}

	//转账确认后的界面
	private void transferConfirm(double ammount, String target) {
		add(transferConfirmPanel);
		transferConfirmPanel.setLayout(null);

		//确认后显示文字
		transferConfirmLabel.setBounds(0,150,800,30);
		transferConfirmLabel.setFont(font);
		transferConfirmLabel.setHorizontalAlignment(SwingConstants.CENTER);

		transferConfirmLabel2.setBounds(0,200,800,30);
		transferConfirmLabel2.setFont(font);
		transferConfirmLabel2.setHorizontalAlignment(SwingConstants.CENTER);

		StringBuffer ct = new StringBuffer();
		StringBuffer ct2 = new StringBuffer();
		ct.append("已成功向账户 ");
		ct.append(target);
		ct.append(" 转账");

		ct2.append(df.format(ammount));
		ct2.append("元");

		transferConfirmLabel.setText(ct.toString());
		transferConfirmPanel.add(transferConfirmLabel);

		transferConfirmLabel2.setText(ct2.toString());
		transferConfirmPanel.add(transferConfirmLabel2);

		// 底下的返回按钮
		backButtonBelow.setBounds(350, 320, 100, 50);
		transferConfirmPanel.add(backButtonBelow);
	}

	//显示信息
	private void information() throws SQLException, IOException {
		add(informationPanel);
		informationPanel.setLayout(null);
		User user = QueryInfoDelegate.queryUser().getBody();

		// 返回按钮
		backButton.setBounds(40,20,80,40);
		informationPanel.add(backButton);

		//用户ID
		userIDLabel.setBounds(180,100,100,30);
		userIDLabel.setFont(font2);
		informationPanel.add(userIDLabel);

		userIDText.setText(user.getBid());

		userIDText.setBounds(300,100,300,30);
		userIDText.setEditable(false);
		userIDText.setHorizontalAlignment(SwingConstants.CENTER);
		userIDText.setFont(font2);
		informationPanel.add(userIDText);

		//用户名
		userNameLabel.setBounds(180,150,100,30);
		userNameLabel.setFont(font2);
		informationPanel.add(userNameLabel);

		userNameText.setBounds(300,150,300,30);
		userNameText.setEditable(false);

		String name = user.getUsername();

		userNameText.setText(name);
		userNameText.setHorizontalAlignment(SwingConstants.CENTER);
		userNameText.setFont(font2);
		informationPanel.add(userNameText);

		//
		cardIDLabel.setBounds(180,200,100,30);
		cardIDLabel.setFont(font2);
		informationPanel.add(cardIDLabel);

		cardIDText.setBounds(300,200,300,30);
		cardIDText.setEditable(false);

		String id = user.getId();

		cardIDText.setText(id);
		cardIDText.setHorizontalAlignment(SwingConstants.CENTER);
		cardIDText.setFont(font2);
		informationPanel.add(cardIDText);

		//性别
		genderLabel.setBounds(180,250,100,30);
		genderLabel.setFont(font2);
		informationPanel.add(genderLabel);

		genderText.setBounds(300,250,300,30);
		genderText.setEditable(false);

		String sex = user.getSex();

		genderText.setText(sex);
		genderText.setHorizontalAlignment(SwingConstants.CENTER);
		genderText.setFont(font2);
		informationPanel.add(genderText);

		//可更改
		edit1.setBounds(630,250,50,30);
		edit1.setEnabled(true);
		informationPanel.add(edit1);

		save1.setBounds(690,250,60,30);
		save1.setVisible(false);
		save1.setEnabled(false);
		informationPanel.add(save1);

		//电话号码
		phoneLabel.setBounds(180,300,100,30);
		phoneLabel.setFont(font2);
		informationPanel.add(phoneLabel);

		phoneText.setBounds(300,300,300,30);
		phoneText.setEditable(false);

		String tel = user.getTel();

		phoneText.setText(tel);
		phoneText.setHorizontalAlignment(SwingConstants.CENTER);
		phoneText.setFont(font2);
		informationPanel.add(phoneText);

		//可更改
		edit2.setBounds(630,300,50,30);
		edit2.setEnabled(true);
		informationPanel.add(edit2);

		save2.setBounds(690,300,60,30);
		save2.setVisible(false);
		save2.setEnabled(false);
		informationPanel.add(save2);

		//出生日期
		birthdayLabel.setBounds(180,350,100,30);
		birthdayLabel.setFont(font2);
		informationPanel.add(birthdayLabel);

		birthdayText.setBounds(300,350,300,30);
		birthdayText.setEditable(false);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String birth = simpleDateFormat.format(user.getBirth());

		birthdayText.setText(birth);
		birthdayText.setHorizontalAlignment(SwingConstants.CENTER);
		birthdayText.setFont(font2);
		informationPanel.add(birthdayText);

		//可更改
		edit3.setBounds(630,350,50,30);
		edit3.setEnabled(true);
		informationPanel.add(edit3);

		save3.setBounds(690,350,60,30);
		save3.setVisible(false);
		save3.setEnabled(false);
		informationPanel.add(save3);

		//提示文本
		informationNotice.setBounds(0,50,800,30);
		informationNotice.setFont(font2);
		informationNotice.setText("");
		informationNotice.setHorizontalAlignment(SwingConstants.CENTER);
		informationPanel.add(informationNotice);
	}

	private void cancelAccount() throws SQLException, IOException {
		JDialog cancelDialog = new JDialog(this,true);
		
		cancelDialog.setLayout(null);

		JButton accept1 = new JButton("确认");
		JButton accept2 = new JButton("确认");
		
		JButton cancel1 = new JButton("取消");
		JLabel cancelNotice = new JLabel("确定要删除账户吗？");

		cancelDialog.setSize(400,240);
		
		cancelDialog.setResizable(false);
		cancelDialog.setLocation(STool.getwX(400),STool.getwY(240));
		
		cancelNotice.setBounds(0,50,400,30);
		cancelNotice.setFont(font2);
		cancelNotice.setHorizontalAlignment(SwingConstants.CENTER);
		cancelDialog.add(cancelNotice);
		
		//取消按钮监听器
		cancel1.setBounds(80,120,100,30);
		cancel1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//关闭该窗口
				cancelDialog.setVisible(false);
				cancelDialog.dispose();
			}
		});
		cancelDialog.add(cancel1);
		
		//确认按钮
		accept1.setBounds(220,120,100,30);
		accept1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				accept1.setVisible(false);
				cancel1.setVisible(false);
				
				try {
					//销户逻辑
					DestroyAccountDelegate.destroyAccount();
					//销户成功
					cancelNotice.setText("销户成功！程序即将关闭");
					accept2.setBounds(150,120,100,30);
					cancelDialog.add(accept2);
					accept2.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							//销户成功关闭所有窗口 
							cancelDialog.setVisible(false);
							cancelDialog.dispose();
							setVisible(false);
							dispose();
						}
					});
					accept2.setVisible(true);
				}catch (Exception e1) {
					//销户失败
					cancelNotice.setText("销户失败！请重试!");
					cancel1.setBounds(300,120,100,30);
					cancel1.setVisible(true);
				}

			}
		});
		cancelDialog.add(accept1);
		
		cancelDialog.setVisible(true);
		
	}
	
	//添加所有监听器
	public void addListeners() {

		// 监听左上角的返回按钮
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//只显示主菜单
				indexPanel.setVisible(true);
				try {
					index();
				} catch (SQLException | IOException ex) {
					throw new RuntimeException(ex);
				}
				withdrawPanel.setVisible(false);
				depositPanel.setVisible(false);
				transferPanel.setVisible(false);
				informationPanel.setVisible(false);
			}
		});

		// 监听底下的返回按钮
		backButtonBelow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				indexPanel.setVisible(true);
				try {
					index();
				} catch (SQLException | IOException ex) {
					throw new RuntimeException(ex);
				}
				showBalancePanel.setVisible(false);
				withdrawConfirmPanel.setVisible(false);
				depositConfirmPanel.setVisible(false);
				transferConfirmPanel.setVisible(false);
			}
		});

		// 监听按钮1 显示余额
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				indexPanel.setVisible(false);
				try {
					showBalance();
				} catch (SQLException | IOException ex) {
					throw new RuntimeException(ex);
				}

                showBalancePanel.setVisible(true);

			}
		});

		// 监听按钮2 取款
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				indexPanel.setVisible(false);
				try {
					withdraw();
				} catch (SQLException | IOException ex) {
					throw new RuntimeException(ex);
				}
				withdrawPanel.setVisible(true);

			}
		});

		// 监听按钮3 存款
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				indexPanel.setVisible(false);
				deposit();
				depositPanel.setVisible(true);
			}
		});

		// 监听按钮4 转账
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				indexPanel.setVisible(false);
				transfer();
				transferPanel.setVisible(true);
			}
		});

		// 监听按钮5 个人信息
		button5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				indexPanel.setVisible(false);
				try {
					information();
				} catch (SQLException | IOException ex) {
					throw new RuntimeException(ex);
				}
                informationPanel.setVisible(true);
			}
		});
		
		// 监听按钮5 注销
		button6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					cancelAccount();
				} catch (SQLException | IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		});

		//实时监听取款文本
		withdrawText.getDocument().addDocumentListener( new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				//如果文本删除
				String a = new String(withdrawText.getText());
				//验证正则表达式
				if(!Pattern.matches("^([1-9]\\d{0,9}|0)([.]?|(\\.\\d{1,2})?)$", a)){
					withdrawNotice.setText("请输入正确的金额");
					withdrawButton.setEnabled(false);
				}else {
					//将输入的文本转换为double型
					try{double ammount = Double.parseDouble(a);
					//输入的文本不能为零
					if(ammount == 0.00) {
						withdrawNotice.setText("取款的金额不能等于0");
					}
					else {
						//在notice上显示格式化后的金额
						StringBuffer sb = new StringBuffer();
						sb.append(df.format(ammount));
						sb.append(" 元");
						withdrawNotice.setText(sb.toString());
						withdrawButton.setEnabled(true);
					}}
					//StringToDouble转化异常
					catch (NumberFormatException e1) {
						withdrawNotice.setText("请输入正确的金额");
						withdrawButton.setEnabled(false);
						System.out.println(e1);
					}
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				String a = new String(withdrawText.getText());
				if(!Pattern.matches("^([1-9]\\d{0,9}|0)([.]?|(\\.\\d{1,2})?)$", a)){
					withdrawNotice.setText("请输入正确的金额");
					withdrawButton.setEnabled(false);
				}else {
					try{double ammount = Double.parseDouble(a);
					if(ammount == 0.00) {
						withdrawNotice.setText("取款的金额不能等于0");
					}
					else {
						StringBuffer sb = new StringBuffer();
						sb.append(df.format(ammount));
						sb.append(" 元");
						withdrawNotice.setText(sb.toString());
						withdrawButton.setEnabled(true);
					}}
					catch (NumberFormatException e1) {
						withdrawNotice.setText("请输入正确的金额");
						withdrawButton.setEnabled(false);
						System.out.println(e1);
					}
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});

		//实时监听存款文本
		depositText.getDocument().addDocumentListener( new DocumentListener() {
			@Override
			//如果文本删除
			public void removeUpdate(DocumentEvent e) {
				String a = new String(depositText.getText());
				//验证正则表达式
				if(!Pattern.matches("^([1-9]\\d{0,9}|0)([.]?|(\\.\\d{1,2})?)$", a)){
					depositNotice.setText("请输入正确的金额");
					depositButton.setEnabled(false);
				}else {
					//将输入的文本转换为double型
					try{double ammount = Double.parseDouble(a);
					//输入的文本不能为零
					if(ammount == 0.00) {
						depositNotice.setText("存款的金额不能等于0");
					}
					else {
						//在notice上显示格式化后的金额
						StringBuffer sb = new StringBuffer();
						sb.append(df.format(ammount));
						sb.append(" 元");
						depositNotice.setText(sb.toString());
						depositButton.setEnabled(true);
					}}
					//StringToDouble转化异常
					catch (NumberFormatException e1) {
						depositNotice.setText("请输入正确的金额");
						depositButton.setEnabled(false);
						System.out.println(e1);
					}
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				String a = new String(depositText.getText());
				if(!Pattern.matches("^([1-9]\\d{0,9}|0)([.]?|(\\.\\d{1,2})?)$", a)){
					depositNotice.setText("请输入正确的金额");
					depositButton.setEnabled(false);
				}else {
					try{double ammount = Double.parseDouble(a);
					if(ammount == 0.00) {
						depositNotice.setText("存款的金额不能等于0");
					}
					else {
						StringBuffer sb = new StringBuffer();
						sb.append(df.format(ammount));
						sb.append(" 元");
						depositNotice.setText(sb.toString());
						depositButton.setEnabled(true);
					}}
					catch (NumberFormatException e1) {
						depositNotice.setText("请输入正确的金额");
						depositButton.setEnabled(false);
						System.out.println(e1);
					}
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});

		//实时监听转账文本
		transferTargetText.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!transferTargetText.getText().equals(""))
					transferButton.setEnabled(true);
				else
					transferButton.setEnabled(false);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!transferTargetText.getText().equals(""))
					transferButton.setEnabled(true);
				else
					transferButton.setEnabled(false);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});
		transferAmmountText.getDocument().addDocumentListener( new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				String a = new String(transferAmmountText.getText());
				if(!Pattern.matches("^([1-9]\\d{0,9}|0)([.]?|(\\.\\d{1,2})?)$", a)){
					transferNotice.setText("请输入正确的金额");
					transferButton.setEnabled(false);
				}else {
					try{double ammount = Double.parseDouble(a);
					if(ammount == 0.00) {
						transferNotice.setText("转账的金额不能等于0");
					}
					else {
						StringBuffer sb = new StringBuffer();
						sb.append(df.format(ammount));
						sb.append(" 元");
						transferNotice.setText(sb.toString());
						if(!transferTargetText.getText().equals(""))
							transferButton.setEnabled(true);
					}}
					catch (NumberFormatException e1) {
						transferNotice.setText("请输入正确的金额");
						transferButton.setEnabled(false);
						System.out.println(e1);
					}
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				String a = new String(transferAmmountText.getText());
				if(!Pattern.matches("^([1-9]\\d{0,9}|0)([.]?|(\\.\\d{1,2})?)$", a)){
					transferNotice.setText("请输入正确的金额");
					transferButton.setEnabled(false);
				}else {
					try{double ammount = Double.parseDouble(a);
					if(ammount == 0.00) {
						transferNotice.setText("转账的金额不能等于0");
					}
					else {
						StringBuffer sb = new StringBuffer();
						sb.append(df.format(ammount));
						sb.append(" 元");
						transferNotice.setText(sb.toString());
						if(!transferTargetText.getText().equals(""))
							transferButton.setEnabled(true);
					}}
					catch (NumberFormatException e1) {
						transferNotice.setText("请输入正确的金额");
						transferButton.setEnabled(false);
						System.out.println(e1);
					}
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});

		// 监听取款按钮
		withdrawButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					String w = withdrawText.getText();
					double wb;
					wb = Double.parseDouble(w);
					double balance = wb;
					ServerResponse<WithdrawResult> response = WithDrawDelegate.withdraw(balance);

					//如果存款金额小于取出金额
					if(response.getCode().equals(ResponseEnum.ERROR)){
						if(response.getErrorMessage().equals(ResponseEnum.ERROR_BALANCE_NOT_ENOUGH)) {
							withdrawNotice.setText("输入的数字不能大于账户余额，请重新输入");
							withdrawButton.setEnabled(false);
						}
						else if(response.getErrorMessage().equals(ResponseEnum.ERROR_BALANCE_TO_LIMIT)) {
							withdrawNotice.setText("输入的数字超过账户上限，请重新输入");
							withdrawButton.setEnabled(false);
						}
					} else {
						//更新余额 以后会在服务器上计算，这里用作测试显示
						//显示取款完成后的界面
						withdrawPanel.setVisible(false);
						withdrawConfirmPanel.setVisible(true);
						withdrawConfirm(wb);

					}}catch (NumberFormatException e1) {
						withdrawNotice.setText("输入错误，无法取款");
					} catch (IOException | SQLException ex) {
					throw new RuntimeException(ex);
				}
            }
		});

		// 监听存款按钮
		depositButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					String d = depositText.getText();
					double dp = 0;
					double balance = 0;
					dp = Double.parseDouble(d);
					ServerResponse<DepositResult> response = DepositDelegate.deposit(dp);
					if(response.getCode().equals(ResponseEnum.ERROR)) {
						if (response.getErrorMessage().equals(ResponseEnum.ERROR_BALANCE_TO_LIMIT)) {
							depositNotice.setText("输入的数字不能大于账户余额，请重新输入");
							depositButton.setEnabled(false);
						}
					}else {
						//显示取款完成后的界面
						depositPanel.setVisible(false);
						depositConfirmPanel.setVisible(true);
						depositConfirm(dp);
					}

				}catch (NumberFormatException e1) {
					depositNotice.setText("输入错误，无法存款");
				} catch (SQLException | IOException ex) {
					throw new RuntimeException(ex);
				}
            }
		});

		//监听转账按钮
		transferButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String amount = new String(transferAmmountText.getText());
					String inid = new String(transferTargetText.getText());
					try {
						double balance = Double.parseDouble(QueryInfoDelegate.queryBalance().getBody());
						double out;
						out = Double.parseDouble(amount);

						ServerResponse<TransferResult> response = TransferDelegate.transfer(inid, out);

						if(response.getCode().equals(ResponseEnum.ERROR)) {
							if(response.getErrorMessage().equals(ResponseEnum.ERROR_BALANCE_NOT_ENOUGH)) {
								transferNotice.setText("超出账户余额！");
							}
							else if(response.getErrorMessage().equals(ResponseEnum.ERROR_USER_NOT_EXISTS)){
								transferNotice.setText("目标账户不存在");
							}
						}else {
							//转账逻辑，应该配合服务端
							//显示转账完成后的界面
							transferPanel.setVisible(false);
							transferConfirm(out,inid);
							transferConfirmPanel.setVisible(true);
						}
					} catch (NullPointerException e1){
						JOptionPane.showMessageDialog(null, "连接丢失","ERROR",JOptionPane.PLAIN_MESSAGE);
					}

				}catch (NumberFormatException e1) {
					transferNotice.setText("输入出错，无法转账");
					System.out.println(e1);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
            }
		});

		//监听信息页更改性别
		edit1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				informationNotice.setText("请输入你想要更改的性别");
				edit1.setEnabled(false);
				save1.setVisible(true);
				save1.setEnabled(true);
				genderText.setEditable(true);
			}
		});

		save1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String sex = QueryInfoDelegate.querySex().getBody();
					String c = genderText.getText();
					if(!sex.equals(c)) {
						//如果格式不符合正则表达式
						if(!(genderText.getText().equals("男")||genderText.getText().equals("女"))){
							informationNotice.setText("请输入正确性别（男、女）");
						}else {
							ChangeInfoDelegate.changeSex(c);
							informationNotice.setText("更改性别成功");
							edit1.setEnabled(true);
							save1.setVisible(false);
							save1.setEnabled(false);
							genderText.setEditable(false);
						}
					}else {
						informationNotice.setText("未进行任何更改");
						edit1.setEnabled(true);
						save1.setVisible(false);
						save1.setEnabled(false);
						genderText.setEditable(false);
					}} catch (IOException ex) {
						throw new RuntimeException(ex);
				}
            }
		});

		//监听信息页更改手机号
		edit2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				informationNotice.setText("请输入你想要更改的手机号");
				edit2.setEnabled(false);
				save2.setVisible(true);
				save2.setEnabled(true);
				phoneText.setEditable(true);
			}
		});

		save2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String tel = QueryInfoDelegate.queryTel().getBody();
					String t = phoneText.getText();
					if(!tel.equals(t)) {
						//如果格式不符合正则表达式
						if(!Pattern.matches("^1[3-9]\\d{9}$",phoneText.getText())){
							informationNotice.setText("请输入正确的手机号");
						}else {
							ChangeInfoDelegate.changeTel(t);
							informationNotice.setText("更改手机号成功");
							edit2.setEnabled(true);
							save2.setVisible(false);
							save2.setEnabled(false);
							phoneText.setEditable(false);
						}
					}else {
						informationNotice.setText("未进行任何更改");
						edit2.setEnabled(true);
						save2.setVisible(false);
						save2.setEnabled(false);
						phoneText.setEditable(false);
					}
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
            }
		});

		//监听信息页更改生日
		edit3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				informationNotice.setText("请输入你想要更改的出生日期");
				edit3.setEnabled(false);
				save3.setVisible(true);
				save3.setEnabled(true);
				birthdayText.setEditable(true);
			}
		});


		save3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String birth = String.valueOf(QueryInfoDelegate.queryBirth().getBody());
					String b = birthdayText.getText();
					if(!birth.equals(b)) {
						//如果格式不符合正则表达式
						if(!Pattern.matches(
								"^((?!0000)[0-9]{4}-((0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)-02-29)$"
								,birthdayText.getText())){
							informationNotice.setText("请输入正确的出生日期");
						}else {
							ChangeInfoDelegate.changeBirth(b);
							informationNotice.setText("更改出生日期成功");
							edit3.setEnabled(true);
							save3.setVisible(false);
							save3.setEnabled(false);
							birthdayText.setEditable(false);
						}
					}else {
						informationNotice.setText("未进行任何更改");
						edit3.setEnabled(true);
						save3.setVisible(false);
						save3.setEnabled(false);
						birthdayText.setEditable(false);
					}
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
            }
		});

		// 限制只能输入数字
		withdrawText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9 ) || keyChar == KeyEvent.VK_PERIOD)) {
					e.consume(); // 关键，屏蔽掉非法输入
				}
			}
		});


		// 限制只能输入数字
		depositText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9 ) || keyChar == KeyEvent.VK_PERIOD)) {
					e.consume(); // 关键，屏蔽掉非法输入
				}
			}
		});

		// 限制只能输入数字
		transferAmmountText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9 ) || keyChar == KeyEvent.VK_PERIOD)) {
					e.consume(); // 关键，屏蔽掉非法输入
				}
			}
		});

		// 限制只能输入数字
		transferTargetText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9 ) || keyChar == KeyEvent.VK_PERIOD)) {
					e.consume(); // 关键，屏蔽掉非法输入
				}
			}
		});

		// 限制只能输入数字
		phoneText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9 ))) {
					e.consume(); // 关键，屏蔽掉非法输入
				}
			}
		});

		// 限制只能输入数字和减号
		birthdayText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9 )||(keyChar == KeyEvent.VK_MINUS))) {
					e.consume(); // 关键，屏蔽掉非法输入
				}
			}
		});
	}
}
