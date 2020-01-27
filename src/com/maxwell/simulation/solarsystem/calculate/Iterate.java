package com.maxwell.simulation.solarsystem.calculate;

import com.maxwell.simulation.maths.objects.Vec3;
import com.maxwell.simulation.solarsystem.objects.CelestialBody;

import java.util.ArrayList;

public class Iterate {

    private ArrayList<CelestialBody> theSystem;
    private int iter;
    private double dT;

    public Iterate(double dT, int iterations, ArrayList<CelestialBody> aSystem) {
        this.theSystem = aSystem;
        this.iter = iterations;
        this.dT = dT;
    }

    /**
     * Calculate the new position of each object within
     * the system for n iterations at deltaT timestep.
     */
    public void run(ArrayList<CelestialBody> aSystem) {

        for(int i = 0; i < iter; i++) {
            calcAcc(aSystem);
            calcVel(aSystem);
            calcPos(aSystem, 7);
        }
    }

    private void calcAcc(ArrayList<CelestialBody> aSystem) {
        // Fill this out later, calculate the acceleration of each object with aSystem
    }

    private void calcVel(ArrayList<CelestialBody> aSystem) {

    }

    // Loop through each object within aSystem and calculate the
    // new position based upon its average velocity over the given timestep
    private void calcPos(ArrayList<CelestialBody> aSystem, double dT) {
        for(CelestialBody aBody : theSystem) {
            aBody.position.add(Vec3.multiplyScalar(aBody.velocity, dT));
        }
    }
}
