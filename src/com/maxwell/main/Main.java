package com.maxwell.main;

import com.maxwell.display.mainwindow.Window;
import com.maxwell.simulation.solarsystem.calculate.Simulation;

import java.io.IOException;

import static com.maxwell.simulation.resources.Constants.initialDataPath;
import static com.maxwell.simulation.resources.Constants.savedDataPath;

public class Main {

    public static void main(String[] args) {
        runSimulation();
        displayOutput();
    }


    // Load start conditions, run simulation, save data
    private static void runSimulation() {
        Simulation sim = new Simulation(365, 365, 50);

        try {
            sim.simulateSolarSystem(initialDataPath, savedDataPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Loads the output and displays it graphically
    private static void displayOutput() {
        Window display = new Window();
        display.showMainWindow();
    }
}
