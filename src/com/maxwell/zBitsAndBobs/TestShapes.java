package com.maxwell.zBitsAndBobs;

import org.lwjgl.opengl.GL45;

public class TestShapes {
    /**
     * Draws a large triangle using openGL, takes up most of the context.
     */
    public static void drawBigTriangle() {
        // Begin drawing
        GL45.glBegin(GL45.GL_TRIANGLES);
        // Top & Red
        GL45.glColor3f(1.0f, 0.0f, 0.0f);
        GL45.glVertex2f(0.0f, 1.0f);

        // Right & Green
        GL45.glColor3f(0.0f, 1.0f, 0.0f);
        GL45.glVertex2f(-1.0f, -1.0f);

        // Left & Blue
        GL45.glColor3f(0.0f, 0.0f, 1.0f);
        GL45.glVertex2f(1.0f, -1.0f);
        GL45.glEnd();
    }

    public static void drawSimpleLine() {
        GL45.glBegin(GL45.GL_LINES);

        GL45.glVertex2f(0.0f, -0.5f);
        GL45.glVertex2f(0.0f, 0.5f);

        GL45.glEnd();
    }
}
