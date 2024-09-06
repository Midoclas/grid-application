package fr.midoclas.grid.application;

import fr.midoclas.grid.application.machine.MachineInterface;

public class Tile {
	private MachineInterface machine = null;

	public Tile(MachineInterface machine) {
		this.machine = machine;
	}

	public MachineInterface getMachine() {
		return machine;
	}

	public void setMachine(MachineInterface machine) {
		this.machine = machine;
	}
}
