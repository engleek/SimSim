package fr.istic.simsim.controle.commands;

import fr.istic.simsim.controle.SimSimControl;

public class RefreshRoster implements MulticastCommand {
    @Override
    public void execute(SimSimControl control) {
        control.getRoster();
    }
}
