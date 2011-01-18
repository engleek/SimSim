package fr.istic.simsim.presentation.simulator.behaviors;

import javax.media.j3d.Behavior;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupOnAWTEvent;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

public class KeyNavigatorBehavior extends Behavior {

    private final TransformGroup transformGroup;
    private final Transform3D transform = new Transform3D();

    private double angle     = 0.0;
    private double positionX = 0.0;
    private double positionY = 0.0;
    private double positionZ = 0.0;

    public KeyNavigatorBehavior(final TransformGroup transformGroup) {
        this.transformGroup = transformGroup;
    }

    @Override
    public void initialize() {
        wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void processStimulus(final Enumeration criteria) {
        while (criteria.hasMoreElements()) {
            final WakeupOnAWTEvent wakeEvent = (WakeupOnAWTEvent) criteria.nextElement();
            final AWTEvent[] events = wakeEvent.getAWTEvent();

            for (final AWTEvent event : events) {
                KeyEvent keyEvent = (KeyEvent) event;
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        angle += 0.1;
                        transform.rotY(angle);
                        transformGroup.setTransform(transform);
                        break;
                    case KeyEvent.VK_RIGHT:
                        angle -= 0.1;
                        transform.rotY(angle);
                        transformGroup.setTransform(transform);
                        break;
                }
            }

        }
        wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
    }
}