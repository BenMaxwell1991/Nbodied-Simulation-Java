package com.maxwell.simulation.solarsystem.data;

import com.google.gson.Gson;
import com.maxwell.simulation.maths.objects.Vec3;
import com.maxwell.simulation.solarsystem.objects.CelestialBody;
import com.maxwell.simulation.solarsystem.objects.NBodiedSystem;
import com.maxwell.simulation.solarsystem.objects.PhysicalObject;
import com.maxwell.utility.FileHelper;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TestData {

    private TestData(){};

    public static ArrayList<CelestialBody> getTestData() {
        ArrayList<CelestialBody> v = new ArrayList<>();
        v.add(getSun());
        v.add(getMercury());
        v.add(getVenus());
        v.add(getEarth());
        v.add(getMoon());
        v.add(getMars());
        return v;
    }

    public static void writeTestData(String path) {
        NBodiedSystem aSystem = new NBodiedSystem("Solar_System", new ArrayList<>());
        aSystem.addObject(getSun());
        aSystem.addObject(getMercury());
        aSystem.addObject(getVenus());
        aSystem.addObject(getEarth());
        aSystem.addObject(getMoon());
        aSystem.addObject(getMars());

        try {
            FileHelper.writeJson(aSystem, path, true);
        } catch (IOException e) {
            System.out.print("Failed to write test data to path: " + path);
        }
    }

    private static CelestialBody getSun() {

        String name = "Sun";
        double mass = 1.989e30;
        Vec3 pos = new Vec3(0, 0,0);
        Vec3 vel = new Vec3(0, 0,0);
        Vec3 acc = new Vec3(0, 0,0);

        PhysicalObject properties = new PhysicalObject(name, mass, pos, vel, acc);

        return new CelestialBody(properties);
    }

    private static CelestialBody getMercury() {

        String name = "Mercury";
        double mass = 3.300e23;
        Vec3 pos = new Vec3(4.6e10,0,0);
        Vec3 vel = new Vec3(0,5.898e4,0);
        Vec3 acc = new Vec3(0, 0,0);

        PhysicalObject properties = new PhysicalObject(name, mass, pos, vel, acc);

        return new CelestialBody(properties);
    }

    private static CelestialBody getVenus() {

        String name = "Venus";
        double mass = 4.87e24;
        Vec3 pos = new Vec3(1.0748e11,0,0);
        Vec3 vel = new Vec3(0,3.526e4,0);
        Vec3 acc = new Vec3(0, 0,0);

        PhysicalObject properties = new PhysicalObject(name, mass, pos, vel, acc);

        return new CelestialBody(properties);
    }

    private static CelestialBody getEarth() {

        String name = "Earth";
        double mass = 5.97e24;
        Vec3 pos = new Vec3(1.47098e11,0,0);
        Vec3 vel = new Vec3(0,3.028e4,0);
        Vec3 acc = new Vec3(0, 0,0);

        PhysicalObject properties = new PhysicalObject(name, mass, pos, vel, acc);

        return new CelestialBody(properties);
    }
    private static CelestialBody getMoon() {

        String name = "Moon";
        double mass = 7.346e22;
        Vec3 pos = new Vec3(1.47461e11,0,0);
        Vec3 vel = new Vec3(0,3.1362e4,0);
        Vec3 acc = new Vec3(0, 0,0);

        PhysicalObject properties = new PhysicalObject(name, mass, pos, vel, acc);

        return new CelestialBody(properties);
    }

    private static CelestialBody getMars() {

        String name = "Mars";
        double mass = 6.4171e23;
        Vec3 pos = new Vec3(2.0662e11,0,0);
        Vec3 vel = new Vec3(0,2.65e4,0);
        Vec3 acc = new Vec3(0, 0,0);

        PhysicalObject properties = new PhysicalObject(name, mass, pos, vel, acc);

        return new CelestialBody(properties);
    }
}
