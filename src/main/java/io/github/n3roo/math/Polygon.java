package io.github.n3roo.math;

import java.util.ArrayList;

public class Polygon {

    // Position of shape
    public Vec2f pos;
    // Direction of shape
    public float angle;
    // Model of shape (relative to pos)
    public ArrayList<Vec2f> points;

    public Polygon(Vec2f pos, ArrayList<Vec2f> points, float angle){
        this.points = points;
        this.pos = pos;
        this.angle = angle;
    }

}
