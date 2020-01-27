package com.maxwell.simulation.solarsystem.objects;

public class CelestialBody extends PhysicalObject implements Cloneable {

    public CelestialBody() {
        super();
    }

    public CelestialBody(PhysicalObject aObject) { super(aObject); }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
