package com.maxwell.simulation.solarsystem.objects;

import com.maxwell.simulation.maths.objects.Vec3;

import java.util.ArrayList;

/**
 * A system containing Celestial bodies
 */
public class NBodiedSystem implements Cloneable {

    private String name;
    private double timeElapsed;
    private ArrayList<CelestialBody> objects;

    public NBodiedSystem() {
        this("", new ArrayList<CelestialBody>());
    }

    public NBodiedSystem(String name, ArrayList<CelestialBody> objects) {
        this.name = name;
        this.objects = objects;
        this.timeElapsed = 0.0;
    }

    // Getters
    public String getName() {
        return this.name;
    }
    public double getTimeElapsed() { return this.timeElapsed; }
    public ArrayList<CelestialBody> getObjects() { return this.objects; }

    // Setters
    public void setName(String value) {
        this.name = value;
    }
    public void setTimeElapsed(double value) { this.timeElapsed = value; }
    public void setObjects(ArrayList<CelestialBody> value) {
        this.objects = value;
    }

    // Utility
    public void addObject(CelestialBody value) { this.objects.add(value); }
    public void deleteObject(int i) { this.objects.remove(i); }


    /**
     * Deep clone the object
     */
    public Object clone() {
        try {
            NBodiedSystem cloned = (NBodiedSystem) super.clone();

            ArrayList<CelestialBody> clonedArray = new ArrayList<>();
            for (CelestialBody aBody : cloned.getObjects()) {
                clonedArray.add((CelestialBody) aBody.clone());
            }

            cloned.setObjects(clonedArray);
            return cloned;
        } catch (Exception e) {
            return null;
        }
    }
}