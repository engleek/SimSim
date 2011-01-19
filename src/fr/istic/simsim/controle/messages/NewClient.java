package fr.istic.simsim.controle.messages;

import fr.istic.simsim.controle.SimSimControl;
import fr.istic.simsim.controle.client.MulticastInterface;

public class NewClient implements MulticastCommand {

    private String name;

    public NewClient(String name) {
        this.name = name;
    }

    @Override
    public void execute(SimSimControl control) {
        control.addClient(name);
    }
}
