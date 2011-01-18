package fr.istic.simsim.presentation.simulator;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;
import fr.istic.simsim.presentation.gui.Controls;
import org.jdesktop.j3d.loaders.vrml97.VrmlLoader;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Point3d;
import java.awt.*;

public class Universe extends JApplet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Canvas3D       canvas3D;
    private SimpleUniverse universe;

    private BranchGroup    rootGroup;
    private TransformGroup rootTransform;

    public Universe() {
        final Controls interactivePanel = new Controls();
        initCanvas();

        setLayout(new BorderLayout());
        add(canvas3D, BorderLayout.CENTER);
        add(interactivePanel, BorderLayout.SOUTH);
    }

    @Override
    public void destroy() {
        universe.removeAllLocales();
    }

    public void initCanvas() {
        // création du canvas
        canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());

        // gestion de l'univers
        universe = new SimpleUniverse(canvas3D);

        // création du branchgroup racine
        rootGroup = new BranchGroup();

        final VirtualObject cube = new VirtualObject("First Cube");
        final VirtualObject cube2 = new VirtualObject("Second Cube");

        rootTransform = new TransformGroup();
        rootTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        rootTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        rootTransform.setCapability(Node.ENABLE_PICK_REPORTING);
        rootTransform.setCapability(Group.ALLOW_CHILDREN_EXTEND);

        // rootTransform.addChild(cube);
        // rootTransform.addChild(cube2);

        cube2.translate(2, 0, 0);
        cube2.setColor(Color.blue);

        rootGroup.addChild(rootTransform);
        setNavigation();

        // rotation de caméra
        final KeyNavigatorBehavior rotationBehavior = new KeyNavigatorBehavior(universe.getViewingPlatform().getViewPlatformTransform());
        rotationBehavior.setSchedulingBounds(new BoundingSphere());
        rootGroup.addChild(rotationBehavior);
        rootGroup.compile();

        // ajout de la racine à l'univers
        universe.addBranchGraph(rootGroup);
        universe.getViewingPlatform().setNominalViewingTransform();

    }

    public void setNavigation() {
        final TransformGroup vpTrans = universe.getViewingPlatform().getViewPlatformTransform();
        final KeyNavigatorBehavior keyNavBeh = new KeyNavigatorBehavior(vpTrans);
        keyNavBeh.setSchedulingBounds(new BoundingSphere(new Point3d(), 1000.0));
        rootGroup.addChild(keyNavBeh);
    }

    public void loadVRML(final String fileName) {
        try {
            final VrmlLoader loader = new VrmlLoader();
            final Scene s = loader.load(fileName);
            rootTransform.addChild(s.getSceneGroup());
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
