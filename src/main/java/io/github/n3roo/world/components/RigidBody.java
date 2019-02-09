package io.github.n3roo.world.components;

import io.github.n3roo.math.Polygon;

public class RigidBody {

    private Polygon polygon;

    private float mass;

    /**
     * It creates a collision box. It is a square, with the (x, y) position being the top left corner.
     * @param polygon the collision polygon,
     * @param mass the mass of the rigid body. (value < 0 means an infinite mass)
     */
    public RigidBody(Polygon polygon, float mass){
        this.polygon = polygon;
        this.mass = mass;
    }

    public Polygon getPolygon(){
        return this.polygon;
    }

    public float getMass(){
        return this.mass;
    }
}
