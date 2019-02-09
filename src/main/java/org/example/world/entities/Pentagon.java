package org.example.world.entities;

import io.github.n3roo.math.Polygon;
import io.github.n3roo.math.Vec2f;
import io.github.n3roo.world.GameObject;
import io.github.n3roo.world.components.RigidBody;

import java.util.ArrayList;

public class Pentagon extends GameObject {

    public Pentagon(){
        Vec2f pos = new Vec2f(-0.5f, 0);
        float angle = 0f;
        float fTheta = (float) Math.PI * 2f / 5f;

        ArrayList<Vec2f> points = new ArrayList<Vec2f>();
        for(int i = 0; i < 5; i ++){
            points.add(new Vec2f((float) (0.3f * Math.cos(fTheta * i)), (float)(0.3f * Math.sin(fTheta * i))));
        }

        rigidBody = new RigidBody(new Polygon(pos, points, angle), 0);
    }

    @Override
    public void update() {
        // TODO
    }

}
