package org.example;

import com.jogamp.newt.event.KeyEvent;
import io.github.n3roo.engine.GameLoop;
import io.github.n3roo.graphics.Animation;
import io.github.n3roo.input.KeyInput;
import io.github.n3roo.input.MouseInput;
import io.github.n3roo.math.Force;
import io.github.n3roo.world.components.RigidBody;
import io.github.n3roo.math.Vec2f;
import io.github.n3roo.world.GameObject;

import java.util.ArrayList;

public class Player extends GameObject {

    public Player(){
        ArrayList<String> sprites = new ArrayList<String>();
        sprites.add("testsprite_1.png");
        sprites.add("testsprite_2.png");

        animations = new Animation[1];
        animations[0] = new Animation(sprites, 8);
    }

    @Override
    public void update(){
        float xInput= 0;
        float yInput = 0;

        if(KeyInput.getKey(KeyEvent.VK_A)){
            xInput --;
        }

        if(KeyInput.getKey(KeyEvent.VK_D)){
            xInput ++;
        }

        if(KeyInput.getKey(KeyEvent.VK_W)){
            yInput ++;
        }

        if(KeyInput.getKey(KeyEvent.VK_S)){
            yInput --;
        }

        addForce(new Vec2f(xInput * GameLoop.updateDelta(), yInput * GameLoop.updateDelta()), Force.Mode.Velocity);

        rotation = (float) Math.toDegrees(Math.atan2(MouseInput.getWorldX() - position.x, MouseInput.getWorldY() - position.y));
    }

    public RigidBody getRigidBody() {
        return new RigidBody(position.x, position.y, 1f, 0.5f);
    }

}
