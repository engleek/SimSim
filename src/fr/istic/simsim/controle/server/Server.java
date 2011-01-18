package fr.istic.simsim.controle.server;

import fr.istic.simsim.Config;
import fr.istic.simsim.controle.CSimSimServer;
import fr.istic.simsim.controle.Message;
import fr.istic.simsim.controle.client.MulticastInterface;

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

public class Server extends UnicastRemoteObject implements MulticastInterface, RemoteMethodServer {

    private CSimSimServer control;

    private MulticastSocket socket;

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

    public void broadcastMessage(Message message) {
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(buffer);

            out.writeObject(message);
            out.flush();

            DatagramPacket packet = new DatagramPacket(buffer.toByteArray(), buffer.toByteArray().length, InetAddress.getByName(Config.multicastHost), Config.multicastPort);

            socket.send(packet);

            Config.log("Broadcast", message.getCommand().name());
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
    public void connect() throws RemoteException {
        Config.log("RMI", "Connect");
        broadcastMessage(new Message(MulticastCommand.connect));
    }

    @Override
    public void disconnect() throws RemoteException {
        Config.log("RMI", "Disconnect");
        broadcastMessage(new Message(MulticastCommand.disconnect));
    }
}