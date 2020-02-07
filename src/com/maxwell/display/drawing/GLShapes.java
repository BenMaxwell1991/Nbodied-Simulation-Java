package com.maxwell.display.drawing;

import org.lwjgl.opengl.GL45;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.awt.*;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL15C.*;

public class GLShapes {

    // Loop through all of the verticies (supplied x1,y1,x2,y2,x3,y3,x4,y4...) drawing as we go
    public static void drawOpenPolygon2D(float[] vertices, int lineWidth, Color c) {

        // Initialize
        float[] lineColour = new float[4];
        int vertexNumber = vertices.length / 2;
        int vbo = getVBO();

        // Generate FloatBuffer
        FloatBuffer fb = HelperFunctions.floatToFloatBuffer(vertices);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, fb, GL_STATIC_DRAW);

        // Configure Settings
        GL45.glLineWidth(lineWidth);
        c.getComponents(lineColour);
        GL45.glColor3f(lineColour[0], lineColour[1], lineColour[2]);

        // Render
        GL45.glEnableClientState(GL45.GL_VERTEX_ARRAY);
        GL45.glVertexPointer(2, GL45.GL_FLOAT, 8, 0);
        GL45.glDrawArrays(GL45.GL_LINE_STRIP, 0, vertexNumber);
    }


    // gets a Vertex Buffer Object
    private static int getVBO() {
        int vbo;
        try(MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer ip = stack.callocInt(1);
            glGenBuffers(ip);
            vbo = ip.get(0);
        }
        return vbo;
    }
}




























