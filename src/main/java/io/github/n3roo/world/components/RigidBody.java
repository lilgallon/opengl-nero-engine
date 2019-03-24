package io.github.n3roo.world.components;

import io.github.n3roo.math.Polygon;

public class RigidBody {

    /**
     * Shape of this rigid body.
     */
    private Polygon polygon;

    /**
     * Mass of this rigid body.
     */
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

    /**
     * @return the shape of this rigid body.
     */
    public Polygon getPolygon(){
        return this.polygon;
    }

    /**
     * @return the mass of this rigid body.
     */
    public float getMass(){
        return this.mass;
    }
}
