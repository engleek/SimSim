package fr.istic.simsim.controle;

import fr.istic.simsim.Config;
import fr.istic.simsim.abstraction.ASimSim;
import fr.istic.simsim.controle.client.ClientMulticastThread;
import fr.istic.simsim.controle.client.ClientRMI;
import fr.istic.simsim.presentation.PSimSim;

import java.io.IOException;

public class CSimSim implements SimSimControl {

    ClientMulticastThread networkInterface;
    ClientRMI             clientRMI;

    private ASimSim abstraction;
    private PSimSim presentation;

    private String name;

    public CSimSim(String name) throws IOException {

        this.name = name;

        networkInterface = new ClientMulticastThread(this);
        clientRMI = new ClientRMI();
        abstraction = new ASimSim(this);
        presentation = new PSimSim(this);

        networkInterface.start();
        clientRMI.connect(name);
    }

    @Override
    public void echo(String message) {
        Config.log("MSG", message);
    }

    @Override
    public void addClient(String name) {
        if (name.equals(this.name)) {
            presentation.addClient(name + " (you)");
        } else {
            presentation.addClient(name);
        }
    }
}
