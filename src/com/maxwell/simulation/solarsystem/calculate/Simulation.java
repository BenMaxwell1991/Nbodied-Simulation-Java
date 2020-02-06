package com.maxwell.simulation.solarsystem.calculate;

import com.maxwell.simulation.maths.methods.MaxwellSimple;
import com.maxwell.simulation.solarsystem.data.TestData;
import com.maxwell.simulation.solarsystem.objects.NBodiedSystem;
import com.maxwell.simulation.solarsystem.objects.SimulationData;
import com.maxwell.files.FileHelper;

import java.io.IOException;
import java.util.ArrayList;

import static com.maxwell.simulation.resources.Constants.secondsInDay;
import static com.maxwell.simulation.solarsystem.calculate.InertialFrame.getInertialFrame;
import static java.lang.Math.ceil;

/**
 * This class holds all the information of the system to be simulated, as well
 * as the parameters for the simulation, such as the timestep and duration.
 *
 * To perform the simulation this class will call an iterative solution, then
 * output this data periodically to a file.
 */
public class Simulation {

    public Simulation(double timestep, double nDays, int iterationsPerPrint) {
        this.deltaT = timestep;
        this.days = nDays;
        this.outputFrequency = iterationsPerPrint;
        this.iterator = new MaxwellSimple();
    }

    // Contains the state of every object within the system at a given time.
    private NBodiedSystem solarSystem;

    // Time between steps in seconds
    private double deltaT;

    // Number of days to run the simulation for
    private double days;

    // How frequently the state of the system is printed to a file.
    private int outputFrequency;

    private MaxwellSimple iterator;

    // Performs the simulation of the solar system
    public void simulateSolarSystem(String loadPath, String savePath) throws IOException {
        SimulationData simulation = new SimulationData("Simulation", new ArrayList<>());
        double iterations = ceil(secondsInDay * days / deltaT);

        // Write data in json format then read this into memory
        TestData.writeTestData(loadPath);
        solarSystem = FileHelper.readJson(loadPath, NBodiedSystem.class);

        // Enter the inertial frame of the center of mass for the system.
        solarSystem.setObjects(getInertialFrame(solarSystem.getObjects()));

        // Enter the inertial Frame;

        simulation.addFrame((NBodiedSystem)solarSystem.clone());

        for (int i = 0; i < iterations; i++) {
            solarSystem.setObjects(iterator.stepForward(solarSystem.getObjects(), deltaT));
            solarSystem.setTimeElapsed(solarSystem.getTimeElapsed() + deltaT);
            if ((i % outputFrequency == 0) && (i != 0)) {
                simulation.addFrame((NBodiedSystem)solarSystem.clone());
            }
        }

        // Writes simulation data to path
        FileHelper.writeJson(simulation, savePath, true);
    }
}
