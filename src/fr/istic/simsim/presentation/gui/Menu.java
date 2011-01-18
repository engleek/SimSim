package fr.istic.simsim.presentation.gui;

import fr.istic.simsim.presentation.gui.actions.ActionLoadVRML;
import fr.istic.simsim.presentation.gui.actions.ActionQuitter;
import fr.istic.simsim.presentation.simulator.Simulator;

import javax.swing.*;

public class Menu extends JMenuBar {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Menu(final Simulator simulator) {
        // création des items
        final JMenuItem load = new JMenuItem(new ActionLoadVRML(this, simulator));
        final JMenuItem exit = new JMenuItem(new ActionQuitter());

        // menu Simulation
        final JMenu file = new JMenu("Simulation");
        file.add(load);
        file.addSeparator();
        file.add(exit);

        // menu bar
        add(file);
    }
}
