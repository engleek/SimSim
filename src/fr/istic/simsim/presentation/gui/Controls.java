package fr.istic.simsim.presentation.gui;

import fr.istic.simsim.presentation.gui.actions.ActionArriere;
import fr.istic.simsim.presentation.gui.actions.ActionAvant;
import fr.istic.simsim.presentation.gui.actions.ActionDroite;
import fr.istic.simsim.presentation.gui.actions.ActionGauche;

import javax.swing.*;

public class Controls extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JButton boutonAvant, boutonArriere, boutonGauche,
            boutonDroite;
    AbstractAction actionAvant, actionArriere, actionGauche,
            actionDroite;

    public Controls() {
        // Layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Actions
        actionAvant = new ActionAvant();
        actionArriere = new ActionArriere();
        actionGauche = new ActionGauche();
        actionDroite = new ActionDroite();

        // Boutons
        boutonAvant = new JButton(actionAvant);
        boutonArriere = new JButton(actionArriere);
        boutonGauche = new JButton(actionGauche);
        boutonDroite = new JButton(actionDroite);

        add(boutonAvant);
        add(boutonArriere);
        add(boutonGauche);
        add(boutonDroite);
    }
}
