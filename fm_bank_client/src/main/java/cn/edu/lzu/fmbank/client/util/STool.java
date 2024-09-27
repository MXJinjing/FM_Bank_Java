package cn.edu.lzu.fmbank.client.util;

import java.awt.Dimension;
import java.awt.Toolkit;

public class STool{
	/*
	 *  获取窗口的位置，屏幕的分辨率等
	 */
	public static int getwX(int windowWidth) {
		Toolkit kit = Toolkit.getDefaultToolkit();              //定义工具包
		Dimension screenSize = kit.getScreenSize();             //获取屏幕的尺寸
		int screenWidth = screenSize.width;                     //获取屏幕的宽
		return screenWidth/2-windowWidth/2;
	}
	
	public static int getwY(int windowHeight) {
		Toolkit kit = Toolkit.getDefaultToolkit();              //定义工具包
		Dimension screenSize = kit.getScreenSize();             //获取屏幕的尺寸
		int screenHeight = screenSize.height;                     //获取屏幕的高
		return screenHeight/2-windowHeight/2;
	}
}
