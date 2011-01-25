package fr.istic.simsim.controle.client;

import fr.istic.simsim.Config;
import fr.istic.simsim.controle.CSimSim;
import fr.istic.simsim.controle.commands.MulticastCommand;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ClientMulticastThread extends Thread implements MulticastInterface {

    private CSimSim control;

    private boolean running = true;

    private MulticastSocket socket;
    private DatagramPacket  packet;
    private byte[]          buffer;

    private int packetCount = 0;

    public ClientMulticastThread(CSimSim controle) throws IOException {
        this("SimSimServerThread", controle);
    }

    public ClientMulticastThread(String name, CSimSim control) throws IOException {
        super(name);

        this.control = control;

        socket = new MulticastSocket(Config.multicastPort);
        socket.joinGroup(InetAddress.getByName(Config.multicastHost));

        Config.log("", "Multicast Client ready");
    }

    @Override
    public void run() {

        while (running) {
            Config.log("", "…");
            buffer = new byte[1024];
            packet = new DatagramPacket(buffer, 1024);
            waitForMessage();
        }

        socket.close();
    }

    public void end() {
        running = false;
    }

    private void waitForMessage() {
        try {
            socket.receive(packet);
            packetCount++;
            unserializeCommand(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void unserializeCommand(DatagramPacket packet) {
        ByteArrayInputStream buffer = new ByteArrayInputStream(packet.getData());

        try {
            ObjectInputStream inputStream = new ObjectInputStream(buffer);

            MulticastCommand command = (MulticastCommand) inputStream.readObject();
            command.execute(control);

            Config.log("Socket[" + packetCount + "]", command.getClass().getName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
