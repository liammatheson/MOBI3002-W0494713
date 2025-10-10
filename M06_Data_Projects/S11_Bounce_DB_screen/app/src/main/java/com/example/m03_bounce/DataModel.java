package com.example.m03_bounce;

// Object DB ... see Room for Android Studio
// https://developer.android.com/training/data-storage/room
public class DataModel {

    // Data Model for a Ball with position and velocity (x,y,dx,dy)
    float x;                // Ball's center (x,y)
    float y;
    float dx;           // Ball's speed
    float dy;
    int color;
    String name;


    public DataModel(Float x, Float y, Float dx, Float dy, int color, String name) {
        this.setModelPosVel(x, y, dx, dy);
        this.color = color;
        this.name = name;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                ", x='" + x + '\'' +
                ", y=" + y +
                ", dx='" + dx + '\'' +
                ", dy=" + dy +
                ", name=" + name;
    }

    public void setModelPosVel(Float x, Float y, Float dx, Float dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    public float getModelX() {
        return this.x;
    }

    public float getModelY() {
        return this.y;
    }

    public float getModelDX() {
        return this.dx;
    }

    public float getModelDY() {
        return this.dy;
    }

    public int getColor() {return color;}

    public void setColor(int color) {this.color = color;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

}
