package io.github.n3roo.math;

import java.util.ArrayList;

public class Polygon {

    // Model of shape (relative to pos)
    public ArrayList<Vec2f> points;

    /**
     * It creates a polygon will all its points. If you want to build a simple polygon, you should use the other
     * constructor : Polygon(Vec2f pos, float radius, float edges, float angle)
     * @param points the points of the polygon.
     */
    public Polygon(ArrayList<Vec2f> points){
        this.points = points;
    }

    /**
     * It creates a polygon with the number of edges and the radius given.
     * @param radius the radius of the circle on which the edges will be taken,
     * @param edges the number of edges that need to be taken,
     * @param angle the offset angle for the edges.
     */
    public Polygon(float radius, int edges, float angle){
        float fTheta = (float) (Math.PI * 2) / edges + angle;

        this.points = new ArrayList<Vec2f>();
        for(int i = 0; i < edges; i ++){
            this.points.add(new Vec2f((float) (radius * Math.cos(fTheta * i)), (float)(radius * Math.sin(fTheta * i))));
        }
    }

}
