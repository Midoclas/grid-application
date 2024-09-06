package fr.midoclas.grid.application.machine;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

import fr.midoclas.grid.application.InfiniteGrid;
import fr.midoclas.grid.application.item.ItemInterface;

public interface MachineInterface{

	public String getOptionName();
	
	public MachineInterface newInstance(InfiniteGrid grid, int gridX, int gridY);
	
	public void addItem(ItemInterface item);
	
	public void removeItem(int index);

	public Image getImage();
	
	public ImageIcon getIcon();
	
	public void render(Graphics g);

}
