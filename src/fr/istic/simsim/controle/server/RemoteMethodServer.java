package fr.istic.simsim.controle.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

public interface RemoteMethodServer extends Remote {
    public UUID connect(String name) throws RemoteException;
    public void disconnect(UUID uuid) throws RemoteException;
    public Collection<String> getRoster() throws RemoteException;
}
