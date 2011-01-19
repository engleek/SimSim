package fr.istic.simsim.controle.client;

import fr.istic.simsim.Config;
import fr.istic.simsim.SimSim;
import fr.istic.simsim.controle.server.RemoteMethodServer;

import java.rmi.Naming;
import java.rmi.RemoteException;

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

    public void connect(String name) throws RemoteException {
        Config.log("RMI", "Connect");

        try {
            server.connect(name);
        } catch (Exception e) {
            System.err.println("RMI Connect exception: " + e.getMessage());
        }
    }
}
