package fr.midoclas.grid.application.machine;

import java.awt.Graphics;
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
	private int offsetX = 0, offsetY = 0, gridX,  gridY, x, y, tileSize;
	private InfiniteGrid grid;
	private boolean active = true;
	
	public Conveyor(InfiniteGrid grid, int gridX, int gridY) {
		this.grid = grid;
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
	
	public void render(Graphics g) {
		int margin = tileSize / 5;
		int size = tileSize - (margin*2);
		for(ItemInterface item : itemList) {
			if (offsetX + size > tileSize + size/2) {
				this.active = false;
			}
			g.drawImage(item.getImage(), x+this.offsetX, y+margin+this.offsetY, size, size, this);
		}
	}
	
	public void updateAnimation() {
		if (this.active) {
			this.offsetX++;
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
}
