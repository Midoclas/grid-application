package fr.midoclas.grid.application.machine;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.Timer;

import fr.midoclas.grid.application.InfiniteGrid;
import fr.midoclas.grid.application.Point;

public class MachineController {
	
	private List<MachineInterface> machines;
	private InfiniteGrid grid;
    private Timer timer;
    private int offsetX = 0, offsetY = 0, tileSize = 0;

	public MachineController(InfiniteGrid grid) {
		this.grid = grid;
        machines = new ArrayList<>();
        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAnimations();
            }
        });
        timer.start();
    }
	
	public void addMachine(MachineInterface machine) {
        machines.add(machine);
    }

    public void renderMachines(Graphics g) {
    	
        for (MachineInterface machine : machines) {
        	machine.render(g, this.offsetX, this.offsetY, this.tileSize);
        }
    }
	
	private void updateAnimations() {
        for (MachineInterface machine : machines) {
        	machine.updateAnimation();
        }
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

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}
}
