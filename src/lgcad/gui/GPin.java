package lgcad.gui;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import lgcad.model.Pin;

public class GPin extends JComponent {
	private static final long serialVersionUID = 1L;
	
	private static final int size=12;
	protected GBreadboard gBreadboard;
	protected Pin pin;

	public GPin(GBreadboard gb, Pin pin, boolean editable){
		this.pin = pin;
		this.gBreadboard = gb;
		setSize(size, size);

		if (editable){
			addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent evt) {
					GPin.this.pin.toggleValue();
					gBreadboard.doSimulation();
					GPin.this.repaint();
				}
			});
		}
	}

	public void paint(Graphics g_) {
		super.paint(g_);
		Graphics2D g = (Graphics2D) g_;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(pin.getValue()? Color.YELLOW: Color.BLACK);
		g.fillOval(0, 0, 10, 10);
	}

}