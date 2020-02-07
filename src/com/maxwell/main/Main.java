package com.maxwell.main;

import com.maxwell.display.mainwindow.Window;
import com.maxwell.simulation.solarsystem.calculate.Simulation;
import com.maxwell.simulation.solarsystem.objects.NBodiedSystem;

import java.io.IOException;
import java.util.ArrayList;

import static com.maxwell.files.FileHelper.initialDataPath;
import static com.maxwell.files.FileHelper.savedDataPath;

public class Main {

    public static Window display;
    public static double initTime = 0.0;
    public static ActiveData activeData;

    public static void main(String[] args) {
        runSimulation();
        activeData = new ActiveData(true);
        initTime = System.nanoTime() + 2E9;
        displayOutput();
    }

    public static ArrayList<NBodiedSystem> getSimulationData() {
        return activeData.simulationData.getData();
    }

    // Load start conditions, run simulation, save data
    private static void runSimulation() {
        Simulation sim = new Simulation(3600, 20000, 50);

        try {
            sim.simulateSolarSystem(initialDataPath, savedDataPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Loads the output and displays it graphically
    private static void displayOutput() {
        display = new Window();
        display.showMainWindow();
    }
}
