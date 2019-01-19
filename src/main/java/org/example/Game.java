package org.example;

import io.github.n3roo.world.World;

public class Game {

    /**
     * This is the first method to be called when the game engine is ready.
     */
    public static void start(){
        long playerId = World.addGameObject(new Player());
    }
}
