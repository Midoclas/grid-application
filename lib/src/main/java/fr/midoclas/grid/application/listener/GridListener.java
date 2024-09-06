package fr.midoclas.grid.application.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

import fr.midoclas.grid.application.GridView;

public class GridListener implements MouseListener, MouseMotionListener, ActionListener {

	private final GridView gridView;

	public GridListener(GridView gridView) {
		this.gridView = gridView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			gridView.handleTileClick(e.getX(), e.getY());
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			gridView.removeTileClick(e.getX(), e.getY());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			if (this.gridView.lastMousePosition != null) {
				int deltaX = e.getX() - this.gridView.lastMousePosition.x;
				int deltaY = e.getY() - this.gridView.lastMousePosition.y;
				gridView.dragGrid(deltaX, deltaY);
			}
			this.gridView.lastMousePosition = e.getPoint();
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.gridView.lastMousePosition = e.getPoint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gridView.repaint();
	}
}
