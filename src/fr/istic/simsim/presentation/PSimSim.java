package fr.istic.simsim.presentation;

import fr.istic.simsim.controle.CSimSim;
import fr.istic.simsim.presentation.gui.GUI;
import fr.istic.simsim.presentation.simulator.Simulator;
import fr.istic.simsim.presentation.simulator.objects.VirtualCube;
import fr.istic.simsim.presentation.simulator.objects.VirtualObject;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.vecmath.Matrix4f;
import java.util.Collection;

public class PSimSim {

    private final CSimSim controle;

    private final GUI       gui;
    private final Simulator simulator;

    public PSimSim(CSimSim controle) {
        this.controle = controle;

        simulator = new Simulator();
        gui = new GUI(simulator, this);

        final VirtualCube cube = new VirtualCube(new Transform3D());
//        final VirtualCube cube2 = new VirtualCube(new Transform3D(new Matrix4f(
//                0f, 0f, 0f, 0f,
//                0f, 0f, 0f, 0f,
//                0f, 0f, 0f, 0f,
//                0f, 0f, 0f, 0f)));

        BranchGroup group = new BranchGroup();
        group.addChild(cube);
//        group.addChild(cube2);

        simulator.addChild(group);

    }

    /*
     * GUI Commands
     */

    public void setRoster(Collection<String> roster) {
        gui.setRoster(roster);
    }

    /*
     * Simulator Commands
     */

    public void addObject(VirtualObject object) {
        simulator.addObject(object);
    }

    public void loadVRML(String filename) {
        simulator.loadVRML(filename);
    }

//    public boolean transformObject
}
