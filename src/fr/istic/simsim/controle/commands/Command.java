package fr.istic.simsim.controle.commands;

import java.io.Serializable;

public class Command implements Serializable {

    private MulticastCommand command;

    public Command(MulticastCommand command) {
        this.command = command;
    }

    public MulticastCommand getCommand() {
        return command;
    }
}
