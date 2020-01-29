package com.maxwell.simulation.solarsystem.objects;

import com.maxwell.simulation.maths.objects.Vec3;

import java.awt.*;

public enum SolarObjects {

    SUN("Sun", 1.989e30,
            new Vec3(0, 0,0),
            new Vec3(0, 0,0),
            new Vec3(0, 0,0),
            new Color(1.0f, 1.0f, 0.0f)),

    MERCURY("Mercury", 3.300e23,
            new Vec3(4.6e10, 0,0),
            new Vec3(0, -5.898e4,0),
            new Vec3(0, 0,0),
            new Color(0.5f, 1.0f, 0.5f)),

    VENUS("Venus", 4.87e24,
            new Vec3(1.0748e11, 0,0),
            new Vec3(0, 3.526e4,0),
            new Vec3(0, 0,0),
            new Color(0.0f, 0.0f, 1.0f)),

    EARTH("Earth", 5.97e24,
            new Vec3(1.47098e11, 0,0),
            new Vec3(0, 3.028e4,0),
            new Vec3(0, 0,0),
            new Color(0.1f, 1.0f, 0.1f)),

    MOON("Moon", 7.346e22,
            new Vec3(1.47461e11, 0,0),
            new Vec3(0, 3.1362e4,0),
            new Vec3(0, 0,0),
            new Color(0.0f, 0.5f, 0.5f)),

    MARS("Mars", 6.4171e23,
            new Vec3(2.0662e11, 0,0),
            new Vec3(0, 2.65e4,0),
            new Vec3(0, 0,0),
            new Color(1.0f, 0.0f, 0.0f)),

    JUPITER("Jupiter", 1.898e27,
            new Vec3(7.4052e11, 0,0),
            new Vec3(0, 1.372e4,0),
            new Vec3(0, 0,0),
            new Color(1.0f, 1.0f, 0.0f));

    public final String name;
    public final double mass;
    public final Vec3 pos;
    public final Vec3 vel;
    public final Vec3 acc;
    public final Color orbitColour;

    SolarObjects(String name, double mass, Vec3 pos, Vec3 vel, Vec3 acc, Color c) {
        this.name = name;
        this.mass = mass;
        this.pos = pos;
        this.vel = vel;
        this.acc = acc;
        this.orbitColour = c;
    }
}
