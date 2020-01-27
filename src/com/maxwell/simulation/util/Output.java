package com.maxwell.simulation.util;

import com.maxwell.simulation.maths.objects.Vec3;

public class Output {

    private Output() {}

    public static void print(String msg) {
        System.out.println(msg);
    }

    public static void printVec3(String aHeader, Vec3 v, String units) {
        System.out.println(aHeader + "\n" +
                "X: " + v.x() + units + "\n" +
                "Y: " + v.y() + units + "\n" +
                "Z: " + v.z() + units);
    }
}
