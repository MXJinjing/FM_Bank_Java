package cn.edu.lzu.fmbank.client.frames;

//import java.awt.Desktop;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cn.edu.lzu.fmbank.client.admin.ExportAccounts;
import cn.edu.lzu.fmbank.client.admin.ExportPdf;
import cn.edu.lzu.fmbank.client.admin.ImportAccounts;
import cn.edu.lzu.fmbank.client.admin.OpenAccount;
import cn.edu.lzu.fmbank.client.util.STool;

import com.itextpdf.text.DocumentException;
import jxl.write.WriteException;

public class AdminFrame extends JFrame{
	/**
	 * 	管理员操作面板
	 */
	private static final long serialVersionUID = 5333059832216784020L;

	private Font font2 = new Font("仿宋", Font.PLAIN, 18);

	private JPanel openAccountPanel = new JPanel();
	private JLabel cardIDLabel = new JLabel("身份证号");
	private JLabel userNameLabel= new JLabel("用户名");
	private JLabel userIDLabel = new JLabel("用户账号ID");
	private JLabel genderLabel = new JLabel("性别");
	private JLabel phoneLabel = new JLabel("手机号");
	private JLabel birthdayLabel = new JLabel("出生日期");
	private JLabel openNoticeLabel = new JLabel();

	private JButton backButton = new JButton("返回");
	private JButton nextButton = new JButton("继续");

	private JTextField cardIDText = new JTextField(12);
	private JTextField userNameText = new JTextField(20);
	private JTextField userIDText = new JTextField(20);
	private JTextField phoneText = new JTextField(11);
	private JTextField genderText = new JTextField(1);
	private JTextField birthdayText = new JTextField(10);

	private File file1 = null;//导入的文件
	private File file2 = null;//导出的文件
	private File file3 = null;//年终报告文件

	private JPanel indexPanel = new JPanel();
	private JButton button1 = new JButton("导出用户信息");
	private JButton button2 = new JButton("导入用户信息");
	private JButton button3 = new JButton("手动开户");
	private JButton button4 = new JButton("导出年终报告");

	private NoteDialog noteDialog = new NoteDialog(this);
	private JDialog inDialog = new JDialog(this,true);
	private JDialog exDialog = new JDialog(this,true);
	private JDialog reportDialog = new JDialog(this,true);
	private JDialog pswdDialog = new JDialog(this,"设置密码",true);

