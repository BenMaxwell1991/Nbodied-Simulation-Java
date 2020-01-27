package com.maxwell.simulation.maths.methods;

import com.maxwell.simulation.solarsystem.objects.CelestialBody;

import java.util.ArrayList;

/**
 * An implementation of this interface should use stepForward to call calc Acc/Vel/Pos
 * in order to update each object within aSystem so that we know their new positions
 * and velocities after a given timestep dT.
 */
abstract class CalculateTimestep {

    /**
     * This function should step forward by a time specified by dT,
     * in order to do so, calc Acc/Vel/Pos must be called in turn.
     */
    abstract ArrayList<CelestialBody> stepForward(ArrayList<CelestialBody> aSystem, double dT);

    /**
     * Calculates the new accelerate of each object within aSystem.
     */
    abstract void calcAcc(ArrayList<CelestialBody> aSystem, double dT);

    /**
     * Calculates the new Velocities of each object within aSystem after dT.
     */
    abstract void calcVel(ArrayList<CelestialBody> aSystem, double dT);

    /**
     * Using the Velocity, calculate the new position of each object.
     */
    abstract void calcPos(ArrayList<CelestialBody> aSystem, double dT);
}
