package fr.istic.simsim.controle.client;

import fr.istic.simsim.SimSim;
import fr.istic.simsim.controle.CSimSim;
import fr.istic.simsim.controle.Message;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ClientMulticastThread extends Thread implements MulticastInterface {

    private CSimSim controle;

    private boolean running = true;

    private MulticastSocket socket;

    public ClientMulticastThread(CSimSim controle) throws IOException {
        this("SimSimServerThread", controle);
    }

    public ClientMulticastThread(String name, CSimSim controle) throws IOException {
        super(name);

        this.controle = controle;

        socket = new MulticastSocket(SimSim.multicastPort);
        socket.joinGroup(InetAddress.getByName(SimSim.multicastHost));

        SimSim.log("", "Multicast Client ready");
    }

    @Override
    public void run() {
        while (running) {
            SimSim.log("", "…");
            waitForMessage();
        }

        socket.close();
    }

    public void end() {
        running = false;
    }

    private void waitForMessage() {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, 1024);

        try {
            socket.receive(packet);
            unserializeMessage(packet);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void unserializeMessage(DatagramPacket packet) {
        ByteArrayInputStream buffer = new ByteArrayInputStream(packet.getData());

        try {
            ObjectInputStream inputStream = new ObjectInputStream(buffer);

            Message message = (Message) inputStream.readObject();
            SimSim.log("Socket", message.getCommand().name());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
