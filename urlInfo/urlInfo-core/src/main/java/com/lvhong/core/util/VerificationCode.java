package com.lvhong.core.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;

public class VerificationCode {
		// 背景色
		private Color bgColor = new Color(255, 255, 255);
		// 基数(一个文字所占的空间大小)
		private int base = 30;
		// 图像宽度
		private int width = base * 4;
		// 图像高度
		private int height = base;
		// 文字个数
		private int len = 4;
		// 设置字体大小
		private int fontSize = 22;
		// 验证码上的文本
		private String text;
		//图片缓存
		private BufferedImage img = null;
		//2D绘制工具
		private Graphics2D g2 = null;
		/**
		 * 生成验证码图片
		 */
		public void drawImage(OutputStream outputStream) {
			//验证码内容
			String codes = PropertiesUtil.getProperty("verificationCode");
			//字体
			String[] fontNames = PropertiesUtil.getProperty("fontNames","").split(",");
			// 1.创建图片缓冲区对象, 并设置宽高和图像类型
			img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			// 2.得到绘制环境
			g2 = (Graphics2D) img.getGraphics();
			// 3.开始画图
			// 设置背景色
			g2.setColor(bgColor);
			g2.fillRect(0, 0, width, height);
			StringBuffer sb = new StringBuffer();// 用来装载验证码上的文本
			for (int i = 0; i < len; i++) {
				// 设置画笔颜色 -- 随机
				// g2.setColor(new Color(255, 0, 0));
				g2.setColor(new Color(getRandom(0, 150), getRandom(0, 150),getRandom(0, 150)));
				// 设置字体
				g2.setFont(new Font(fontNames[getRandom(0, fontNames.length)], Font.BOLD, fontSize));
				// 旋转文字(-45~+45)
				int theta = getRandom(-45, 45);
				g2.rotate(theta * Math.PI / 180, 7 + i * base, height - 8);
				// 写字
				String code = codes.charAt(getRandom(0, codes.length())) + "";
				g2.drawString(code, 7 + i * base, height - 8);
				sb.append(code);
				g2.rotate(-theta * Math.PI / 180, 7 + i * base, height - 8);
			}
			this.text = sb.toString();
			// 画干扰线
			for (int i = 0; i < len + 2; i++) {
				// 设置画笔颜色 -- 随机
				// g2.setColor(new Color(255, 0, 0));
				g2.setColor(new Color(getRandom(0, 150), getRandom(0, 150),
						getRandom(0, 150)));
				g2.drawLine(getRandom(0, 120), getRandom(0, 30), getRandom(0, 120),
						getRandom(0, 30));
			}
			//画边框
			g2.setColor(Color.GRAY);
			g2.drawRect(0, 0, width-1, height-1);
			// 4.保存图片到指定的输出流
			try {
				 ImageIO.write(this.img, "JPEG", outputStream);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}finally{
				// 5.释放资源
				g2.dispose();
			}
		}
		
		/**
		 * 获取验证码字符串
		 * @return
		 */
		public String getCode() {
			return this.text;
		}

		/**
		 * 生成随机数的方法
		 */
		private static int getRandom(int start, int end) {
			Random random = new Random();
			return random.nextInt(end - start) + start;
		}
}