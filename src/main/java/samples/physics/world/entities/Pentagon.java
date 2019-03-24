package samples.physics.world.entities;

import io.github.n3roo.math.Polygon;
import io.github.n3roo.math.Vec2f;
import io.github.n3roo.world.GameObject;
import io.github.n3roo.world.components.RigidBody;

public class Pentagon extends GameObject {

    public Pentagon(){
        position = new Vec2f(-0.5f, 0);
        rigidBody = new RigidBody(new Polygon(0.5f, 5, 0), 0);
        drawRigidBody = true;
    }

    @Override
    public void update() {
        // TODO
    }

}
