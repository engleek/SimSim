package fr.istic.simsim;

import fr.istic.simsim.controle.CSimSim;

import java.io.IOException;

public class SimSim {

    public SimSim(String name) throws IOException {
        new CSimSim(name);
    }

    public static void main(String[] args) throws IOException {
        String name = "SimSim";

        if (args.length > 0) {
            name = args[0];
        }

        new SimSim(name);
    }
}
