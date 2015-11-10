package com.github.quadflask.smartcrop;

public class Crop {
    public int x, y, width, height;
    public Score score;

    public Crop(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}