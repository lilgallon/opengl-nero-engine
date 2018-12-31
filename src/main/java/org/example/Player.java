package org.example;

import io.github.n3roo.graphics.Graphics;
import io.github.n3roo.world.GameObject;

public class Player extends GameObject {

    @Override
    public void update(){
        x += 0.1f;
    }

    @Override
    public void render(){
        Graphics.fillRect(x, y, width, height);
    }

}
