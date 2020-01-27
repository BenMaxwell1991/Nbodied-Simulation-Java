package com.maxwell.simulation.maths.methods;

import com.maxwell.simulation.maths.objects.Vec3;
import com.maxwell.simulation.solarsystem.objects.CelestialBody;
import com.maxwell.simulation.solarsystem.objects.PhysicalObject;

import java.util.ArrayList;

import static com.maxwell.simulation.resources.Constants.G;

/**
 * An enumerated implementation of CalculateTimestep, this should be able to, given a system of Physical Objects,
 * Calculate the acceleration at a given point in time, then using these values, calculate their
 * new velocities and positions at the end of the timestep
 */
public class MaxwellSimple extends CalculateTimestep {

    public ArrayList<CelestialBody> stepForward(ArrayList<CelestialBody> aSystem, double dT) {
        calcAcc(aSystem, dT);
        calcVel(aSystem, dT);
        calcPos(aSystem, dT);
        return aSystem;
    }

    public void calcAcc(ArrayList<CelestialBody> aSystem, double dT) {

        /** Reset the value for acceleration of each object to 0 */
        for (CelestialBody aBody : aSystem) {
            aBody.acceleration = new Vec3();
        }

        /** Loop through each object with the system and update the
         * acceleration with the Force calculated between each object */
        for(int i = 0; i < (aSystem.size() - 1); i++) {
            for(int j = i + 1; j < aSystem.size(); j++) {
                Vec3 Force = calcForce(aSystem.get(i), aSystem.get(j));
                aSystem.get(i).acceleration.add(Vec3.divideScalar(Force, aSystem.get(i).mass));
                aSystem.get(j).acceleration.subtract(Vec3.divideScalar(Force, aSystem.get(j).mass));
            }
        }
    }

    public void calcVel(ArrayList<CelestialBody> aSystem, double dT) {
        for (CelestialBody aBody : aSystem) {
            aBody.velocity.add(Vec3.multiplyScalar(aBody.acceleration, dT));
        }
    }

    public void calcPos(ArrayList<CelestialBody> aSystem, double dT) {
        for (CelestialBody aBody : aSystem) {
            aBody.position.add(Vec3.multiplyScalar(aBody.velocity, dT));
        }
    }

    // Returns the Gravitational Force between object a and b in ArrayList form. Direction a --> b
    private Vec3 calcForce(CelestialBody a, CelestialBody b) {
        double modForce = G * a.mass * b.mass / PhysicalObject.distanceSqr(a, b);
        Vec3 direction = Vec3.normalise(Vec3.subtract(b.position, a.position));
        return Vec3.multiplyScalar(direction, modForce);
    }
}