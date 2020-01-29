package com.maxwell.display.drawing;

import com.maxwell.main.Main;
import com.maxwell.simulation.maths.objects.Vec3;
import com.maxwell.simulation.solarsystem.objects.NBodiedSystem;

import java.util.ArrayList;

import static com.maxwell.display.drawing.HelperFunctions.getPositionDataByObject;

public class DrawingVerticies {

    public ArrayList<ArrayList<Vec3>> posData = new ArrayList<>();

    public void initData() {
        int n = Main.getSimulationData().get(0).getObjects().size();
        for (int i = 0; i < n; i++) {
            posData.add(getPositionDataByObject(i));
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
