package fr.istic.simsim.controle.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteMethodServer extends Remote {
    public void connect(String name) throws RemoteException;
    public void disconnect() throws RemoteException;
}
