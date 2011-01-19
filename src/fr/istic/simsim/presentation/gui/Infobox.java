package fr.istic.simsim.presentation.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Infobox extends JPanel {

    private static final long serialVersionUID = 1L;

    private JSplitPane splitPane;

    private DefaultListModel clients;

    private JTree entityList;
    private JList clientList;

    public Infobox() {
        super(new GridLayout(1, 0));

        setSize(200, 600);
        setPreferredSize(getSize());

        clients = new DefaultListModel();

        entityList = new JTree();
        clientList = new JList(clients);

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

    public void addClient(String name) {
        clients.addElement(name);
    }
}
