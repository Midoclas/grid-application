package fr.midoclas.grid.application.machine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import fr.midoclas.grid.application.InfiniteGrid;
import fr.midoclas.grid.application.Tile;
import fr.midoclas.grid.application.item.ItemInterface;

public class Conveyor extends JPanel implements MachineInterface {
	
	// Image
	private final static String imgPath = "machine/conveyor";
	private final static String iconPath = "machine/conveyor-icon.png";
	
	// Conveyor type
	private final static int DEFAULT = 0;
	private final static int LEFT_TO_RIGHT = 1;
	private final static int LEFT_TO_TOP = 2;
	private final static int LEFT_TO_BOTTOM = 3;
	private final static int LEFT_TO = 4;
	private final static int TO_LEFT = 5;
	private final static int RIGHT_TO_LEFT = 6;
	private final static int RIGHT_TO_TOP = 7;
	private final static int RIGHT_TO_BOTTOM = 8;
	private final static int RIGHT_TO = 9;
	private final static int TO_RIGHT = 10;
	private final static int TOP_TO_RIGHT = 11;
	private final static int TOP_TO_LEFT = 12;
	private final static int TOP_TO_BOTTOM = 13;
	private final static int TOP_TO = 14;
	private final static int TO_TOP = 15;
	private final static int BOTTOM_TO_RIGHT = 16;
	private final static int BOTTOM_TO_TOP = 17;
	private final static int BOTTOM_TO_LEFT = 18;
	private final static int BOTTOM_TO = 19;
	private final static int TO_BOTTOM = 20;
	
	private final static Set<Integer> SET_TO_RIGHT = Set.of(1,10,11,16);
	private final static Set<Integer> SET_TO_LEFT = Set.of(5,6,12,18);
	private final static Set<Integer> SET_TO_TOP = Set.of(2,7,15,17);
	private final static Set<Integer> SET_TO_BOTTOM = Set.of(3,8,13,20);
	
	private final static Set<Integer> SET_FROM_RIGHT = Set.of(6,7,8,9);
	private final static Set<Integer> SET_FROM_LEFT = Set.of(1,2,3,4);
	private final static Set<Integer> SET_FROM_TOP = Set.of(11,12,13,14);
	private final static Set<Integer> SET_FROM_BOTTOM = Set.of(16,17,18,19);
	
	private final static Set<Integer> UNPLUGED = Set.of(0,4,9,14,19,5,10,15,20);
	
	private List<ItemInterface> itemList = new ArrayList<ItemInterface>();
	private int gridX, gridY, tileSize;
	private int marginX, marginY, conveyorEnd;
	private int type = DEFAULT;
	private InfiniteGrid grid;
	private boolean active = true;
	
	public Conveyor(InfiniteGrid grid, int gridX, int gridY, int tileSize) {
		this.grid = grid;
		this.gridX = gridX;
		this.gridY = gridY;
		this.tileSize = tileSize;
		this.checkAdjacent();
	}
	
	public Conveyor() {

	}	

	private void checkAdjacent() {
		int[] dir_to = new int[21];
		int[] dir_from = new int[21];
		
		checkDirection(-1, 0, LEFT_TO, TO_LEFT, RIGHT_TO, TO_RIGHT, dir_from, dir_to, SET_TO_RIGHT, SET_FROM_RIGHT);
		checkDirection(1, 0, RIGHT_TO, TO_RIGHT, LEFT_TO, TO_LEFT, dir_from, dir_to, SET_TO_LEFT, SET_FROM_LEFT);
		checkDirection(0, -1, TOP_TO, TO_TOP, BOTTOM_TO, TO_BOTTOM, dir_from, dir_to, SET_TO_BOTTOM, SET_FROM_BOTTOM);
		checkDirection(0, 1, BOTTOM_TO, TO_BOTTOM, TOP_TO, TO_TOP, dir_from, dir_to, SET_TO_TOP, SET_FROM_TOP);
		
		System.out.println("Contenu de dir_from: " + Arrays.toString(dir_from));
		System.out.println("Contenu de dir_to: " + Arrays.toString(dir_to));
		int type = determineType(dir_from, dir_to);
		setType(type);
//		System.out.println(getType());
		
	}
	
