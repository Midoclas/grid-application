package fr.midoclas.grid.application.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.function.Supplier;

import fr.midoclas.grid.application.GridView;
import fr.midoclas.grid.application.component.MachineIconButton;
import fr.midoclas.grid.application.machine.MachineInterface;

public class MachineIconButtonListener implements ActionListener, FocusListener {

	private final GridView gridView;
	private final MachineInterface machine;
	private final MachineIconButton button;

	public MachineIconButtonListener(MachineIconButton button, MachineInterface machine, GridView gridView) {
		this.button = button;
		this.machine = machine;
		this.gridView = gridView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gridView.setMachineSelected(machine);
	}

	@Override
	public void focusGained(FocusEvent e) {
		button.focus();
	}

	@Override
	public void focusLost(FocusEvent e) {
		button.unFocus();
	}

}
