package com.maxwell.display.screenresolution;

public class ScreenResolution {

    private int width;
    private int height;

    protected ScreenResolution(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public void setWidth(int value) { width = value; }
    public void setHeight(int value) { height = value; }

    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }
}
