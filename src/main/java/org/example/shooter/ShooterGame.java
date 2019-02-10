package org.example.shooter;

import io.github.n3roo.world.World;
import org.example.shooter.world.entities.Player;
import org.example.physics.world.entities.Triangle;
import org.example.shooter.world.tiles.GrassTile;

public class ShooterGame {

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

        World.addGameObject(new Player());
    }
}
