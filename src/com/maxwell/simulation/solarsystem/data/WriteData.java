package com.maxwell.simulation.solarsystem.data;


import com.maxwell.simulation.solarsystem.objects.CelestialBody;
import com.maxwell.simulation.solarsystem.objects.NBodiedSystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * An enumeration that allows us to write the data from our
 * simulation to a file after each iteration. This is a singleton so
 * that it may only ever be instantiated once.
 */
public enum WriteData {

    INSTANCE;
    String outPath = "C:/workspace/Java/solarsystem/data/data.txt";

    /**
     * Prints the current state of aSystem to file
     */
    public void writeToFile (NBodiedSystem aSystem, boolean isHeader) {
        int maxLineLength = 16;

        try {
            File outFile = new File(outPath);
            OutputStream os = new FileOutputStream(outFile, true);

            // Loop through aSystem, printing the position values for each body to file 'outFile'
            for (CelestialBody aBody : aSystem.getObjects()) {
                // Write the names of the objects instead of their positions for the header
                String xStr;
                String yStr;
                String zStr;
                if (isHeader) {
                    xStr = appendSpacesToStr(aBody.name + " Pos x(m)", maxLineLength);
                    yStr = appendSpacesToStr(aBody.name + " Pos y(m)", maxLineLength);
                    zStr = appendSpacesToStr(aBody.name + " Pos z(m)", maxLineLength);
                } else {
                    // Create strings all of equal length (maxLineLength) in exponential format
                    xStr = appendSpacesToStr(String.format("%1.4e", aBody.position.x()), maxLineLength);
                    yStr = appendSpacesToStr(String.format("%1.4e", aBody.position.y()), maxLineLength);
                    zStr = appendSpacesToStr(String.format("%1.4e", aBody.position.z()), maxLineLength);
                }

                // Write to file
                os.write((xStr + yStr + zStr + "    ").getBytes());
            }
            os.write("\n".getBytes());
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Check for the existence of a file at outPath, delete if it is found
     * and create new file in its place.
     */
    public void resetData() throws IOException {
        File file = new File(outPath);
        if (file.isFile()) {
            boolean deleted = file.delete();
            if (!deleted) {
                throw new IOException("Deleting file failed.");
            }
        }
        assert file.createNewFile();
    }

    /**
     * Appends however many space characters are required to string s in order
     * to reach maxLength
     */
    private String appendSpacesToStr(String s, int maxLength) {
        int numberOfSpaces = maxLength - s.length();
        StringBuilder sBuilder = new StringBuilder(s);
        for (int i = 0; i < numberOfSpaces; i++) {
            sBuilder.append(" ");
        }
        return sBuilder.toString();
    }
}
