package fr.istic.simsim.presentation.simulator.objects;

import com.sun.j3d.utils.geometry.ColorCube;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import java.awt.*;

public abstract class VirtualObject extends TransformGroup {

    public VirtualObject(Transform3D transform3D) {
        super(transform3D);
    }
}
