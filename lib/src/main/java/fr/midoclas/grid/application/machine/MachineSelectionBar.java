package fr.midoclas.grid.application.machine;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import fr.midoclas.grid.application.GridView;
import fr.midoclas.grid.application.component.MachineIconButton;

public class MachineSelectionBar extends JPanel {

	private Map<String, MachineInterface> machineMap = new HashMap<>();

	public MachineSelectionBar(GridView grid) {
		this.initMachine();
		this.initStyle();

		for (MachineInterface machine : machineMap.values()) {
			MachineIconButton button = new MachineIconButton(machine, grid);
			add(button);
		}
		
		Object firstKey = machineMap.keySet().toArray()[0];
		grid.setMachineSelected(machineMap.get(firstKey));

		if (!machineMap.isEmpty()) {
			((MachineIconButton) getComponent(0)).setSelected(true);
		}
	}

	private void initMachine() {
//		machineMap.put("DRILL", new Drill());
//		machineMap.put("SMELTER", new Smelter());
		machineMap.put("CONVOYER", new Conveyor());
	}
	
	private void initStyle() {
		this.setOpaque(false);
		this.setBackground(new Color(0, 0, 0, 0));
		this.setForeground(new Color(0, 0, 0, 0));
		this.repaint();
	}
}