	public void addItem(ItemInterface item) {
		this.itemList.add(item);
	}
	
	public void removeItem(int index) {
		this.itemList.remove(index);
	}
	
	public void render(Graphics g, int offsetX, int offsetY) {
	
		int margin = tileSize / 5;
		int size = tileSize - (margin*2);
		
		int machineX = (gridX * tileSize) - offsetX + marginX;
		int machineY = (gridY * tileSize) - offsetY + marginY;
		    
		for(ItemInterface item : itemList) {
			if (item.getOffsetX() + size > tileSize + size/2) {
				this.active = false;
			}
			g.drawImage(item.getImage(), machineX+item.getOffsetX(), machineY+item.getOffsetY(), size, size, this);
			
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
	
	public int getType() {
		return this.type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	private int determineType(int[] dir_from, int[] dir_to) {
		if (dir_from[LEFT_TO] == 1 && dir_to[TO_RIGHT] == 1) return LEFT_TO_RIGHT;
	    if (dir_from[RIGHT_TO] == 1 && dir_to[TO_LEFT] == 1) return RIGHT_TO_LEFT;
	    if (dir_from[TOP_TO] == 1 && dir_to[TO_BOTTOM] == 1) return TOP_TO_BOTTOM;
	    if (dir_from[BOTTOM_TO] == 1 && dir_to[TO_TOP] == 1) return BOTTOM_TO_TOP;
	    if (dir_from[LEFT_TO] == 1 && dir_to[TO_TOP] == 1) return LEFT_TO_TOP;
	    if (dir_from[LEFT_TO] == 1 && dir_to[TO_BOTTOM] == 1) return LEFT_TO_BOTTOM;
	    if (dir_from[RIGHT_TO] == 1 && dir_to[TO_TOP] == 1) return RIGHT_TO_TOP;
	    if (dir_from[RIGHT_TO] == 1 && dir_to[TO_BOTTOM] == 1) return RIGHT_TO_BOTTOM;
	    if (dir_from[TOP_TO] == 1 && dir_to[TO_LEFT] == 1) return TOP_TO_LEFT;
	    if (dir_from[TOP_TO] == 1 && dir_to[TO_RIGHT] == 1) return TOP_TO_RIGHT;
	    if (dir_from[BOTTOM_TO] == 1 && dir_to[TO_LEFT] == 1) return BOTTOM_TO_LEFT;
	    if (dir_from[BOTTOM_TO] == 1 && dir_to[TO_RIGHT] == 1) return BOTTOM_TO_RIGHT;
	    if (dir_from[LEFT_TO] == 1) return LEFT_TO;
	    if (dir_from[RIGHT_TO] == 1) return RIGHT_TO;
	    if (dir_from[TOP_TO] == 1) return TOP_TO;
	    if (dir_from[BOTTOM_TO] == 1) return BOTTOM_TO;
	    if (dir_to[TO_LEFT] == 1) return TO_LEFT;
	    if (dir_to[TO_RIGHT] == 1) return TO_RIGHT;
	    if (dir_to[TO_TOP] == 1) return TO_TOP;
	    if (dir_to[TO_BOTTOM] == 1) return TO_BOTTOM;
	    return DEFAULT;
	}
	
	private void plugConveyor(Conveyor conveyor, int toDirection) {
		int conveyorType = conveyor.getType();
		
		if (conveyorType == LEFT_TO && toDirection == TO_RIGHT) conveyor.setType(LEFT_TO_RIGHT);
		if (conveyorType == TOP_TO && toDirection == TO_RIGHT) conveyor.setType(TOP_TO_RIGHT);
		if (conveyorType == BOTTOM_TO && toDirection == TO_RIGHT) conveyor.setType(BOTTOM_TO_RIGHT);
		if (conveyorType == DEFAULT) conveyor.setType(toDirection);
		
		if (conveyorType == RIGHT_TO && toDirection == TO_LEFT) conveyor.setType(RIGHT_TO_LEFT);
		if (conveyorType == TOP_TO && toDirection == TO_LEFT) conveyor.setType(TOP_TO_LEFT);
		if (conveyorType == BOTTOM_TO && toDirection == TO_LEFT) conveyor.setType(BOTTOM_TO_LEFT);
		if (conveyorType == DEFAULT) conveyor.setType(toDirection);

		if (conveyorType == BOTTOM_TO && toDirection == TO_TOP) conveyor.setType(BOTTOM_TO_TOP);
		if (conveyorType == LEFT_TO && toDirection == TO_TOP) conveyor.setType(LEFT_TO_TOP);
		if (conveyorType == RIGHT_TO && toDirection == TO_TOP) conveyor.setType(RIGHT_TO_TOP);
		if (conveyorType == DEFAULT) conveyor.setType(toDirection);

		if (conveyorType == TOP_TO && toDirection == TO_BOTTOM) conveyor.setType(TOP_TO_BOTTOM);
		if (conveyorType == LEFT_TO && toDirection == TO_BOTTOM) conveyor.setType(LEFT_TO_BOTTOM);
		if (conveyorType == RIGHT_TO && toDirection == TO_BOTTOM) conveyor.setType(RIGHT_TO_BOTTOM);
		if (conveyorType == DEFAULT) conveyor.setType(toDirection);
		
		
		
		
		if (conveyorType == TO_RIGHT && toDirection == TO_LEFT) conveyor.setType(LEFT_TO_RIGHT);
		if (conveyorType == TO_TOP && toDirection == TO_LEFT) conveyor.setType(LEFT_TO_TOP);
		if (conveyorType == TO_BOTTOM && toDirection == TO_LEFT) conveyor.setType(LEFT_TO_BOTTOM);
		
		if (conveyorType == TO_LEFT && toDirection == TO_RIGHT) conveyor.setType(RIGHT_TO_LEFT);
		if (conveyorType == TO_TOP && toDirection == TO_RIGHT) conveyor.setType(RIGHT_TO_TOP);
		if (conveyorType == TO_BOTTOM && toDirection == TO_RIGHT) conveyor.setType(RIGHT_TO_BOTTOM);

		if (conveyorType == TO_LEFT && toDirection == TO_BOTTOM) conveyor.setType(BOTTOM_TO_LEFT);
		if (conveyorType == TO_RIGHT && toDirection == TO_BOTTOM) conveyor.setType(BOTTOM_TO_RIGHT);
		if (conveyorType == TO_TOP && toDirection == TO_BOTTOM) conveyor.setType(BOTTOM_TO_TOP);

		if (conveyorType == TO_LEFT && toDirection == TO_TOP) conveyor.setType(TOP_TO_LEFT);
		if (conveyorType == TO_RIGHT && toDirection == TO_TOP) conveyor.setType(TOP_TO_RIGHT);
		if (conveyorType == TO_BOTTOM && toDirection == TO_TOP) conveyor.setType(TOP_TO_BOTTOM);
	}
	
	private void checkDirection(int x, int y, int fromDirection, int toDirection, int oppositeFrom, int oppositeTo, int[] dir_from, int[] dir_to, Set<Integer> to, Set<Integer> from) {
		
		Tile tile = grid.getTile(gridX+x, gridY+y);
		MachineInterface machine = tile.getMachine();
		if (machine instanceof Conveyor) {
			Conveyor conveyor = (Conveyor) machine;
			int conveyorType = conveyor.getType();
						
			if (UNPLUGED.contains(conveyorType) && !from.contains(conveyorType)) {
				plugConveyor(conveyor, oppositeTo);
				conveyorType = conveyor.getType();
			}
			
			System.out.println(conveyorType);
			if (from.contains(conveyorType)) {
				System.out.println("to : "+toDirection);
				dir_to[toDirection] = 1;
			}
			if (to.contains(conveyorType)) {
				System.out.println("from : "+fromDirection);
				dir_from[fromDirection] = 1;
			}
		}
	}
	
	@Override
	public String getOptionName() {
		return "CONVEYOR";
	}

	@Override
	public Image getImage() {
		return new ImageIcon(getClass().getClassLoader().getResource(imgPath+"-"+type+".png")).getImage();
	}

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon(getClass().getClassLoader().getResource(iconPath));
	}

	@Override
	public MachineInterface newInstance(InfiniteGrid grid, int gridX, int gridY, int tileSize) {
		return new Conveyor(grid, gridX, gridY, tileSize);
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
