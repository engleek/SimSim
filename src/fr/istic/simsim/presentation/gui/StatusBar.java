package fr.istic.simsim.presentation.gui;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JLabel {
    public StatusBar() {
        super();

        setPreferredSize(new Dimension(100, 16));
        setMessage("Ready");
    }

    public void setMessage(String message) {
        setText(" " + message);
    }
}
