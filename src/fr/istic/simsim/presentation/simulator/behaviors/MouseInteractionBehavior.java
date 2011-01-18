package fr.istic.simsim.presentation.simulator.behaviors;

import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.picking.PickResult;

import javax.media.j3d.*;
import javax.vecmath.Vector3d;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

public class MouseInteractionBehavior extends Behavior {

    private final BranchGroup branch;
    private       WakeupOr    wEvents;
    private       int         buttonsInUse;
    private       boolean     button1Pressed, button2Pressed, button3Pressed;
    private TransformGroup interactedObject;
    int x1, y1, x2, y2;

    public MouseInteractionBehavior(final BranchGroup branch) {
        this.branch = branch;
    }

    @Override
    public void initialize() {
        final WakeupOnAWTEvent wAWTEvent = new WakeupOnAWTEvent(AWTEvent.MOUSE_EVENT_MASK);
        final WakeupOnAWTEvent wAWTEvent2 = new WakeupOnAWTEvent(AWTEvent.MOUSE_MOTION_EVENT_MASK);

        final WakeupCriterion[] conditions = {wAWTEvent, wAWTEvent2};

        wEvents = new WakeupOr(conditions);

        wakeupOn(wEvents);

        buttonsInUse = 0;
        button1Pressed = false;
        button2Pressed = false;
        button3Pressed = false;
    }

    @Override
    public void processStimulus(final Enumeration criteria) {
        while (criteria.hasMoreElements()) {
            final WakeupOnAWTEvent w = (WakeupOnAWTEvent) criteria.nextElement();
            final AWTEvent events[] = w.getAWTEvent();
            for (final AWTEvent event : events) {
                // System.out.println ("" + events [i] + " " + events
                // [i].getSource
                // ()) ;
                if (event.getID() == MouseEvent.MOUSE_PRESSED) {
                    if (((MouseEvent) event).getButton() == MouseEvent.BUTTON1) {
                        button1Pressed = true;
                    }
                    if (((MouseEvent) event).getButton() == MouseEvent.BUTTON2) {
                        button2Pressed = true;
                    }
                    if (((MouseEvent) event).getButton() == MouseEvent.BUTTON3) {
                        button3Pressed = true;
                    }
                    if (buttonsInUse == 0) {
                        final PickCanvas pickShape = new PickCanvas((Canvas3D) event.getSource(), branch);
                        pickShape.setShapeLocation((MouseEvent) event);
                        x1 = ((MouseEvent) event).getX();
                        y1 = ((MouseEvent) event).getY();
                        final PickResult[] sgPath = pickShape.pickAllSorted();
                        if (sgPath != null) {
                            // System.out.println (sgPath [0]) ;
                            try {
                                // System.out.println (sgPath [0].getObject ())
                                // ;
                                interactedObject = (TransformGroup) sgPath[0].getNode(PickResult.TRANSFORM_GROUP);
                            } catch (final Exception e) {
                                System.out.println(e);
                            }
                        }
                    }
                    buttonsInUse++;
                } else if (event.getID() == MouseEvent.MOUSE_RELEASED) {
                    buttonsInUse--;
                    if (buttonsInUse == 0) {
                        interactedObject = null;
                    }
                    if (((MouseEvent) event).getButton() == MouseEvent.BUTTON1) {
                        button1Pressed = false;
                    }
                    if (((MouseEvent) event).getButton() == MouseEvent.BUTTON2) {
                        button2Pressed = false;
                    }
                    if (((MouseEvent) event).getButton() == MouseEvent.BUTTON3) {
                        button3Pressed = false;
                    }
                } else if (event.getID() == MouseEvent.MOUSE_DRAGGED) {
                    if (interactedObject != null) {
                        double dx = 0, dy = 0, dz = 0;
                        double dh = 0, dp = 0, dr = 0;
                        x2 = ((MouseEvent) event).getX();
                        y2 = ((MouseEvent) event).getY();
                        if (button1Pressed) { // rotation
                            dh = Math.PI * (x2 - x1) / 40.0;
                            dp = Math.PI * (y1 - y2) / 40.0;
                            dr = (dh - dp) / 2.0;
                        }
                        if (button2Pressed) { // zoom
                            dz = (x1 - x2 + y2 - y1) / 40.0;
                        }
                        if (button3Pressed) { // translation dans le plan de
                            // l'écran
                            dx = (x2 - x1) / 40.0;
                            dy = (y1 - y2) / 40.0;
                        }
                        translate(dx, dy, dz);
                        rotate(dh, dp, dr);
                        x1 = x2;
                        y1 = y2;
                    }
                }
            }
        }
        wakeupOn(wEvents);
    }

    protected void rotate(final double dh, final double dp, final double dr) {
        final Transform3D oldT3D = new Transform3D();
        final Transform3D localT3D = new Transform3D();
        final Transform3D newT3D = new Transform3D();

        interactedObject.getTransform(oldT3D);

        final Vector3d rotate = new Vector3d();
        rotate.set(dh, dp, dr);
        localT3D.setEuler(rotate);
        newT3D.mul(oldT3D, localT3D);

        interactedObject.setTransform(newT3D);
    }

    protected void translate(final double dx, final double dy, final double dz) {
        final Transform3D oldT3D = new Transform3D();
        final Transform3D localT3D = new Transform3D();
        final Transform3D newT3D = new Transform3D();
        final Vector3d translate = new Vector3d();

        interactedObject.getTransform(oldT3D);

        translate.set(dx, dy, dz);
        localT3D.setTranslation(translate);
        // newT3D.mul (oldT3D, localT3D) ;
        newT3D.mul(localT3D, oldT3D);

        interactedObject.setTransform(newT3D);
    }
}