	public AdminFrame() {
		super("管理面板");
		setSize(800,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(STool.getwX(800), STool.getwY(500));//设置窗口居中

		addListeners();
		setVisible(true);
	}


	//进入主界面
	public void load() {
		try{
			System.out.println("正在加载管理员主界面");
			index();
		}catch (Exception e) {
			System.out.println("界面加载异常:"+e);
		}
	}

	//导入用户文件的窗口
	private void importDialog() {

		file1 = null;//清空文件

		inDialog.setResizable(false);
		inDialog.setLayout(null);
		inDialog.setSize(500,250);
		inDialog.setLocation(STool.getwX(500),STool.getwY(250));

		//文件选择器
		JFileChooser chooser = new JFileChooser(); 
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.setMultiSelectionEnabled(false);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setDialogTitle("选择文件以导入");

		JLabel label = new JLabel("请选择文件以导入");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(font2);
		label.setBounds(0,50,500,30);

		//路径文本
		JTextField textField = new JTextField("");
		textField.setEditable(false);
		textField.setBounds(80,100,330,30);

		//浏览文件按钮，打开文件选择界面
		JButton chooseButton = new JButton("浏览");
		chooseButton.setFont(font2);
		chooseButton.setBounds(80,150,150,40);

		//导入文件按钮，默认不可用
		JButton confirmButton = new JButton("导入");
		confirmButton.setFont(font2);
		confirmButton.setEnabled(false);
		confirmButton.setBounds(270,150,150,40);


		inDialog.add(textField);
		inDialog.add(label);
		inDialog.add(chooseButton);
		inDialog.add(confirmButton);

		//上述按钮的监听器
		chooseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooser.showOpenDialog(chooser);        //打开文件选择窗
				file1 = chooser.getSelectedFile();  	//获取选择的文件
				if(file1 != null) {
					textField.setText(file1.getPath());	//获取选择文件的路径
					confirmButton.setEnabled(true);
				}else {
					confirmButton.setEnabled(false);
				}
			}
		});

		confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//导入的方法
				Boolean success;
				success = ImportAccounts.doit(file1.toString());

				if(success) {
					noteDialog.setText("导入成功");
					noteDialog.setVisible(true);
					noteDialog.dispose();
					inDialog.dispose();
				}else {
					noteDialog.setText("导入失败");
					noteDialog.setVisible(true);
				}
			}
		});

		//显示窗口
		inDialog.setVisible(true);
	}

	//导出所有数据文件的窗口
	private void exportDialog() {

		file2 = new File(System.getProperty("user.home")+"\\export.xls");//默认导出路径

		exDialog.setResizable(false);
		exDialog.setLayout(null);
		exDialog.setSize(500,250);
		exDialog.setLocation(STool.getwX(500),STool.getwY(250));

		//文件选择器
		JFileChooser chooser = new JFileChooser(); 
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		chooser.setMultiSelectionEnabled(false);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setDialogTitle("选择文件夹以存放");

		JLabel label = new JLabel("请选择文件存放位置");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(font2);
		label.setBounds(0,50,500,30);

		//路径文本
		JTextField textField = new JTextField(file2.getPath());
		textField.setEditable(true);
		textField.setBounds(80,100,330,30);

		//浏览文件按钮，打开文件选择界面
		JButton chooseButton = new JButton("浏览");
		chooseButton.setFont(font2);
		chooseButton.setBounds(80,150,150,40);

		//导入文件按钮，默认可用
		JButton confirmButton = new JButton("导出");
		confirmButton.setFont(font2);
		confirmButton.setBounds(270,150,150,40);

		exDialog.add(textField);
		exDialog.add(label);
		exDialog.add(chooseButton);
		exDialog.add(confirmButton);

		//上述按钮的监听器
		chooseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooser.showOpenDialog(chooser);        //打开文件选择窗
				textField.setText(chooser.getSelectedFile().getPath());	//获取选择文件的路径
				confirmButton.setEnabled(true);
			}
		});

		confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				file2 = new File(textField.getText());

				Boolean success = true;

				try {
					ExportAccounts.doit(file2);
				} catch (SQLException | IOException | WriteException ex) {
					success = false;
					throw new RuntimeException(ex);
				}
				//初始化导出的输出流

				if(success) {
					//打开文件
					Desktop temp = Desktop.getDesktop();
					try {
						temp.open(file2);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					noteDialog.setText("导出成功");
					noteDialog.setVisible(true);
					noteDialog.dispose();
					exDialog.dispose();
				}else {
					noteDialog.setText("导出失败");
					noteDialog.setVisible(true);
				}
			}

		});

		//显示窗口
		exDialog.setVisible(true);
	}
	
	private void exReport() {

		file3 = new File(System.getProperty("user.home")+"\\report.pdf");//默认导出路径

		reportDialog.setResizable(false);
		reportDialog.setLayout(null);
		reportDialog.setSize(500,250);
		reportDialog.setLocation(STool.getwX(500),STool.getwY(250));

		//文件选择器
		JFileChooser chooser = new JFileChooser(); 
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		chooser.setMultiSelectionEnabled(false);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setDialogTitle("选择文件夹以存放");

		JLabel label = new JLabel("请选择文件存放位置");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(font2);
		label.setBounds(0,50,500,30);

		//路径文本
		JTextField textField = new JTextField(file3.getPath());
		textField.setEditable(true);
		textField.setBounds(80,100,330,30);

		//浏览文件按钮，打开文件选择界面
		JButton chooseButton = new JButton("浏览");
		chooseButton.setFont(font2);
		chooseButton.setBounds(80,150,150,40);

		//导入文件按钮，默认可用
		JButton confirmButton = new JButton("导出");
		confirmButton.setFont(font2);
		confirmButton.setBounds(270,150,150,40);

		reportDialog.add(textField);
		reportDialog.add(label);
		reportDialog.add(chooseButton);
		reportDialog.add(confirmButton);

		//上述按钮的监听器
		chooseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooser.showOpenDialog(chooser);        //打开文件选择窗
				textField.setText(chooser.getSelectedFile().getPath());	//获取选择文件的路径
				confirmButton.setEnabled(true);
			}
		});

		confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				file3 = new File(textField.getText());

				Boolean success = true;

				try {
					ExportPdf.doit(file3.toString());
				} catch (SQLException | FileNotFoundException | DocumentException ex) {
					success = false;
					ex.printStackTrace();
				}

				if(success) {

					noteDialog.setText("导出成功");
					noteDialog.setVisible(true);
					noteDialog.dispose();
					reportDialog.dispose();
				}else {
					noteDialog.setText("导出失败");
					noteDialog.setVisible(true);
				}
			}

		});
		
		reportDialog.setVisible(true);
	}


	private void openAccount() {
		add(openAccountPanel);
		openAccountPanel.setLayout(null);

		// 返回按钮
		backButton.setBounds(40,20,80,40);
		openAccountPanel.add(backButton);

		// 继续按钮
		nextButton.setBounds(680,20,80,40);
		openAccountPanel.add(nextButton);

		//用户ID
		userIDLabel.setBounds(180,100,100,30);
		userIDLabel.setFont(font2);
		openAccountPanel.add(userIDLabel);

		userIDText.setBounds(300,100,300,30);
		userIDText.setText("");
		userIDText.setHorizontalAlignment(SwingConstants.CENTER);
		userIDText.setFont(font2);
		openAccountPanel.add(userIDText);

		//用户名
		userNameLabel.setBounds(180,150,100,30);
		userNameLabel.setFont(font2);
		openAccountPanel.add(userNameLabel);

		userNameText.setBounds(300,150,300,30);
		userNameText.setText("");
		userNameText.setHorizontalAlignment(SwingConstants.CENTER);
		userNameText.setFont(font2);
		openAccountPanel.add(userNameText);

		//身份证号
		cardIDLabel.setBounds(180,200,100,30);
		cardIDLabel.setFont(font2);
		openAccountPanel.add(cardIDLabel);

		cardIDText.setBounds(300,200,300,30);
		cardIDText.setText("");
		cardIDText.setHorizontalAlignment(SwingConstants.CENTER);
		cardIDText.setFont(font2);
		openAccountPanel.add(cardIDText);

		//性别
		genderLabel.setBounds(180,250,100,30);
		genderLabel.setFont(font2);
		openAccountPanel.add(genderLabel);

		genderText.setBounds(300,250,300,30);
		genderText.setText("");
		genderText.setHorizontalAlignment(SwingConstants.CENTER);
		genderText.setFont(font2);
		openAccountPanel.add(genderText);


		//电话号码
		phoneLabel.setBounds(180,300,100,30);
		phoneLabel.setFont(font2);
		openAccountPanel.add(phoneLabel);

		phoneText.setBounds(300,300,300,30);
		phoneText.setText("");
		phoneText.setHorizontalAlignment(SwingConstants.CENTER);
		phoneText.setFont(font2);
		openAccountPanel.add(phoneText);

		//出生日期
		birthdayLabel.setBounds(180,350,100,30);
		birthdayLabel.setFont(font2);
		openAccountPanel.add(birthdayLabel);

		birthdayText.setBounds(300,350,300,30);
		birthdayText.setText("");
		birthdayText.setHorizontalAlignment(SwingConstants.CENTER);
		birthdayText.setFont(font2);
		openAccountPanel.add(birthdayText);

		//提示文本
		openNoticeLabel.setBounds(0,50,800,30);
		openNoticeLabel.setFont(font2);
		openNoticeLabel.setText("");
		openNoticeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		openAccountPanel.add(openNoticeLabel);
	}

	//显示主页的方法
	private void index() {
		add(indexPanel);
		indexPanel.setLayout(null);

		button1.setBounds(250,50,300,50);
		button1.setFont(font2);
		indexPanel.add(button1);

		button2.setBounds(250,110,300,50);
		button2.setFont(font2);
		indexPanel.add(button2);

		button3.setBounds(250,170,300,50);
		button3.setFont(font2);
		indexPanel.add(button3);
		
		button4.setBounds(250,330,300,50);
		button4.setFont(font2);
		indexPanel.add(button4);
	}

	//添加所有监听器
	private void addListeners() {

		//导出数据
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exportDialog();
			}
		});

		//导入数据
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				importDialog();
			}
		});

		//手动开户导入
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				indexPanel.setVisible(false);
				openAccount();
				openAccountPanel.setVisible(true);
			}
		});
		
		//年终报告
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exReport();
			}
		});

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//只显示主菜单
				indexPanel.setVisible(true);
				openAccountPanel.setVisible(false);
				index();
			}
		});

		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(userIDText.getText().length()==0||userNameText.getText().length()==0||cardIDText.getText().length()==0) {
					openNoticeLabel.setText("请填写完整信息");
				}
				else if(genderText.getText().length()!=1||!(genderText.getText().toCharArray()[0] == 'F'||genderText.getText().toCharArray()[0] == 'M')) {
					openNoticeLabel.setText("请输入正确的性别F/M");
				}
				else if(!Pattern.matches("^1[3-9]\\d{9}$",phoneText.getText())){
					openNoticeLabel.setText("请输入正确的手机号");
				}else if(!Pattern.matches(
						"^((?!0000)[0-9]{4}-((0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)-02-29)$"
						,birthdayText.getText())) {
					openNoticeLabel.setText("请输入正确的出生日期");
				}else {
					//进入设置密码的界面
					pswdDialog.setResizable(false);
					pswdDialog.setLayout(null);
					pswdDialog.setSize(500,250);
					pswdDialog.setLocation(STool.getwX(500),STool.getwY(250));

					JLabel label1 = new JLabel("设置密码");
					label1.setBounds(50,30,130,40);
					label1.setFont(font2);
					pswdDialog.add(label1);

					JPasswordField pswd = new JPasswordField(16);
					pswd.setBounds(180,30,220,40);
					pswd.setHorizontalAlignment(SwingConstants.CENTER);
					pswdDialog.add(pswd);

					JLabel label2 = new JLabel("再次输入");
					label2.setBounds(50,80,130,40);
					label2.setFont(font2);
					pswdDialog.add(label2);

					JPasswordField repswd = new JPasswordField(16);
					repswd.setBounds(180,80,220,40);
					repswd.setHorizontalAlignment(SwingConstants.CENTER);
					pswdDialog.add(repswd);

					JButton confirmButtion = new JButton("提交");
					confirmButtion.setFont(font2);
					confirmButtion.setBounds(175,150,125,40);
					pswdDialog.add(confirmButtion);
					confirmButtion.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							if(pswd.getPassword().length<4) {
								//密码必须大于4位
								noteDialog.setText("请输入大于4位的密码");
								noteDialog.setVisible(true);
							}else {
								String pswdString = new String(pswd.getPassword());
								String repswdString = new String(repswd.getPassword());

								//如果两次输入的密码一致
								if(pswdString.equals(repswdString))	{	
									Boolean success = true;
									//提交用户信息
									//
//									//将用户信息记录到一个类中
//									User temp = new User();
//									try {
//										temp.setUserID(Integer.parseInt(userIDText.getText()));
//										temp.setUserName(userIDText.getText());
//										temp.setGender(genderText.getText().toCharArray()[0]);
//										temp.setCardID(cardIDText.getText());
//										temp.setPhone(phoneText.getText());
//										temp.setBirthday(birthdayText.getText());
//										OpenAccount.doit(temp.getUserID(), temp.getUserName(), temp.getGender(), temp.getCardID(), temp.getPhone(), temp.getBirthday(), repswdString);
//
//									}catch (NumberFormatException e1) {
//										success = false;
//										e1.printStackTrace();
//									} catch (SQLException ex) {
//										success = false;
//										ex.printStackTrace();
//									}
//									if(success) {
//										noteDialog.setText("开户成功");
//									}else {
//										noteDialog.setText("开户失败");
//									}

									//清空密码框
									pswd.setText("");
									repswd.setText("");
									noteDialog.setVisible(true);
									pswdDialog.dispose();
									indexPanel.setVisible(true);
									openAccountPanel.setVisible(false);
								}else {
									noteDialog.setText("两次输入的密码不一致");
									noteDialog.setVisible(true);
								}
							}
						}
					});

					pswdDialog.addWindowListener(new WindowListener() {

						@Override
						public void windowOpened(WindowEvent e) {

						}

						@Override
						public void windowIconified(WindowEvent e) {
						}

						@Override
						public void windowDeiconified(WindowEvent e) {
						}

						@Override
						public void windowDeactivated(WindowEvent e) {
						}

						@Override
						public void windowClosing(WindowEvent e) {
							//清空密码框
							pswd.setText("");
							repswd.setText("");
						}

						@Override
						public void windowClosed(WindowEvent e) {
						}

						@Override
						public void windowActivated(WindowEvent e) {
						}
					});
					pswdDialog.setVisible(true);
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

		// 限制只能输入数字
		cardIDText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9 ))) {
					e.consume(); // 关键，屏蔽掉非法输入
				}
			}
		});

		// 限制只能输入数字
		userIDText.addKeyListener(new KeyAdapter() {
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
