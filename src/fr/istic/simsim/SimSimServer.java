package fr.istic.simsim;

import fr.istic.simsim.controle.CSimSimServer;

import java.io.IOException;

public class SimSimServer {

    public SimSimServer(String name) throws IOException {
        new CSimSimServer(name);
    }

    public static void main(String[] args) throws IOException {
        String name = "SimSimServer";

        if (args.length > 0) {
            name = args[0];
        }

        new SimSimServer(name);
    }
}
