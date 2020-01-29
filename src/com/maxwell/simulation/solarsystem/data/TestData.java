package com.maxwell.simulation.solarsystem.data;

import com.maxwell.simulation.solarsystem.objects.SolarObjects;
import com.maxwell.simulation.solarsystem.objects.CelestialBody;
import com.maxwell.simulation.solarsystem.objects.NBodiedSystem;
import com.maxwell.simulation.solarsystem.objects.PhysicalObject;
import com.maxwell.files.FileHelper;

import java.io.IOException;
import java.util.ArrayList;

public class TestData {

    private TestData() {
    }

    ;

    // Returns an array of all the initial test data
    public static ArrayList<CelestialBody> getTestData() {
        ArrayList<CelestialBody> v = new ArrayList<>();

        for (int i = 0; i < SolarObjects.values().length; i++) {
            v.add(new CelestialBody(
                    new PhysicalObject(
                            SolarObjects.values()[0].name,
                            SolarObjects.values()[0].mass,
                            SolarObjects.values()[0].pos,
                            SolarObjects.values()[0].vel,
                            SolarObjects.values()[0].acc)));
        }
        return v;
    }

    // Writes all of the initial test data to 'path', naming the system "Solar_System"
    public static void writeTestData(String path) {
        NBodiedSystem aSystem = new NBodiedSystem("Solar_System", new ArrayList<>());

        for (int i = 0; i < SolarObjects.values().length; i++) {
            aSystem.addObject(new CelestialBody(
                    new PhysicalObject(
                            SolarObjects.values()[i].name,
                            SolarObjects.values()[i].mass,
                            SolarObjects.values()[i].pos,
                            SolarObjects.values()[i].vel,
                            SolarObjects.values()[i].acc)));
        }
        try {
            FileHelper.writeJson(aSystem, path, true);
        } catch (IOException e) {
            System.out.print("Failed to write test data to path: " + path);
        }
    }
}