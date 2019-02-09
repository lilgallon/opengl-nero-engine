package org.example.physics.world.entities;

import io.github.n3roo.math.Polygon;
import io.github.n3roo.math.Vec2f;
import io.github.n3roo.world.GameObject;
import io.github.n3roo.world.components.RigidBody;


public class Triangle extends GameObject {

    public Triangle(){
        rigidBody = new RigidBody(new Polygon(new Vec2f(0.5f, 0.5f), 0.3f, 3, 0), 0);
    }

    @Override
    public void update() {
        // TODO
    }
}
