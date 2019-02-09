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

    public Polygon(Polygon polygon){
        this.points = new ArrayList<Vec2f>(polygon.points);
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

    /**
     * It takes the points and achieves a translation and rotation to get the world points (absolutes values instead of
     * relative values)
     * @param pos the position of the polygon in the world,
     * @param rotation the rotation of the polygon in the world.
     * @return the points list (absolute values).
     */
    public ArrayList<Vec2f> getWorldPoints(Vec2f pos, float rotation){
        ArrayList<Vec2f> worldPoints = new ArrayList<Vec2f>(points);

        for(int i = 0; i < worldPoints.size(); i ++){
            float teta = - rotation * (float) Math.PI / 180f;
            // Rotation
            float x = (worldPoints.get(i).x * (float) Math.cos(teta)) - (worldPoints.get(i).y * (float) Math.sin(teta));
            float y = (worldPoints.get(i).x * (float) Math.sin(teta)) + (worldPoints.get(i).y * (float) Math.cos(teta));
            // Translation
            x += pos.x;
            y += pos.y;

            worldPoints.set(i, new Vec2f(x, y));
        }

        return worldPoints;
    }

}
