package io.github.n3roo.engine;

import io.github.n3roo.graphics.Renderer;
import io.github.n3roo.world.World;
import org.example.TestPlayer;

public class Main {

    public static void main(String[] args){
        Renderer.init();
        GameLoop.start();

        // Test code
        World.addGameObject(new TestPlayer());
    }
}
