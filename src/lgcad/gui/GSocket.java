package lgcad.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import lgcad.model.Socket;

class GSocket extends JComponent{
	private static final long serialVersionUID = 1L;

	protected GBreadboard parentBreadboard;
	protected Socket socket;
	
	public GSocket(Socket socket, GBreadboard parent){
		this.socket = socket;
		this.parentBreadboard = parent;
		
		addMouseListener(new GateClickListener());
		setSize(51, 41);
	}

	public void paint(Graphics g_) {
		super.paint(g_);
		Graphics2D g = (Graphics2D) g_;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.BLACK);
		switch (socket.gate.type) {
			case And: 
				g.drawLine(0, 0, 30, 0);
				g.drawLine(0, 0, 0, 40);
				g.drawLine(0, 40, 30, 40);
				g.drawArc(10, 0, 40, 40, 270, 180);
				break;
			case Or:
				g.drawArc(-10, 0, 20, 40, -90, 180);
				g.drawLine(0, 0, 20, 0);
				g.drawLine(0, 40, 20, 40);
				g.drawArc(-10, 0, 60, 40, 270, 180);
				break;
			case Not: 		
				g.drawLine(0, 0, 0, 40);
				g.drawLine(0, 40, 43, 20);
				g.drawLine(0, 0, 43, 20);
				g.drawOval(43, 17, 7, 7);
				break;
			case Nand: 
				g.drawLine(0, 0, 21, 0);
				g.drawLine(0, 0, 0, 40);
				g.drawLine(0, 40, 21, 40);
				g.drawArc(2, 0, 40, 40, 270, 180);
				g.drawOval(43, 17, 7, 7);
				break;
			case Nor: 
				g.drawArc(-10, 0, 20, 40, -90, 180);
				g.drawLine(0, 0, 15, 0);
				g.drawLine(0, 40, 15, 40);
				g.drawArc(-10, 0, 52, 40, 270, 180);
				g.drawOval(43, 17, 7, 7);
				break;
			case Xor: 
				g.drawArc(-10, 0, 20, 40, -90, 180);
				g.drawArc(-5, 0, 20, 40, -90, 180);
				g.drawLine(8, 0, 15, 0);
				g.drawLine(8, 40, 15, 40);
				g.drawArc(-10, 0, 52, 40, 270, 180);
				g.drawOval(43, 17, 7, 7);
				break;
		}
		
	}

	class GateClickListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			new InputPicker(GSocket.this);
			super.mouseClicked(e);
		}
	}

	public void makeConnections(Integer pinNumber1, Integer pinNumber2, Integer outPinNumber) {
		socket.inputPinNumbers.clear();
		socket.inputPinNumbers.add(pinNumber1);
		socket.inputPinNumbers.add(pinNumber2);

		if (socket.outputPin !=null)
			socket.outputPin.setSource(null);
		
		try{
			socket.parentBreadBoard.pins[socket.column+1][outPinNumber].getSourceSocket().outputPin= null;
		} catch (Exception exc){
			
		}
		
		socket.outputPin = socket.parentBreadBoard.pins[socket.column+1][outPinNumber];
		socket.parentBreadBoard.pins[socket.column+1][outPinNumber].setSource(socket);
		
		parentBreadboard.repaint();
	}
	
}