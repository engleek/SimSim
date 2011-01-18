package fr.istic.simsim.presentation.gui.actions;

import fr.istic.simsim.presentation.gui.Menu;
import fr.istic.simsim.presentation.simulator.Simulator;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionLoadVRML extends AbstractAction {

    private static final long serialVersionUID = 1L;

    private final Menu parent;

    private final Simulator simulator;

    public ActionLoadVRML(final Menu parent, Simulator simulator) {
        super("Charger VRML…");

        this.parent = parent;
        this.simulator = simulator;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        final JFileChooser chooser = new JFileChooser();
        final int result = chooser.showOpenDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
            final String fileName = chooser.getSelectedFile().getPath();
            System.out.println("Fichier chargé : " + fileName);
            simulator.loadVRML(fileName);
        }
    }
}
