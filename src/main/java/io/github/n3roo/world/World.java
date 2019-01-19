package io.github.n3roo.world;

import java.util.HashMap;
import java.util.Map;

public class World {

    /**
     * A container with all the game objects linked to their ids to access them easily.
     */
    private static Map<Long, GameObject> gameObjects = new HashMap<Long, GameObject>();

    private static long availableId = 0;

    public static void update(){

        // Go through all the entities and update them
        for(Map.Entry<Long, GameObject> gameObject : gameObjects.entrySet()){
            gameObject.getValue().update();
        }
    }

    public static void render(){

        // Go through all the entities and render them
        for(Map.Entry<Long, GameObject> gameObject : gameObjects.entrySet()){
            gameObject.getValue().render();
        }
    }

    /**
     * It adds a gameObject to the world, and returns its id. Ths id can be used trough getGameObject(long id) to get
     * the gameObject.
     * @param gameObject the gameObject to add.
     * @return gameObject id.
     */
    public static long addGameObject(GameObject gameObject) {
        if(gameObject == null){
            throw new IllegalArgumentException("Can't add a null game object to the world.");
        }

        gameObjects.put(availableId, gameObject);
        availableId ++;
        return availableId - 1;
    }

    /**
     * It retrieve the game object associated to the given id.
     * @param id the id of the game object.
     * @return the game object if it was found.
     */
    public static GameObject getGameObject(long id){
        if(id >= availableId){
            throw new IllegalArgumentException("The id given is greater or equal to the first available id. The gameobject is unknown.");
        }else{
            GameObject gameObject = gameObjects.get(id);
            if(gameObject == null){
                throw new IllegalArgumentException("The gameobject of id " + id + " is unknown.");
            }else{
                return gameObjects.get(id);
            }
        }
    }
}
