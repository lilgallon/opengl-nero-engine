package samples.physics.world.entities;

import com.jogamp.newt.event.KeyEvent;
import io.github.n3roo.engine.GameLoop;
import io.github.n3roo.input.KeyInput;
import io.github.n3roo.input.MouseInput;
import io.github.n3roo.math.Force;
import io.github.n3roo.math.Polygon;
import io.github.n3roo.math.Vec2f;
import io.github.n3roo.world.GameObject;
import io.github.n3roo.world.components.RigidBody;


public class Triangle extends GameObject {

    public Triangle(){
        position = new Vec2f(0.5f, 0);
        rigidBody = new RigidBody(new Polygon(0.3f, 3, 0), 0);
        drawRigidBody = true;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void update() {
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
}
