package io.github.n3roo.engine;

import io.github.n3roo.graphics.Renderer;
import org.example.Game;

public class Main {

    public static void main(String[] args){
        Renderer.init();
        GameLoop.start();

        // User class
        Game.start();
    }
}
