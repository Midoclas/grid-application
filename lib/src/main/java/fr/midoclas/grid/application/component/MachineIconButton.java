package fr.midoclas.grid.application.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.UIManager;

import fr.midoclas.grid.application.GridView;
import fr.midoclas.grid.application.listener.MachineIconButtonListener;
import fr.midoclas.grid.application.machine.MachineInterface;

public class MachineIconButton extends JButton {
	
	private MachineIconButtonListener listener;
	
	public MachineIconButton(MachineInterface machine, GridView gridView) {
		this.setActionCommand(machine.getOptionName());
		this.setIcon(machine.getIcon());
		
		this.listener = new MachineIconButtonListener(this, machine, gridView);
		
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setBorder(BorderFactory.createLineBorder(Color.PINK, 5));
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(80, 80));
		// button.setToolTipText(command);
		this.addActionListener(listener);
		this.addFocusListener(listener);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        // Redéfinir l'état pressé pour qu'il ne change pas de couleur de fond
        if (getModel().isPressed()) {
            setBackground(new Color(0, 0, 0, 0));
        } else {

        }
        super.paintComponent(g);
    }
	
	public void focus() {
		this.setBorderPainted(true);
		this.repaint();
    }

    public void unFocus() {
    	this.setBorderPainted(false);
    	this.repaint();
    }

}
