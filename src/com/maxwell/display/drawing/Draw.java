package com.maxwell.display.drawing;

import com.maxwell.main.Main;
import com.maxwell.simulation.maths.objects.Vec3;
import com.maxwell.simulation.resources.Constants;
import com.maxwell.simulation.solarsystem.objects.SolarObjects;

import java.util.ArrayList;

import static com.maxwell.main.Main.initTime;

public class Draw {

    static final int lineWidth = 1;

    public static void doDraw(long windowHandle) {

        float sx = Main.display.scale_x;
        float sy = Main.display.scale_y;

        DrawingVerticies orbits = new DrawingVerticies();

        // Simulation seconds per Second
        double simulationSpeed = Constants.secondsInDay * 150;
        double elapsedTime = (System.nanoTime() - initTime) / 1E9;
        int lengthOfOrbitTrail = Constants.secondsInDay * 300;

        int finalTime = (int) Math.round(elapsedTime * simulationSpeed);
        int initTime = finalTime - (lengthOfOrbitTrail);

        try {
            orbits.getDataTillTimestamp(initTime, finalTime);
        } catch (CloneNotSupportedException e) {
            //TODO: Handle later
        }

        float[] maxValues = HelperFunctions.findMaxValues(orbits.posData.get(orbits.posData.size() - 1));
        float theMaxValue = Math.max(maxValues[0], maxValues[1]) * 1.2f;

        for (ArrayList<Vec3> aBody : orbits.posData) {
            for(int i = 0; i < aBody.size(); i++) {
                aBody.get(i).x(aBody.get(i).x() * sx / theMaxValue);
                aBody.get(i).y(aBody.get(i).y() * sy / theMaxValue);
            }
        }

        ArrayList<float[]> doubleToFloat = new ArrayList<>(orbits.posData.size());

        for (int i = 0; i < orbits.posData.size(); i++) {
            ArrayList<Vec3> aBody = orbits.posData.get(i);
            float[] ArrayBuilder = new float[aBody.size() * 2];
            for (int j = 0; j < aBody.size(); j++) {
                ArrayBuilder[j * 2] = (float) aBody.get(j).x();
                ArrayBuilder[(j * 2) + 1] = (float) aBody.get(j).y();
            }
            doubleToFloat.add(ArrayBuilder);
        }

        for (int i = 0; i < SolarObjects.values().length; i ++) {
            GLShapes.drawOpenPolygon2D(doubleToFloat.get(i), lineWidth, SolarObjects.values()[i].orbitColour);
        }
    }
}
