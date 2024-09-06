package fr.midoclas.grid.application.item;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Iron implements ItemInterface {

	public final static String imgPath = "machine/iron.png";
	private int offsetX = 0, offsetY = 0;
	
	@Override
	public Image getImage() {
		return new ImageIcon(getClass().getClassLoader().getResource(imgPath)).getImage();
	}

	@Override
	public void updatePosition(int x, int y) {
		this.offsetX += x;
		this.offsetY += y;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}
}
