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
        networkInterface.start();

        ClientRMI clientRMI = new ClientRMI();
        clientRMI.connect();

        abstraction = new ASimSim(this);

        presentation = new PSimSim(this);
    }

    public void echo(String message) {
        Config.log("MSG", message);
    }
}
