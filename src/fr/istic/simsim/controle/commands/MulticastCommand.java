package fr.istic.simsim.controle.commands;

import fr.istic.simsim.controle.SimSimControl;

import java.io.Serializable;

public interface MulticastCommand extends Serializable {
    public void execute(SimSimControl control);
}
