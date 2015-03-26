/*
 * Maps.java
 */

package com.phongbm.snake;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class Maps extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int W_MAPS = 580;
	public static final int H_MAPS = 420;

	private boolean isModernBox;

	private Image imgBackground = new ImageIcon(getClass().getResource(
			"/images/imgBackground.png")).getImage();;

	public Maps() {
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		this.setBounds(10, 10, W_MAPS + 20, H_MAPS + 20);
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	}

	public boolean isModernBox() {
		return isModernBox;
	}

	public void setModernBox(boolean isModernBox) {
		this.isModernBox = isModernBox;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(imgBackground, 10, 10, W_MAPS, H_MAPS, null);
		if (isModernBox) {
			g2D.setColor(Color.GREEN);
			g2D.setStroke(new BasicStroke(10F));
			g2D.drawRect(5, 5, 590, 430);
		}
	}

}