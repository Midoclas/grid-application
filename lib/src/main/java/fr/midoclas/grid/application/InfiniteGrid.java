package fr.midoclas.grid.application;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class InfiniteGrid {
	private Map<Point, Tile> tiles;

	public InfiniteGrid() {
		tiles = new HashMap<>();
	}

	public Tile getTile(int x, int y) {  
		Point point = new Point(x, y);
		Tile tile = tiles.get(point);
		if (tile == null) {
			return new Tile(null);
		}
		return tile;
	}

	public void setTile(int x, int y, Tile tile) {
		Point point = new Point(x, y);
		tiles.put(point, tile);
	}
	
	public void removeTile(int x, int y) {
		Point point = new Point(x, y);
		tiles.remove(point);
	}
}
