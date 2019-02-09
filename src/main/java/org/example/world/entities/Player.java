package org.example.world.entities;

import com.jogamp.newt.event.KeyEvent;
import io.github.n3roo.engine.GameLoop;
import io.github.n3roo.graphics.Animation;
import io.github.n3roo.graphics.Renderer;
import io.github.n3roo.input.KeyInput;
import io.github.n3roo.input.MouseInput;
import io.github.n3roo.math.Force;
import io.github.n3roo.world.components.RigidBody;
import io.github.n3roo.math.Vec2f;
import io.github.n3roo.world.GameObject;

import java.util.ArrayList;

public class Player extends GameObject {

    private static int IDLE;
    private static int MOVE;

    public Player(){
        animations = new Animation[2];

        // Idle animation
        IDLE = 0;
        ArrayList<String> idleFrames = new ArrayList<String>();
        for(int i = 0; i < 20; i ++){
            idleFrames.add("rifle/idle/survivor-idle_rifle_" + i + ".png");
        }
        animations[IDLE] = new Animation(idleFrames, 15);

        // Move animation
        MOVE = 1;
        ArrayList<String> moveFrames = new ArrayList<String>();
        for(int i = 0; i < 20; i ++){
            moveFrames.add("rifle/move/survivor-move_rifle_" + i + ".png");
        }
        animations[MOVE] = new Animation(moveFrames, 50);

        // Fix sprite rotation so it points the cursor
        graphicsRotation = -90;
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

        if(xInput != 0 || yInput != 0){
            currentAnimation = MOVE;
        }else{
            currentAnimation = IDLE;
        }

        addForce(new Vec2f(xInput * GameLoop.updateDelta(), yInput * GameLoop.updateDelta()), Force.Mode.Velocity);

        rotation = (float) Math.toDegrees(Math.atan2(MouseInput.getWorldX() - position.x, MouseInput.getWorldY() - position.y));

        Renderer.cameraX = this.position.x;
        Renderer.cameraY = this.position.y;
    }

    public RigidBody getRigidBody() {
        return new RigidBody(position.x, position.y, 1f, 0.5f, 0);
    }

}
