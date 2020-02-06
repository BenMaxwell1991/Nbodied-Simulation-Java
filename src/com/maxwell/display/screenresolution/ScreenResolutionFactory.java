package com.maxwell.display.screenresolution;

import java.awt.*;

public class ScreenResolutionFactory {

    private ScreenResolutionFactory() {

    }

    // Generate a ScreenResolution object that takes into account the window scaling factor
    public static ScreenResolution getScreenResolution() {

        int res = java.awt.Toolkit.getDefaultToolkit().getScreenResolution();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int scale = res / 96;

        int windowWidth = (int) Math.round(screenSize.getWidth() * scale * 3 / 4);
        int windowHeight = (int) Math.round(screenSize.getHeight() * scale * 3 / 4);

        return new ScreenResolution(windowWidth, windowHeight);
    }
}
