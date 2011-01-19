package fr.istic.simsim.controle;

import fr.istic.simsim.Config;
import fr.istic.simsim.abstraction.ASimSim;
import fr.istic.simsim.controle.server.Server;

import java.io.IOException;

public class CSimSimServer implements SimSimControl {

    Server networkInterface;

    private ASimSim abstraction;

    private String name;

    public CSimSimServer(String name) throws IOException {

        this.name = name;

        networkInterface = new Server(this);
        abstraction = new ASimSim(this);
    }

    @Override
    public void echo(String message) {
        Config.log("MSG", message);
    }
    
    @Override
    public void addClient(String name) {
        // TODO: Find a use for this on server side
    }
}
