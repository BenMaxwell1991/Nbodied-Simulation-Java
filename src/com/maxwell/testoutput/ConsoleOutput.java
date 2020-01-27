package com.maxwell.testoutput;

import com.maxwell.simulation.solarsystem.objects.CelestialBody;

import java.util.ArrayList;

import static com.maxwell.simulation.util.Output.print;

public class ConsoleOutput {

    /** This class should never be instantiated. */
    private ConsoleOutput(){};

    /**
     * Prints the given physical system of celestial bodies to the console so that the output can be checked.
     */
    public static void printSystem(ArrayList<CelestialBody> aSystem) {
        int i = 1;
        for (CelestialBody aBody : aSystem) {
            print("Object " + i + ":");
            print("Name: " + aBody.name);
            print("Mass: " + aBody.mass + "kg");
            print("Position: \n" +
                  "x = " + aBody.position.x() + "m\n" +
                  "y = " + aBody.position.y() + "m\n" +
                  "z = " + aBody.position.z() + "m");
            print("Velocity: \n" +
                    "x = " + aBody.velocity.x() + "m/s\n" +
                    "y = " + aBody.velocity.y() + "m/s\n" +
                    "z = " + aBody.velocity.z() + "m/s");
            print("Acceleration: \n" +
                    "x = " + aBody.acceleration.x() + "m/s^2\n" +
                    "y = " + aBody.acceleration.y() + "m/s^2\n" +
                    "z = " + aBody.acceleration.z() + "m/s^2\n");
            i++;
        }
    }
}
