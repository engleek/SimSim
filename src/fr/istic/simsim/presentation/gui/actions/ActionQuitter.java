package fr.istic.simsim.presentation.gui.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionQuitter extends AbstractAction {

    private static final long serialVersionUID = 1L;

    public ActionQuitter() {
        super("Quitter");
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        System.exit(0);
    }
}
