package org.example.world.entities;

import io.github.n3roo.math.Polygon;
import io.github.n3roo.math.Vec2f;
import io.github.n3roo.world.GameObject;
import io.github.n3roo.world.components.RigidBody;

import java.util.ArrayList;

public class Triangle extends GameObject {

    public Triangle(){
        Vec2f pos = new Vec2f(0.5f, 0.5f);
        float angle = 0f;
        float radius = 0.2f;
        float edges = 3f;
        float fTheta = (float) (Math.PI * 2) / edges;

        ArrayList<Vec2f> points = new ArrayList<Vec2f>();
        for(int i = 0; i < edges; i ++){
            points.add(new Vec2f((float) (radius * Math.cos(fTheta * i)), (float)(radius * Math.sin(fTheta * i))));
        }

        System.out.println(points);

        rigidBody = new RigidBody(new Polygon(pos, points, angle), 0);
    }

    @Override
    public void update() {
        // TODO
    }
}
