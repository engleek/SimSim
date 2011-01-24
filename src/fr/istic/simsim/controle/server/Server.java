package fr.istic.simsim.controle.server;

import fr.istic.simsim.Config;
import fr.istic.simsim.controle.CSimSimServer;
import fr.istic.simsim.controle.client.MulticastInterface;
import fr.istic.simsim.controle.commands.MulticastCommand;
import fr.istic.simsim.controle.commands.RefreshRoster;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class Server extends UnicastRemoteObject implements MulticastInterface, RemoteMethodServer {

    private CSimSimServer control;

    private MulticastSocket   socket;

    private HashMap<UUID, String> roster = new HashMap<UUID, String>();

    public Server(CSimSimServer control) throws IOException {
        this(control, "SimSimServerThread");
    }

    public Server(CSimSimServer control, String name) throws IOException {
        super();

        this.control = control;

        // Setup Multicast
        try {
            socket = new MulticastSocket();
            socket.joinGroup(InetAddress.getByName(Config.multicastHost));

            Config.log("", "Multicast Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }

        // Setup RMI Server
        try {
            LocateRegistry.createRegistry(Config.rmiPort);
            Naming.rebind("//" + Config.rmiHost + ":" + Config.rmiPort + "/" + Config.serviceName, this);
            Config.log("", "RMI Server ready");
        } catch (Exception e) {
            System.err.println("RMI Server exception: " + e);
        }
    }

    /**
     * Multicast Server Methods
     */

    public void broadcastCommand(MulticastCommand command) {
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(buffer);

            out.writeObject(command);
            out.flush();

            DatagramPacket packet = new DatagramPacket(buffer.toByteArray(), buffer.toByteArray().length, InetAddress.getByName(Config.multicastHost), Config.multicastPort);

            socket.send(packet);

            Config.log("Broadcast", command.getClass().getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void end() {
        socket.close();
    }

    /**
     * RMI Server Methods
     */

    @Override
    public UUID connect(String name) throws RemoteException {
        Config.log("RMI", "Connect");

        UUID uuid = UUID.randomUUID();

        roster.put(uuid, name);
        broadcastCommand(new RefreshRoster());

        return uuid;
    }

    @Override
    public ArrayList<String> getRoster() {
        return new ArrayList<String>(roster.values());
    }

    @Override
    public void disconnect(UUID uuid) throws RemoteException {
        Config.log("RMI", "Disconnect");

        roster.remove(uuid);
        broadcastCommand(new RefreshRoster());
    }
}