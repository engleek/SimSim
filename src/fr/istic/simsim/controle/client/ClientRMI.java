package fr.istic.simsim.controle.client;

import fr.istic.simsim.Config;
import fr.istic.simsim.controle.server.RemoteMethodServer;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class ClientRMI {

    private RemoteMethodServer server;

    public ClientRMI() {
        try {
            server = (RemoteMethodServer) Naming.lookup("//" + Config.rmiHost + ":" + Config.rmiPort + "/" + Config.serviceName);
            Config.log("", "RMI Client ready");
        } catch (Exception e) {
            System.err.println("RMI Exception : " + e.getMessage());
        }
    }

    public void connect(String name) {
        Config.log("RMI", "Connect");

        try {
            server.connect(name);
        } catch (Exception e) {
            System.err.println("RMI connect exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<String> getRoster() {
        ArrayList<String> roster = new ArrayList<String>();

        try {
            roster = (ArrayList<String>) server.getRoster();
        } catch (Exception e) {
            System.err.println("RMI getRoster exception: " + e.getMessage());
        }

        return roster;
    }
}
