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
    private Timer timer;
	
	public MachineController() {
        machines = new ArrayList<>();
        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAnimations();
            }
        });
        timer.start();
    }
	
	public void addMachine(MachineInterface machine, int x, int y) {
        machines.add(new MachineInterface());
    }

    public void renderMachines(Graphics g, int tileSize) {
        for (MachineInterface machine : machines) {
        	machine.render(g, machine.getX(), machine.getY(), tileSize);
        }
    }
	
	private void updateAnimations() {
        for (MachineInterface machine : machines) {
        	machine.updateAnimation();
        }
    }
}
