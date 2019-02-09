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

        ArrayList<Vec2f> o = new ArrayList<Vec2f>();
        for(int i = 0; i < 5; i ++){
            o.add(new Vec2f((float) (30f * Math.cos(fTheta * i)), (float)(30f * Math.sin(fTheta * i))));
        }
        ArrayList<Vec2f> p = new ArrayList<Vec2f>(o);

        rigidBody = new RigidBody(new Polygon(p, pos, angle, o), 0);
    }

    @Override
    public void update() {
        // TODO
    }

}
