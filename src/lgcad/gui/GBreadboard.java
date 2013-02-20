package lgcad.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import lgcad.model.Breadboard;
import lgcad.model.Pin;
import lgcad.model.Socket;
import lgcad.model.Gate.Type;


public class GBreadboard extends JPanel {
	private static final long serialVersionUID = 1L;

	Breadboard mBreadboard;
	
	public GBreadboard(int width, int height){
		super();
		mBreadboard = new Breadboard(width, height);
		
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension((getColumnCount()-1)*200+30, (getRowCount())*50+30));
		setLayout(null);
		
		for (int i = 0; i<width; i++) {
			if (i<width-1){
				JButton bNewGate = new JButton("Add Gate");
				bNewGate.addActionListener(new AddGateListener(i));
				
				bNewGate.setLocation(i*200+70, 10);
				bNewGate.setSize(100, 20);
				add(bNewGate);
			}
			
			boolean editable = i==0? true: false;
			
			for (int j = 0; j<height; j++) {
				GPin gPin = new GPin(this, mBreadboard.pins[i][j], editable);
				gPin.setLocation(getPinLocation(i,j));
				add(gPin);
			}
		}
	}

	public void addSocket(Type type, Integer col) {
		try {
			int i=col;
			int j= mBreadboard.RowCounter[col];
			mBreadboard.RowCounter[col]++;
			
			mBreadboard.sockets[i][j] = new Socket(this.mBreadboard, i, j, type);
			GSocket gSocket = new GSocket(mBreadboard.sockets[i][j], this);
			
			gSocket.setLocation(getSocketLocation(i,j));
			add(gSocket);
			repaint();
		}
		catch (Exception exc){
			System.out.println("Trying to add more Gates than the breadboard can fit!!!");
		}
	}

	public int getRowCount(){
		return mBreadboard.pins[0].length;
	}

	public int getColumnCount(){
		return mBreadboard.pins.length;
	}

	protected Point getPinLocation(int i, int j) {
		return new Point(i*200+10, (j+1)*50);
	}
	
	protected Point getSocketLocation(int i, int j) {
		return new Point(i*200+85, (j+1)*50 + 10);
	}

	public void paint(Graphics g){
		super.paint(g);
		
		if (mBreadboard.sockets != null){
			for (int x=0; x<getColumnCount()-1; x++){
				for (int y=0; y<getRowCount()-1; y++){
					if (mBreadboard.sockets[x][y] != null){
						Socket sock = mBreadboard.sockets[x][y];
						for (int row: sock.inputPinNumbers){
							int x1 = getPinLocation(sock.column, row).x+10;
							int y1 = getPinLocation(sock.column, row).y+4;
							int x2 = getSocketLocation(sock.column, sock.row).x;
							int y2 = getSocketLocation(sock.column, sock.row).y+20;
							
							g.drawLine(x1, y1, x2, y2);
						}
					}
				}
			}
			
			for (int x=1; x<getColumnCount(); x++){
				for (int y=0; y<getRowCount(); y++){
					if (mBreadboard.pins[x][y] != null){
						Pin pin = mBreadboard.pins[x][y];
						Socket sock;
						if ((sock = pin.getSourceSocket()) != null){
							int x1 = getPinLocation(x, y).x;
							int y1 = getPinLocation(x, y).y+4;
							int x2 = getSocketLocation(sock.column, sock.row).x+50;
							int y2 = getSocketLocation(sock.column, sock.row).y+20;
							
							g.drawLine(x1, y1, x2, y2);
						}
					}
				}
			}
		}
		
	}
	
	public void doSimulation(){
		for (int x=1; x<getColumnCount(); x++){
			for (int y=0; y<getRowCount(); y++){
				if (mBreadboard.pins[x][y] != null){
					Pin pin = mBreadboard.pins[x][y];
					pin.updateValueFromSource();
				}
			}
		}
		
		repaint();
	}
	
	class AddGateListener implements ActionListener {
		int column;
		
		public AddGateListener(int col){
			column = col;
		}
		
		public void actionPerformed(ActionEvent e) {
			new GatePicker(GBreadboard.this, column);
		}
		
	}
}
