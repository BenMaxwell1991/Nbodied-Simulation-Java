package com.maxwell.simulation.solarsystem.calculate;

import com.maxwell.simulation.maths.objects.Vec3;
import com.maxwell.simulation.solarsystem.objects.CelestialBody;
import com.maxwell.simulation.solarsystem.objects.PhysicalObject;

import java.util.ArrayList;

public class InertialFrame {

    private InertialFrame(){};

    public static ArrayList<CelestialBody> getInertialFrame(ArrayList<CelestialBody> aSystem) {
        ArrayList<CelestialBody> inertialSystem = new ArrayList<CelestialBody>();

        double massTotal = 0;
        Vec3 pTotal = new Vec3();
        Vec3 mdTotal = new Vec3();

        /**
         * Loop through system in order to find the total Mass, Mass*Distance & Momentum
         */
        for(CelestialBody aBody : aSystem) {
            Vec3 md = Vec3.multiplyScalar(aBody.position, aBody.mass);
            Vec3 p = Vec3.multiplyScalar(aBody.velocity, aBody.mass);
            mdTotal = Vec3.add(mdTotal, md);
            pTotal = Vec3.add(pTotal, p);
            massTotal += aBody.mass;
        }

        /** Find center of mass and it' properties */
        Vec3 posCOM = Vec3.divideScalar(mdTotal, massTotal);
        Vec3 velCOM = Vec3.divideScalar(pTotal, massTotal);
        Vec3 accCOM = new Vec3(0, 0, 0);

//        CelestialBody COM = new CelestialBody(new PhysicalObject("Center of Mass", massTotal, posCOM, velCOM, accCOM));
//        aSystem.add(COM);

        /**
         * Loop though system, entering the inertial frame of reference
         * for reach object relative to the center of mass
         */
        for (CelestialBody aBody : aSystem) {
            PhysicalObject aObject = new PhysicalObject(
                    aBody.name,
                    aBody.mass,
                    Vec3.subtract(aBody.position, posCOM),
                    Vec3.subtract(aBody.velocity, velCOM),
                    new Vec3());

            CelestialBody inertialBody = new CelestialBody(aObject);
            inertialSystem.add(inertialBody);
        }

        return inertialSystem;
    }
}











