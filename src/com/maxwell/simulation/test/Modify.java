package com.maxwell.simulation.test;

import java.util.ArrayList;

public class Modify {

    private Modify(){}

    public static void changeValues(ClassWithValues c, String newName, double newNumber) {
        c.setName(newName);
        c.setNumber(newNumber);
    }

    public static void changeValuesInLoop(ArrayList<ClassWithValues> v, String newName, double newNumber) {
        for (ClassWithValues c : v) {
            c.setName(newName);
            c.setNumber(newNumber);
        }
    }
}
