package lgcad.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import lgcad.model.Gate.Type;


public class GatePicker extends JFrame {
	private static final long serialVersionUID = 1L;

	JComboBox comboColumn, comboGate;
	GBreadboard parentBreadboard;
	int column;
	
	public GatePicker(GBreadboard parentBreadboard){
		this(parentBreadboard, -1);
	}
	
	public GatePicker(GBreadboard parent, int col){
		this.parentBreadboard = parent;
		this.column = col;
		JPanel panel;
		add(panel = new JPanel());
		
		comboGate = new JComboBox(Type.values());
		panel.add(comboGate);
		
		if (column < 0){
			int cols = parentBreadboard.getColumnCount()-1;
			Integer[] columnNumbers = new Integer[cols];
			for(int i=0; i<cols; i++) columnNumbers[i] = i;
			comboColumn = new JComboBox(columnNumbers);
			panel.add (comboColumn);
		}
		
		JButton bOk = new JButton("OK");
		bOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				if (column>=0)
					parentBreadboard.addSocket((Type)comboGate.getSelectedItem(), column);
				else
					parentBreadboard.addSocket((Type)comboGate.getSelectedItem(), (Integer) comboColumn.getSelectedItem());
				
				setVisible(false);
			}
		});
		panel.add(bOk);

		setLocationRelativeTo(parent);
		pack();
		setResizable(false);
		setVisible(true);
	}

}
