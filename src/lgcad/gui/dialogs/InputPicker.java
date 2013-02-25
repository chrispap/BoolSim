package lgcad.gui.dialogs;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lgcad.gui.GSocket;

public class InputPicker extends JDialog {
    private static final long serialVersionUID = 1L;

    JComboBox input1, input2, output;
    GSocket gSocket;

    public InputPicker(GSocket gate) {
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        gSocket = gate;
        JPanel panel;
        add(panel = new JPanel());

        int rows = gSocket.getParentBreadboard().getRowCount();
        Integer[] pinNumbers = new Integer[rows];
        for (int i = 0; i < rows; i++)
            pinNumbers[i] = i + 1;

        JButton bOk = new JButton("OK");
        bOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                gSocket.makeConnections((Integer) input1.getSelectedItem() - 1, (Integer) input2.getSelectedItem() - 1,
                        (Integer) output.getSelectedItem() - 1);
                setVisible(false);
            }
        });

        panel.add(new JLabel("Input Pin 1"));
        panel.add(input1 = new JComboBox(pinNumbers));
        panel.add(new JLabel("Input Pin 2"));
        panel.add(input2 = new JComboBox(pinNumbers));
        input2.setSelectedIndex(1);
        panel.add(new JLabel("Output pin"));
        panel.add(output = new JComboBox(pinNumbers));
        panel.add(bOk);

        setLocationRelativeTo(gSocket.getParentBreadboard());
        pack();
        setResizable(false);
        setVisible(true);
    }

}
