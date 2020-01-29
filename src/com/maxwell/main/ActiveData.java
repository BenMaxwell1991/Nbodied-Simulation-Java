package com.maxwell.main;

import com.maxwell.simulation.solarsystem.objects.SimulationData;
import com.maxwell.files.FileHelper;

import static com.maxwell.files.FileHelper.savedDataPath;

// This class contains the active data of the system in easy to access memory
public class ActiveData {

    public SimulationData simulationData;

    ActiveData(boolean fromFile) {
        if (fromFile) {
            readActiveDataFromFile();
        } else {
            simulationData = new SimulationData();
        }

    }

    public void readActiveDataFromFile() {
        simulationData = FileHelper.readData(savedDataPath, SimulationData.class);
    }

    public int getObjectNumber() {
        return simulationData.getData().get(0).getObjects().size();
    }
}
