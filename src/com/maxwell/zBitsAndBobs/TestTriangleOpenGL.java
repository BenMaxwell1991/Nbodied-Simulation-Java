package com.maxwell.zBitsAndBobs;

import org.lwjgl.opengl.*;
import org.lwjgl.glfw.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL45C.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * The main window within which the graphics is displayed
 */
public class TestTriangleOpenGL {

    // The handle to the main window
    private long mainWindow;
    private int winWidth = 0;
    private int winHeight = 0;

    public void showMainWindow() {
        initGLFW();
        initGL();
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(mainWindow);
        glfwDestroyWindow(mainWindow);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void initGLFW() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        mainWindow = glfwCreateWindow(2880, 1620, "Main Window", NULL, NULL);

        if (mainWindow == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(mainWindow, (mainWindow, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(mainWindow, true); // We will detect this in the rendering loop
        });

        // Get the thread stack and push a new frame
        setWindowSize();;

        // Get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        // Center the window
        glfwSetWindowPos(mainWindow,
                (vidmode.width() - winWidth) / 2,
                (vidmode.height() - winHeight) / 2);

        // Make the OpenGL context current
        glfwMakeContextCurrent(mainWindow);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(mainWindow);
    }

    private void initGL() {
        // This is vital in order to use any of the OpenGL bindings
        GLCapabilities capabilities = GL.createCapabilities();

        GL45.glMatrixMode(GL45.GL_PROJECTION);
        GL45.glLoadIdentity();
        GL45.glOrtho(-1, 1, -1, 1, 1, -1);
        GL45.glMatrixMode(GL45.GL_MODELVIEW);
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glViewport(0, 0, winWidth, winHeight);
    }

    private void loop() {

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(mainWindow) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            drawBigTriangleGL();
            glfwSwapBuffers(mainWindow); // swap the color buffers

            glfwSetWindowSizeCallback(mainWindow, (mainWindow, width, height) -> {
                glViewport(0, 0, width, height);
                drawBigTriangleGL();
                glfwSwapBuffers(mainWindow);
            });

            // Poll for window events. The key callback above will only be invoked during this call.
            glfwPollEvents();
        }
    }

    /**
     * Draws a large triangle using openGL, takes up most of the context.
     */
    private void drawBigTriangleGL() {
        setWindowSize();

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

    /**
     * Sets winWidth and winHeight to the current height of the context window
     */
    private void setWindowSize() {
        int[] w = new int[1];
        int[] h = new int[1];
        glfwGetWindowSize(mainWindow, w, h);
        winWidth = w[0];
        winHeight = h[0];
    }
}