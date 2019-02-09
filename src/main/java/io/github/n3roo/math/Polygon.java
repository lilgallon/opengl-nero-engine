package io.github.n3roo.math;

import java.util.ArrayList;

public class Polygon {

    // Transformed points (world space)
    public ArrayList<Vec2f> p;
    // Position of shape
    public Vec2f pos;
    // Direction of shape
    float angle;
    // Model of shape (relative to pos)
    ArrayList<Vec2f> o;

    public Polygon(ArrayList<Vec2f> p, Vec2f pos, float angle, ArrayList<Vec2f> o){
        this.p = p;
        this.pos = pos;
        this.angle = angle;
        this.o = o;
    }

}
