package fr.istic.simsim.presentation;

import fr.istic.simsim.controle.CSimSim;
import fr.istic.simsim.presentation.gui.GUI;
import fr.istic.simsim.presentation.simulator.Simulator;

public class PSimSim {

    private final CSimSim controle;

    private final GUI       gui;
    private final Simulator simulator;

    public PSimSim(CSimSim controle) {
        this.controle = controle;

        simulator = new Simulator();
        gui = new GUI(simulator);
    }

    public void addClient(String name) {
        gui.addClient(name);
    }
}
