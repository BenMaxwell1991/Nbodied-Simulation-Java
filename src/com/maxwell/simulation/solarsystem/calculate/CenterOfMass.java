package com.maxwell.simulation.solarsystem.calculate;

import com.maxwell.simulation.maths.objects.Vec3;
import com.maxwell.simulation.solarsystem.objects.CelestialBody;

import java.util.ArrayList;

public class CenterOfMass extends CelestialBody {

    public CenterOfMass(ArrayList<CelestialBody> aSystem) {
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

        this.name ="Center of Mass";
        this.mass = massTotal;
        this.position = posCOM;
        this.velocity = velCOM;
        this.acceleration = accCOM;
    }
}
