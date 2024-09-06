package fr.midoclas.grid.application;

import javax.swing.*;

import fr.midoclas.grid.application.machine.MachineSelectionBar;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Application {
	private GridView gridView;
	private MachineSelectionBar machineSelectionBar;

	public Application() {
		gridView = new GridView(new InfiniteGrid());
		machineSelectionBar = new MachineSelectionBar(gridView);
	}

	public void start() {
		JFrame frame = new JFrame("Infinite Grid");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(gridView, BorderLayout.CENTER);
		mainPanel.add(machineSelectionBar, BorderLayout.SOUTH);
		
	    frame.add(mainPanel);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Application().start());
	}
}
