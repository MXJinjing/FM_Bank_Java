package cn.edu.lzu.fmbank.client.frames;

import cn.edu.lzu.fmbank.client.util.STool;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class NoteDialog extends JDialog{
	/**
	 * 提示弹窗
	 */
	private static final long serialVersionUID = 5991483867531516279L;
	JLabel note = new JLabel("提示文本");
	JButton button = new JButton("确定");
	
	private Font font2 = new Font("仿宋", Font.PLAIN, 18);
	
	public NoteDialog(JFrame frame, String noteTitle,String noteText) {
		super(frame,noteTitle,true);
		this.note.setText(noteText);
		load();
	}
	
	public NoteDialog(JFrame frame,String noteText) {
		super(frame,true);
		this.note.setText(noteText);
		load();
	}
	
	public NoteDialog(JFrame frame) {
		super(frame,true);
		load();
	}
	
	public void setText(String text) {
		note.setText(text);
	}
	
	private void load() {
		setResizable(false);
		setSize(350,200);
		setLocation(STool.getwX(350),STool.getwY(200));
		setLayout(null);
		
		note.setFont(font2);
		note.setHorizontalAlignment(SwingConstants.CENTER);
		note.setBounds(0,50,350,30);
		add(note);
		
		button.setFont(font2);
		button.setBounds(125,100,100,30);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		add(button);
	}
}
