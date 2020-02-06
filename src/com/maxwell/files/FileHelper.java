package com.maxwell.files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.Primitives;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {

    /** These paths are where everything is loaded from/saved to */
    public static final String initialDataPath = "C:/workspace/Java/solarsystem/data/solar_system.json";
    public static final String savedDataPath = "C:/workspace/Java/solarsystem/data/simulation_results.json";

    private FileHelper(){};

    /**
     * Check for the existence of a file at path, delete if it is found
     * and create new file in its place.
     */
    public static void createEmptyFile(String path) throws IOException {
        File file = new File(path);
        deleteFile(path);
        boolean created = file.createNewFile();
        if (!created) {
            throw new IOException("Creating file failed.");
        }
    }

    /**
     * Check for file at 'path' and delete if it exists.
     */
    public static void deleteFile(String path) throws IOException {
        File file = new File(path);
        if (file.isFile()) {
            boolean deleted = file.delete();
            if (!deleted) {
                throw new IOException("Deleting file failed.");
            }
        }
    }

    /**
     * Reads the Json file at 'path' into memory and returns the object
     */
    public static <T> T readJson(String path, Class<T> classOfT) {
        Object object = new Object();
        try {
            FileReader reader = new FileReader(path);
            Gson gson = new Gson();
            object = gson.fromJson(reader, classOfT);
        } catch (IOException e) {
            System.out.print("Failed to read test data from path: " + path);
        }
        return Primitives.wrap(classOfT).cast(object);
    }


    /**
     * Writes obj to path using googles GSON library, either in pretty print format or not
     */
    public static void writeJson(Object obj, String path, boolean prettyPrint) throws IOException {
        FileWriter aWriter = new FileWriter(path);
        GsonBuilder gb = new GsonBuilder();

        // Sets Json output to a human readable less condensed format
        if (prettyPrint) {
            gb.setPrettyPrinting();
        }
        Gson gson = gb.create();

        // Do writing
        gson.toJson(obj, aWriter);
        aWriter.flush();
        aWriter.close();
    }

    public static void writeJson(Object obj, String path) throws IOException{
        writeJson(obj, path, false);
    }
}
