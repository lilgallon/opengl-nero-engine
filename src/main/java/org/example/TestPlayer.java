package org.example;

import com.jogamp.newt.event.KeyEvent;
import io.github.n3roo.engine.GameLoop;
import io.github.n3roo.graphics.Animation;
import io.github.n3roo.input.KeyInput;
import io.github.n3roo.input.MouseInput;
import io.github.n3roo.world.GameObject;

import java.util.ArrayList;

public class TestPlayer extends GameObject {

    public TestPlayer(){
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

        x += xInput * GameLoop.updateDelta();
        y += yInput * GameLoop.updateDelta();

        rotation = (float) Math.toDegrees(Math.atan2(MouseInput.getWorldX() - x, MouseInput.getWorldY() - y));
    }

}
