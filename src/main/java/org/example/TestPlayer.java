package org.example;

import com.jogamp.newt.event.KeyEvent;
import io.github.n3roo.engine.GameLoop;
import io.github.n3roo.graphics.Animation;
import io.github.n3roo.input.KeyInput;
import io.github.n3roo.input.MouseInput;
import io.github.n3roo.resources.ImageResource;
import io.github.n3roo.world.GameObject;

public class TestPlayer extends GameObject {

    public TestPlayer(){
        animations = new Animation[1];
        animations[0] = new Animation();
        animations[0].frames = new ImageResource[2];
        animations[0].frames[0] = new ImageResource("testsprite_1.png");
        animations[0].frames[1] = new ImageResource("testsprite_2.png");
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

        x += xInput * GameLoop.updateDelta();
        y += yInput * GameLoop.updateDelta();

        rotation = (float) Math.toDegrees(Math.atan2(MouseInput.getWorldX() - x, MouseInput.getWorldY() - y));
    }

}
