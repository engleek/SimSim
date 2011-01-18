package fr.istic.simsim.presentation.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Infobox extends JPanel {

    private static final long serialVersionUID = 1L;

    private JSplitPane splitPane;

    private JTree entityList;
    private JList clientList;

    private Vector<String> clients = new Vector<String>();

    public Infobox() {
        super(new GridLayout(1, 0));

        setSize(200, 600);
        setPreferredSize(getSize());

        entityList = new JTree();
        clientList = new JList(clients);

        clients.add("You");

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, entityList, clientList);
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(0.5);

        entityList.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Scene"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        clientList.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Clients"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        add(splitPane);
    }
}
