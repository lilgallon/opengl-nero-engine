package io.github.n3roo.world;

import java.util.ArrayList;

public class World {

    private static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

    public static void update(){

        // Go through all the entities and update them
        for(GameObject gameObject : gameObjects){
            gameObject.update();
        }
    }

    public static void render(){

        // Go through all the entities and render them
        for(GameObject gameObject : gameObjects){
            gameObject.render();
        }
    }

    public static void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }
}
