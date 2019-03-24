package samples.physics;

import io.github.n3roo.hud.components.FpsCounter;
import io.github.n3roo.world.World;
import samples.physics.world.entities.Pentagon;
import samples.physics.world.entities.Platform;
import samples.physics.world.entities.Triangle;

public class PhysicsGame {

    public static void start(){
        // Borders
        long borderTop = World.addGameObject(new Platform());

        // Entities
        long triangleId = World.addGameObject(new Triangle());
        long pentagonId = World.addGameObject(new Pentagon());

        // Hud components
        long fpsCounterId = World.addHudComponent(new FpsCounter());
    }
}
