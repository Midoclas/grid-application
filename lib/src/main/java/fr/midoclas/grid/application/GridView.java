package fr.midoclas.grid.application;

import javax.swing.*;

import fr.midoclas.grid.application.item.Iron;
import fr.midoclas.grid.application.listener.GridListener;
import fr.midoclas.grid.application.machine.Conveyor;
import fr.midoclas.grid.application.machine.MachineController;
import fr.midoclas.grid.application.machine.MachineInterface;

import java.awt.*;
import java.util.function.Supplier;

public class GridView extends JPanel {
	private InfiniteGrid grid;
	private MachineInterface selectedOption;
	private int offsetX, offsetY, gridX, gridY, mouseX, mouseY;
	private final int tileSize = 50;
	private Timer timer;
	public java.awt.Point lastMousePosition = new java.awt.Point(0,0);
	public MachineController machineController;

	public GridView(InfiniteGrid grid) {
		GridListener listener = new GridListener(this);
		machineController = new MachineController(grid);
		machineController.setTileSize(tileSize);
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
		this.setBackground(Color.PINK);
		this.timer = new Timer(8, listener);
		this.timer.start();
		this.grid = grid;
		this.offsetX = 0;
		this.offsetY = 0;
	}

	public void render(Graphics g) {
		this.calcMouseCoordinate(lastMousePosition.x, lastMousePosition.y);
		
		int width = getWidth();
		int height = getHeight();

		int horizontalTiles = width / tileSize + 2;
		int verticalTiles = height / tileSize + 2;

		int startX = offsetX / tileSize;
		int startY = offsetY / tileSize;
		

		for (int x = startX - tileSize; x < startX + horizontalTiles; x++) {
			for (int y = startY - tileSize; y < startY + verticalTiles; y++) {
				Tile tile = grid.getTile(x, y);
				int drawX = (x * tileSize) - offsetX;
				int drawY = (y * tileSize) - offsetY;
				if (tile.getMachine() != null) {
					MachineInterface tileMachine = tile.getMachine();
					if (tileMachine != null) {
						g.drawImage(tileMachine.getImage(), drawX, drawY, tileSize, tileSize, this);
					}
				} else {
					if (x == mouseX && y == mouseY) {
						Graphics2D g2d = (Graphics2D) g;
				        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5F));
						g2d.drawImage(this.selectedOption.getImage(), drawX, drawY, tileSize, tileSize, this);
				        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1F));
					}
//					g.setColor(Color.BLACK);
//					g.drawRect(drawX, drawY, tileSize, tileSize);
				}
				
				g.setColor(Color.BLUE);
				g.drawString(x + "," + y, drawX, drawY + 10);
			}
		}
		machineController.renderMachines(g);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		render(g);
	}

	public void scroll(int deltaX, int deltaY) {
		offsetX -= deltaX;
		offsetY -= deltaY;
		machineController.setOffsetX(offsetX);
		machineController.setOffsetY(offsetY);
	}
	
	private void calcMouseCoordinate(int x, int y) {
		this.mouseX = Math.floorDiv(x + offsetX, tileSize);
		this.mouseY = Math.floorDiv(y + offsetY, tileSize);
	}
	
	private void calcCoordinate(int x, int y) {
		this.gridX = Math.floorDiv(x + offsetX, tileSize);
		this.gridY = Math.floorDiv(y + offsetY, tileSize);
	}

	public Tile getTileAtPosition(int x, int y) {
		this.calcCoordinate(x, y);
		return grid.getTile(this.gridX, this.gridY);
	}
	
	public void setTileAtPosition(int x, int y, Tile tile) {
		this.calcCoordinate(x, y);
		grid.setTile(this.gridX, this.gridY, tile);
	}
	
	public void removeTileAtPosition(int x, int y) {
		this.calcCoordinate(x, y);
		grid.removeTile(this.gridX, this.gridY);
	}

	public void handleTileClick(int x, int y) {
		Tile tile = this.getTileAtPosition(x, y);
		if (tile != null) {
			if (tile.getMachine() == null && this.selectedOption != null) {
				MachineInterface machine = this.selectedOption.newInstance(this.grid, this.gridX, this.gridY, this.tileSize);
				tile.setMachine(machine);
				machineController.addMachine(machine);
				this.setTileAtPosition(x, y, tile);
			}
		}
	}
	
	public void removeTileClick(int x, int y) {
		Tile tile = this.getTileAtPosition(x, y);
		if (tile != null) {
			if (tile.getMachine() != null) {
				tile.setMachine(null);
				this.removeTileAtPosition(x, y);
			}
		}
	}

	public void dragGrid(int deltaX, int deltaY) {
		this.scroll(deltaX, deltaY);
	}

	public void setMachineSelected(MachineInterface machine) {
		this.selectedOption = machine;
	}
}
