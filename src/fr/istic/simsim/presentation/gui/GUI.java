package fr.istic.simsim.presentation.gui;

import fr.istic.simsim.presentation.simulator.Simulator;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class GUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private Simulator simulator;
    private Controls  controls;
    private Infobox   infobox;
    private StatusBar statusBar;

    private Menu menu;

    public GUI(final Simulator simulator) {
        super("Sim");

        setLayout(new BorderLayout());

        controls = new Controls();
        infobox = new Infobox();
        menu = new Menu(simulator);
        statusBar = new StatusBar();

        add(simulator, BorderLayout.CENTER);
        add(controls, BorderLayout.WEST);
        add(infobox, BorderLayout.EAST);
        add(statusBar, BorderLayout.SOUTH);

        setJMenuBar(menu);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(900, 600);
        setPreferredSize(getSize());
        setVisible(true);
    }

    public void addClient(String name) {
        infobox.addClient(name);
    }

    public void setRoster(Collection<String> roster) {
        infobox.setRoster(roster);
    }
}
