package com.maxwell.simulation.solarsystem.objects;

import java.util.ArrayList;

/**
 * Contains all of the data for the simulation, i.e. the State of the system at every time interval
 */
public class SimulationData {

    private String name;
    private ArrayList<NBodiedSystem> data;

    public SimulationData() {
        this("", new ArrayList<NBodiedSystem>());
    }

    public SimulationData(String name, ArrayList<NBodiedSystem> data) {
        this.name = name;
        this.data = data;
    }

    // Getters
    public String getName() {
        return this.name;
    }
    public ArrayList<NBodiedSystem> getData() { return this.data; }

    // Setters
    public void setName(String value) {
        this.name = value;
    }
    public void setData(ArrayList<NBodiedSystem> value) {
        this.data = value;
    }

    // Utility
    public void addFrame(NBodiedSystem aSystem) {
        NBodiedSystem aCopy = new NBodiedSystem(aSystem.getName(), aSystem.getObjects());
        aCopy.setTimeElapsed(aSystem.getTimeElapsed());
        this.data.add(aCopy);
    }
    public void deleteFrame(int i) { this.data.remove(i); }
}
