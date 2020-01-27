package com.maxwell.simulation.solarsystem.objects;

import com.google.gson.annotations.Expose;
import com.maxwell.simulation.maths.objects.Vec3;

import com.google.gson.*;

/**
 * A class that stores all the desired physical information about an object
 * as well as the objects name.
 */
public class PhysicalObject implements Cloneable {

    public String name;

    public double mass;

    public Vec3 position;

    public Vec3 velocity;

    public Vec3 acceleration;

    public PhysicalObject() {
        this("", 0, new Vec3(), new Vec3(), new Vec3());
    }

    public PhysicalObject(PhysicalObject obj) {
        this(obj.name, obj.mass, obj.position, obj.velocity, obj.acceleration);
    }

    public PhysicalObject(String aName,
                         double aMass,
                         Vec3 posVec,
                         Vec3 velVec,
                         Vec3 accVec) {
        name = aName;
        mass = aMass;
        position = posVec;
        velocity = velVec;
        acceleration = accVec;
    }

    public static double distanceSqr(PhysicalObject objectA, PhysicalObject objectB) {
//        return (Math.pow((objectA.position.x() - objectB.position.x()), 2) +
//                Math.pow((objectA.position.y() - objectB.position.y()), 2) +
//                Math.pow((objectA.position.z() - objectB.position.z()), 2));
        return Vec3.distanceSquared(objectA.position, objectB.position);
    }

    public static double distance(PhysicalObject objectA, PhysicalObject objectB) {
//        return Math.sqrt(distanceSqr(objectA, objectB));
        return Math.sqrt(Vec3.distanceSquared(objectA.position, objectB.position));
    }

    public Vec3 getMomentum() {
        return Vec3.multiplyScalar(this.velocity, this.mass);
    }

    public Object clone() throws CloneNotSupportedException {
        PhysicalObject cloned = (PhysicalObject)super.clone();

        cloned.position = (Vec3)cloned.position.clone();
        cloned.velocity = (Vec3)cloned.velocity.clone();
        cloned.acceleration = (Vec3)cloned.acceleration.clone();

        return cloned;
    }
}
