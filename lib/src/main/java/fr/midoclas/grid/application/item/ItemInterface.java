package fr.midoclas.grid.application.item;

import java.awt.Image;

public interface ItemInterface {
	public Image getImage();
	public void updatePosition(int x, int y);
	public int getOffsetX();
	public int getOffsetY();
}
