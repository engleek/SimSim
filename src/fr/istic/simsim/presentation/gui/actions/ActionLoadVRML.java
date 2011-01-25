package fr.istic.simsim.presentation.gui.actions;

import fr.istic.simsim.presentation.PSimSim;
import fr.istic.simsim.presentation.gui.GUI;
import fr.istic.simsim.presentation.gui.Menu;
import fr.istic.simsim.presentation.simulator.Simulator;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionLoadVRML extends AbstractAction {

    private static final long serialVersionUID = 1L;

    private final Menu parent;

    private final PSimSim presentation;

    public ActionLoadVRML(final Menu parent, PSimSim presentation) {
        super("Charger VRML…");

        this.parent = parent;
        this.presentation = presentation;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        final JFileChooser chooser = new JFileChooser();
        final int result = chooser.showOpenDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
            final String fileName = chooser.getSelectedFile().getPath();
            System.out.println("Fichier chargé : " + fileName);
            presentation.loadVRML(fileName);
        }
    }
}
