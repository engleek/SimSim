package fr.istic.simsim.controle.client;

import fr.istic.simsim.SimSim;
import fr.istic.simsim.controle.server.RemoteMethodServer;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class ClientRMI {

    private RemoteMethodServer server;

    public ClientRMI() {
        try {
            server = (RemoteMethodServer) Naming.lookup("//" + SimSim.rmiHost + ":" + SimSim.rmiPort + "/" + SimSim.serviceName);
            SimSim.log("", "RMI Client ready");
        } catch (Exception e) {
            System.err.println("RMI Exception : " + e.getMessage());
        }
    }

    public void connect() throws RemoteException {
        SimSim.log("RMI", "Connect");
        try {
            server.connect();
        } catch (Exception e) {
            System.err.println("RMI Connect exception: " + e.getMessage());
        }
    }
}
