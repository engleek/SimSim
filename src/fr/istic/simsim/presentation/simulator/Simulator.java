package fr.istic.simsim.presentation.simulator;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.picking.PickTool;
import com.sun.j3d.utils.picking.behaviors.PickRotateBehavior;
import com.sun.j3d.utils.picking.behaviors.PickTranslateBehavior;
import com.sun.j3d.utils.picking.behaviors.PickZoomBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;
import fr.istic.simsim.presentation.simulator.behaviors.KeyNavigatorBehavior;
import org.jdesktop.j3d.loaders.vrml97.VrmlLoader;

import javax.media.j3d.*;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public class Simulator extends Canvas3D {

    private static final long serialVersionUID = 1L;

    // private final PhysicalBody physicalBody;
    // private final PhysicalEnvironment physicalEnvironment;

    private final SimpleUniverse universe;
    private final Locale         locale;

    private final BranchGroup scene;

    private final Transform3D    orientation;
    private final TransformGroup sceneTrans;

    private final KeyNavigatorBehavior keyNav;

    public Simulator() {
        super(SimpleUniverse.getPreferredConfiguration());

        // physicalBody = new PhysicalBody();
        // physicalEnvironment = new PhysicalEnvironment();

        // Création de l'univers
        universe = new SimpleUniverse(this);
        universe.getViewingPlatform().setNominalViewingTransform();

        // High resolution coords (utile?)
        locale = new Locale(universe);

        // Scene Orientation
        orientation = new Transform3D();
        orientation.setEuler(new Vector3d(Math.PI / 3, Math.PI / 6, 0));

        // Scene Transform Group
        sceneTrans = new TransformGroup(orientation);
        sceneTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        sceneTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        sceneTrans.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
        sceneTrans.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        sceneTrans.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
        sceneTrans.setCapability(TransformGroup.ENABLE_PICK_REPORTING);

        ColorCube cube = new ColorCube(0.2);
        cube.getGeometry().setCapability(Geometry.ALLOW_INTERSECT);
        sceneTrans.addChild(cube);

        TransformGroup viewTransform = universe.getViewingPlatform().getViewPlatformTransform();
        keyNav = new KeyNavigatorBehavior(viewTransform);
        keyNav.setSchedulingBounds(new BoundingSphere(new Point3d(), 1000.0));

        scene = new BranchGroup();
        scene.addChild(sceneTrans);
        scene.addChild(keyNav);

        BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 0), 100);

        PickTranslateBehavior ptb = new PickTranslateBehavior(scene, this, bounds);
        PickRotateBehavior prb = new PickRotateBehavior(scene, this, bounds);
        PickZoomBehavior pzb = new PickZoomBehavior(scene, this, bounds);

        prb.setMode(PickTool.GEOMETRY);
        ptb.setMode(PickTool.GEOMETRY);
        pzb.setMode(PickTool.GEOMETRY);

        prb.setTolerance(0.0f);
        ptb.setTolerance(0.0f);
        pzb.setTolerance(0.0f);

        scene.addChild(prb);
        scene.addChild(ptb);
        scene.addChild(pzb);

        scene.compile();

        locale.addBranchGraph(scene);
    }

    public void addChild(BranchGroup child) {
        sceneTrans.addChild(child);
    }

    public void loadVRML(final String fileName) {
        final VrmlLoader loader = new VrmlLoader();
        try {
            final Scene vrmlScene = loader.load(fileName);
            addChild(vrmlScene.getSceneGroup());
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

}
