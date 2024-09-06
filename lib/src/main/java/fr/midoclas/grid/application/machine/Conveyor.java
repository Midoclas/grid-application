package fr.midoclas.grid.application.machine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import fr.midoclas.grid.application.InfiniteGrid;
import fr.midoclas.grid.application.item.ItemInterface;

public class Conveyor extends JPanel implements MachineInterface {
	
	private final static String imgPath = "machine/conveyor.png";
	private final static String iconPath = "machine/conveyor-icon.png";
	private List<ItemInterface> itemList = new ArrayList<ItemInterface>();
	private int gridX, gridY;
	private boolean active = true;
	
	public Conveyor(InfiniteGrid grid, int gridX, int gridY) {
		this.gridX = gridX;
		this.gridY = gridY;
		this.checkAdjacent();
	}
	
	public Conveyor() {

	}	

	private void checkAdjacent() {
		
	}
	
	public void addItem(ItemInterface item) {
		this.itemList.add(item);
	}
	
	public void removeItem(int index) {
		this.itemList.remove(index);
	}
	
	public void render(Graphics g, int offsetX, int offsetY, int tileSize) {
	
		int margin = tileSize / 5;
		int size = tileSize - (margin*2);
		
		int machineX = (gridX * tileSize) - offsetX;
		int machineY = (gridY * tileSize) - offsetY;
		    
		    
		for(ItemInterface item : itemList) {
			if (item.getOffsetX() + size > tileSize + size/2) {
				this.active = false;
			}
			g.drawImage(item.getImage(), machineX+item.getOffsetX(), machineY+margin+item.getOffsetY(), size, size, this);
			
		}
	}
	
	public void updateAnimation() {
		if (this.active) {
			for(ItemInterface item : itemList) {
				item.updatePosition(1, 0);
			}
			
		}
	}
	
	public void enable() {
		this.active = true;
	}
	
	public void disable() {
		this.active = false;
	}

	@Override
	public String getOptionName() {
		return "CONVEYOR";
	}

	@Override
	public Image getImage() {
		return new ImageIcon(getClass().getClassLoader().getResource(imgPath)).getImage();
	}

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon(getClass().getClassLoader().getResource(iconPath));
	}

	@Override
	public MachineInterface newInstance(InfiniteGrid grid, int gridX, int gridY) {
		return new Conveyor(grid, gridX, gridY);
	}

	@Override
	public int getGridX() {
		return this.gridX;
	}

	@Override
	public int getGridY() {
		return this.gridY;
	}
}
