package com.maxwell.display.drawing;

import com.maxwell.simulation.maths.objects.Vec3;
import com.maxwell.simulation.solarsystem.objects.NBodiedSystem;
import com.maxwell.simulation.solarsystem.objects.SimulationData;
import com.maxwell.utility.FileHelper;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;

import static com.maxwell.simulation.resources.Constants.savedDataPath;

public class HelperFunctions {

    // Returns an array of the position of every object at every instance of the simulation
    public static ArrayList<ArrayList<Vec3>> getPositionData() {

        SimulationData data = FileHelper.readData(savedDataPath, SimulationData.class);

        ArrayList<ArrayList<Vec3>> positionData = new ArrayList<>(data.getData().size());
        for (int i = 0; i < data.getData().size(); i++) {
            NBodiedSystem frame = data.getData().get(i);
            positionData.add(new ArrayList<>());
            for (int j = 0; j < data.getData().get(j).getObjects().size(); j++) {
                Vec3 object_j_position = frame.getObjects().get(j).position;
                positionData.get(i).add(object_j_position);
            }
        }
        return positionData;
    }

    public static float[] findMaxValues(ArrayList<Vec3> array) {
        float[] maxValues = new float[3];
        Arrays.fill(maxValues, 0.0f);

        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).x() > maxValues[0]) { maxValues[0] = (float) array.get(i).x(); }
            if (array.get(i).y() > maxValues[1]) { maxValues[1] = (float) array.get(i).y(); }
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

    public static FloatBuffer floatToFloatBuffer(float[] floatArray) {
        // First create ByteBUffer
        ByteBuffer bb = ByteBuffer.allocateDirect(floatArray.length * 4);
        bb.order(ByteOrder.nativeOrder());

        // Now Convert ByteBuffer to FloatBuffer
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(floatArray);
        fb.position(0);

        return fb;
    }
}
