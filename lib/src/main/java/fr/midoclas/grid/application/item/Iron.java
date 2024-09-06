package fr.midoclas.grid.application.item;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Iron implements ItemInterface {

	public final static String imgPath = "machine/iron.png";
	
	@Override
	public Image getImage() {
		return new ImageIcon(getClass().getClassLoader().getResource(imgPath)).getImage();
	}
	
}
