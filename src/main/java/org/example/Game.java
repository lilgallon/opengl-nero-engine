package org.example;

import io.github.n3roo.world.World;
import org.example.world.entities.Pentagon;
import org.example.world.entities.Player;
import org.example.world.entities.Triangle;
import org.example.world.tiles.GrassTile;

public class Game {

    /**
     * This is the first method to be called when the game engine is ready.
     */
    public static void start(){

        for(int x = 0; x < 10; x++) {
            for(int y = 0; y < 10; y++) {
                GrassTile tile = new GrassTile();
                tile.setPosition(tile.getWidth() * x, tile.getHeight() * y);
                World.addTile(tile);
            }
        }

        long playerId = World.addGameObject(new Player());
        long triangleId = World.addGameObject(new Triangle());
        long pentagonId = World.addGameObject(new Pentagon());
    }
}
