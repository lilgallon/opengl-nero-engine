package io.github.n3roo.engine;

import io.github.n3roo.graphics.Renderer;

public class Main {

    public static void main(String[] args){
        Renderer.init();
        GameLoop.start();
    }
}
