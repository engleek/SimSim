package fr.istic.simsim.controle;

import fr.istic.simsim.controle.client.MulticastInterface.MulticastCommand;

import java.io.Serializable;

public class Message implements Serializable {

    private MulticastCommand command;

    public Message(MulticastCommand command) {
        this.command = command;
    }

    public MulticastCommand getCommand() {
        return command;
    }
}
