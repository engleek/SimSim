package fr.istic.simsim.presentation.simulator.objects;

import com.sun.j3d.utils.geometry.ColorCube;

import javax.media.j3d.Node;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

public class VirtualCube extends VirtualObject {
    private static int index = 0;

    public VirtualCube(Transform3D transform3D) {
        super(transform3D);

        setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        setCapability(Node.ENABLE_PICK_REPORTING);

        ColorCube cube = new ColorCube(1.0);

        addChild(cube);
    }
}
