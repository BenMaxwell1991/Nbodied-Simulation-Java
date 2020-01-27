package com.maxwell.simulation.resources;

/**
 * This class contains useful constants that will help with the
 * simulation of a n bodied gravitation system
 */
public class Constants {

    /** This class should (obviously) never be instantiated */
    private Constants(){}

    /** These paths are where everything is loaded from/saved to */
    public static final String initialDataPath = "C:/workspace/Java/solarsystem/data/solar_system.json";
    public static final String savedDataPath = "C:/workspace/Java/solarsystem/data/simulation_results.json";

    /** Gravitation constant G in physics */
    public static final double G = 6.674E-11;

    /** Number of seconds within a day on Earth */
    public static final int secondsInDay = 86400;


}
