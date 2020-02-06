package com.maxwell.display.mainwindow;


import com.maxwell.display.drawing.Draw;
import org.lwjgl.opengl.*;
import org.lwjgl.glfw.*;

import java.awt.*;

import static com.maxwell.display.mainwindow.CallBackFunctions.setKeyCallBack;
import static com.maxwell.display.mainwindow.CallBackFunctions.setScrollResizeCallback;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL45C.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * The main window within which the graphics is displayed
 */
public class Window {

    // The handle to the main window
    private long mainWindow;
    private int winWidth = 0;
    private int winHeight = 0;
    public float scale_x = 1.0f;
    public float scale_y = 1.0f;
    public float scale_z = 1.0f;

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
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int windowWidth = (int) Math.round(screensize.getWidth() * 3/4);
        int windowHeight = (int) Math.round(screensize.getHeight() * 3/4);
        mainWindow = glfwCreateWindow(windowWidth, windowHeight, "Main Window", NULL, NULL);

        if (mainWindow == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        setKeyCallBack(this, mainWindow);
        setScrollResizeCallback(this, mainWindow, scale_x, scale_y, scale_z);

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

        // While resizing, dynamically redraws the contents of the glfwWindow
        glfwSetWindowSizeCallback(mainWindow, (mainWindow, width, height) -> {
            glViewport(0, 0, width, height);
            Draw.doDraw(mainWindow);
        });
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
            Draw.doDraw(mainWindow);
            glfwSwapBuffers(mainWindow);
            // Poll for window events. The key callback above will only be invoked during this call.
            glfwPollEvents();
        }
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