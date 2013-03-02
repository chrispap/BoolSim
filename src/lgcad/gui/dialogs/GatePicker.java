package lgcad.gui.dialogs;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import lgcad.gui.GSocket;
import lgcad.model.Gate;

public class GatePicker extends JDialog {
    private static final long serialVersionUID = 1L;

    JComboBox<Gate.Type> comboGate;
    GSocket gSocket;

    public GatePicker(GSocket sock) {
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        this.gSocket = sock;
        JPanel panel;
        add(panel = new JPanel());
        comboGate = new JComboBox<Gate.Type>(Gate.Type.values());
        panel.add(comboGate);
        JButton bOk = new JButton("OK");
        bOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                gSocket.setGateType((lgcad.model.Gate.Type) comboGate.getSelectedItem());
                setVisible(false);
                gSocket.getParentBreadboard().updateSimulation();
            }
        });
        panel.add(bOk);

        setLocationRelativeTo(gSocket.getParentBreadboard());
        pack();
        setResizable(false);
        setVisible(true);
    }

}
