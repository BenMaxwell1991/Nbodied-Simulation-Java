package com.maxwell.display.drawing;

import com.maxwell.main.Main;
import com.maxwell.simulation.maths.objects.Vec3;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class HelperFunctions {

    public static ArrayList<Vec3> getPositionDataByObject(int x) {
        ArrayList<Vec3> positionData = new ArrayList<>();

        for (int i = 0; i < Main.getSimulationData().size(); i++) {
            positionData.add(Main.getSimulationData().get(i).getObjects().get(x).position);
        }
        return positionData;
    }

    public static ArrayList<Vec3> getPositionDataByObjectUntilTimestamp(int x, int timestamp) throws CloneNotSupportedException {
        ArrayList<Vec3> positionData = new ArrayList<>();

        for (int i = 0; i < Main.getSimulationData().size(); i++) {
            positionData.add((Vec3) (Main.getSimulationData().get(i).getObjects().get(x).position).clone());
            if (Main.getSimulationData().get(i).getTimeElapsed() >= timestamp) {
                break;
            }
        }
        return positionData;
    }

    public static float[] findMaxValues(ArrayList<Vec3> array) {
        float[] maxValues = new float[3];
        Arrays.fill(maxValues, 0.0f);

        for (int i = 0; i < array.size(); i++) {
            if (Math.abs(array.get(i).x()) > maxValues[0]) { maxValues[0] = (float) Math.abs(array.get(i).x()); }
            if (Math.abs(array.get(i).y()) > maxValues[1]) { maxValues[1] = (float) Math.abs(array.get(i).y()); }
            if (Math.abs(array.get(i).z()) > maxValues[2]) { maxValues[2] = (float) Math.abs(array.get(i).z()); }
        }

        return maxValues;
    }

    public static float[] rescale(ArrayList<Vec3> vertices, float scale_x, float scale_y) {
        float[] rescaled =  new float[vertices.size() * 2];

        for(int i = 0; i < vertices.size(); i++) {
            rescaled[i * 2] = (float) (vertices.get(i).x() * scale_x);
            rescaled[(i * 2) + 1] = (float) (vertices.get(i).y() * scale_y);
        }
        return rescaled;
    }

    public static ArrayList<Vec3> getSingleObject(ArrayList<ArrayList<Vec3>> objectData, int x) {

        // Find the position data for the single object 'x'
        ArrayList<Vec3> singleObjectData = new ArrayList<>();
        for (int i = 0; i < objectData.size(); i++) {
            singleObjectData.add(objectData.get(i).get(x));
        }

        return singleObjectData;
    }

    /**
     * Returns a FloatBuffer wrapper for the floatArray passed in
     */
    public static FloatBuffer floatToFloatBuffer(float[] floatArray) {
        // First create ByteBuffer
        ByteBuffer bb = ByteBuffer.allocateDirect(floatArray.length * 4);
        bb.order(ByteOrder.nativeOrder());

        // Now Convert ByteBuffer to FloatBuffer
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(floatArray);
        fb.position(0);

        return fb;
    }
}
