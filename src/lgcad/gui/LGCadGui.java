package lgcad.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LGCadGui extends JFrame{
	private static final long serialVersionUID = 1L;

	public GBreadboard gBreadboard;
	
	public LGCadGui(int width, int height){
		super("Logic Gate Simulator");
		setLayout(new BorderLayout());

		/* Create Panels */
		JPanel westPanel = new JPanel(new GridLayout(0, 1));
		gBreadboard = new GBreadboard(width,height);

		/* Add Panels to the Frame */
		add(westPanel, BorderLayout.WEST);
		add(gBreadboard, BorderLayout.CENTER);

		/* Populate the Panels created above */
		JButton bNewProject = new JButton("New Project");
		bNewProject.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				System.out.println("New Project");
			}
		});
		
		JButton bNewGate = new JButton("Add Gate");
		bNewGate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				new GatePicker(gBreadboard);
			}
		});
		
		JButton bSimulate = new JButton("Simulate");
		bSimulate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				LGCadGui.this.gBreadboard.updateSimulation();
			}
		});
		
		westPanel.add(bNewProject);
		westPanel.add(bNewGate);
		westPanel.add(bSimulate);

		/* Show the Frame */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		//this.setResizable(false);
		setVisible(true);
	}
}
