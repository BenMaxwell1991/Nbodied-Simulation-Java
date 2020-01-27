package com.maxwell.display.mainwindow;

import com.maxwell.display.drawing.GLShapes;
import com.maxwell.display.drawing.HelperFunctions;
import com.maxwell.simulation.maths.objects.Vec3;

import java.awt.*;
import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;

public class Draw {

    public static void doDraw(long windowHandle) {
        final Color lineColorSun = new Color(1.0f, 1.0f, 0.0f);
        final Color lineColorMercury = new Color(0.0f, 1.0f, 1.0f);
        final Color lineColorVenus = new Color(1.0f, 0.0f, 1.0f);
        final Color lineColorEarth = new Color(0.0f, 1.0f, 0.0f);
        final Color lineColorMoon = new Color(0.0f, 1.0f, 0.0f);
        final Color lineColorMars = new Color(0.0f, 0.0f, 1.0f);
        final Color lineColorJupiter = new Color(0.5f, 1.0f, 0.5f);
        final int lineWidth = 1;

        // Get all data relating to earth
        ArrayList<ArrayList<Vec3>> allObjectData = HelperFunctions.getPositionData();

        ArrayList<Vec3> sun = HelperFunctions.getSingleObject(allObjectData, 0);
        ArrayList<Vec3> mercury = HelperFunctions.getSingleObject(allObjectData, 1);
        ArrayList<Vec3> venus = HelperFunctions.getSingleObject(allObjectData, 2);
        ArrayList<Vec3> earth = HelperFunctions.getSingleObject(allObjectData, 3);
        ArrayList<Vec3> moon = HelperFunctions.getSingleObject(allObjectData, 4);
        ArrayList<Vec3> mars = HelperFunctions.getSingleObject(allObjectData, 5);

        ArrayList<ArrayList<Vec3>> system = new ArrayList<>();
        system.add(sun);
        system.add(mercury);
        system.add(venus);
        system.add(earth);
        system.add(moon);
        system.add(mars);

        float[] maxValues = HelperFunctions.findMaxValues(earth);

        for (ArrayList<Vec3> aBody : system) {
            for(int i = 0; i < aBody.size(); i++) {
                aBody.get(i).x(aBody.get(i).x() / (2 * maxValues[0]));
                aBody.get(i).y(aBody.get(i).y() / (2 *maxValues[1]));
            }
        }

        ArrayList<float[]> doubleToFloat = new ArrayList<>(system.size());



        for (int i = 0; i < system.size(); i++) {
            ArrayList<Vec3> aBody = system.get(i);
            float[] ArrayBuilder = new float[aBody.size() * 2];
            for (int j = 0; j < aBody.size(); j++) {
                ArrayBuilder[j * 2] = (float) aBody.get(j).x();
                ArrayBuilder[(j * 2) + 1] = (float) aBody.get(j).y();
            }
            doubleToFloat.add(ArrayBuilder);
        }

        GLShapes.drawOpenPolygon2D(doubleToFloat.get(0), lineWidth, lineColorSun);
        GLShapes.drawOpenPolygon2D(doubleToFloat.get(1), lineWidth, lineColorMercury);
        GLShapes.drawOpenPolygon2D(doubleToFloat.get(2), lineWidth, lineColorVenus);
        GLShapes.drawOpenPolygon2D(doubleToFloat.get(3), lineWidth, lineColorEarth);
        GLShapes.drawOpenPolygon2D(doubleToFloat.get(4), lineWidth, lineColorMoon);
        GLShapes.drawOpenPolygon2D(doubleToFloat.get(5), lineWidth, lineColorMars);

        glfwSwapBuffers(windowHandle);
    }
}
