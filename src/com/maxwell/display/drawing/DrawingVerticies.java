package com.maxwell.display.drawing;

import com.maxwell.main.Main;
import com.maxwell.simulation.maths.objects.Vec3;

import java.util.ArrayList;

import static com.maxwell.display.drawing.HelperFunctions.getPositionDataByObject;
import static com.maxwell.display.drawing.HelperFunctions.getPositionDataByObjectUntilTimestamp;

public class DrawingVerticies {

    public ArrayList<ArrayList<Vec3>> posData = new ArrayList<>();

    void clearData() {
        posData = new ArrayList<>();
    }

    public void getAllData() {
        int n = Main.getSimulationData().get(0).getObjects().size();
        for (int i = 0; i < n; i++) {
            posData.add(getPositionDataByObject(i));
        }
    }

    public void getDataTillTimestamp(int initTime, int finalTime) throws CloneNotSupportedException {
        int n = Main.getSimulationData().get(0).getObjects().size();
        for (int i = 0; i < n; i++) {
            posData.add(getPositionDataByObjectUntilTimestamp(i, initTime, finalTime));
        }
    }

    private static ArrayList<ArrayList<Vec3>> getPositionData(ArrayList<ArrayList<Vec3>> allData) {
        ArrayList<ArrayList<Vec3>> posData = new ArrayList<>();

        for (int i = 0; i < allData.get(0).size(); i++) {
            posData.add(HelperFunctions.getSingleObject(allData, i));
        }

        return posData;
    }
}
