package org.example.physics;

import io.github.n3roo.world.World;
import org.example.physics.world.entities.Pentagon;
import org.example.physics.world.entities.Platform;
import org.example.physics.world.entities.Triangle;

public class PhysicsGame {

    public static void start(){

        // Entities
        World.addGameObject(new Pentagon());
        World.addGameObject(new Triangle());

        // Borders
        World.addGameObject(new Platform());
    }
}
