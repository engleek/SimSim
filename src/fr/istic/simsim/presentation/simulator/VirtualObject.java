package fr.istic.simsim.presentation.simulator;

import com.sun.j3d.utils.geometry.ColorCube;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import java.awt.*;

public class VirtualObject extends TransformGroup {

    private final Appearance appearance;
    private final ColorCube  cube;

    public VirtualObject(final String name) {
        appearance = new Appearance();
        cube = new ColorCube(1.0);
        cube.setAppearance(appearance);
        addChild(cube);
    }

    public void translate(final double x, final double y, final double z) {
        final Transform3D transform = new Transform3D();

        transform.setTranslation(new Vector3d(x, y, z));

        setTransform(transform);
    }

    public void setColor(final Color color) {
        appearance.setColoringAttributes(new ColoringAttributes(new Color3f(color), ColoringAttributes.NICEST));
    }
}
