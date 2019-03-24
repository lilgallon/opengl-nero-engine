package samples.shooter.world.entities;

import com.jogamp.newt.event.KeyEvent;
import io.github.n3roo.engine.GameLoop;
import io.github.n3roo.graphics.Animation;
import io.github.n3roo.graphics.Renderer;
import io.github.n3roo.input.KeyInput;
import io.github.n3roo.input.MouseInput;
import io.github.n3roo.math.Force;
import io.github.n3roo.math.Polygon;
import io.github.n3roo.world.World;
import io.github.n3roo.world.components.RigidBody;
import io.github.n3roo.math.Vec2f;
import io.github.n3roo.world.GameObject;

import java.util.ArrayList;

public class Player extends GameObject {

    private GameObject legs;

    private static int IDLE;
    private static int MOVE;

    public Player(){
        // Collision box
        ArrayList<Vec2f> points = new ArrayList<>();
        points.add(new Vec2f(-0.1f, -0.1f));
        points.add(new Vec2f(-0.1f, +0.1f));
        points.add(new Vec2f(+0.1f, +0.1f));
        points.add(new Vec2f(+0.1f, -0.1f));

        rigidBody = new RigidBody(new Polygon(points), 0);

        // Animation
        animations = new Animation[2];

        // Idle animation
        IDLE = 0;
        ArrayList<String> idleFrames = new ArrayList<>();
        for(int i = 0; i < 20; i ++){
            idleFrames.add("rifle/idle/survivor-idle_rifle_" + i + ".png");
        }
        animations[IDLE] = new Animation(idleFrames, 15);

        // Move animation
        MOVE = 1;
        ArrayList<String> moveFrames = new ArrayList<>();
        for(int i = 0; i < 20; i ++){
            moveFrames.add("rifle/move/survivor-move_rifle_" + i + ".png");
        }
        animations[MOVE] = new Animation(moveFrames, 50);

        // Fix sprite rotation so it points the cursor
        graphicsRotation = -90;

        // Legs
        legs = new GameObject();
        legs.setPosition(this.position.x, this.position.y); // it follows automatically
        legs.setWidth(width * 0.7f);
        legs.setHeight(height * 0.7f);
        World.addGameObject(legs);

        Animation[] legsAnimations = new Animation[2];

        // Idle Animation
        ArrayList<String> fIdleFrames = new ArrayList<>();
        fIdleFrames.add("feet/idle/survivor-idle_0.png");
        legsAnimations[IDLE] = new Animation(fIdleFrames, 1);

        // Run animation
        ArrayList<String> fMoveFrames = new ArrayList<>();
        for(int i = 0; i < 20; i ++){
            fMoveFrames.add("feet/run/survivor-run_" + i + ".png");
        }
        legsAnimations[MOVE] = new Animation(fMoveFrames, 50);

        legs.setAnimations(legsAnimations);
    }

    @Override
    @SuppressWarnings("Duplicates")
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
            legs.setCurrentAnimation(MOVE);
        }else{
            currentAnimation = IDLE;
            legs.setCurrentAnimation(IDLE);
        }


        addForce(new Vec2f(xInput * GameLoop.updateDelta(), yInput * GameLoop.updateDelta()), Force.Mode.Velocity);

        rotation = (float) Math.toDegrees(Math.atan2(MouseInput.getWorldX() - position.x, MouseInput.getWorldY() - position.y));

        legs.setRotation(rotation - 90);
        legs.setPosition(this.position.x, this.position.y - 0.05f);

        Renderer.camera.x = this.position.x;
        Renderer.camera.y = this.position.y;
    }

}
