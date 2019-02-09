package org.example.physics;

import io.github.n3roo.world.World;
import org.example.physics.world.entities.Pentagon;
import org.example.physics.world.entities.Triangle;

public class PhysicsGame {

    public static void start(){
        long triangleId = World.addGameObject(new Triangle());
        long pentagonId = World.addGameObject(new Pentagon());
    }
}
