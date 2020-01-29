package com.maxwell.display.mainwindow;

import com.maxwell.simulation.maths.objects.Vec3;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;

public class CallBackFunctions {

    // Setup a key callback, this function is called every time a key is pressed.
    public static void setKeyCallBack(Window window, long windowHandle) {
        glfwSetKeyCallback(windowHandle, (mainWindow, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(mainWindow, true); // We will detect this in the rendering loop
        });
    }


    public static void setScrollResizeCallback(Window window, long windowHandle, float scale_x, float scale_y, float scale_z) {
        Vec3 scale = new Vec3((double)scale_x, (double)scale_y, 0.0);
        glfwSetScrollCallback(windowHandle, (mainWindow, xoffset, yoffset) -> {
            scale.x(window.scale_x + (float) (window.scale_x * yoffset / 10));
            scale.y(window.scale_y + (float) (window.scale_y * yoffset / 10));
            scale.z(window.scale_z + (float) (window.scale_z * yoffset / 10));

            window.scale_x = (float) scale.x();
            window.scale_y = (float) scale.y();
            window.scale_z = (float) scale.z();
        });
    }
}
