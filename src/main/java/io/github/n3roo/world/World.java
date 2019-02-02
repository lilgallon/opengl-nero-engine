package io.github.n3roo.world;

import io.github.n3roo.math.Force;
import io.github.n3roo.math.Vec2f;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class World {

    private static ConcurrentLinkedQueue<Tile> tiles = new ConcurrentLinkedQueue<Tile>();

    /**
     * A container with all the game objects linked to their ids to access them easily.
     */
    private static Map<Long, GameObject> gameObjects = new ConcurrentHashMap<Long, GameObject>();

    private static long availableId = 0;

    /**
     * This variable is used to slow down the forces.
     */
    private static float friction = 0.95f;

    public static void update(){

        // Go through all the entities and update them
        for(Map.Entry<Long, GameObject> gameObjectEntry : gameObjects.entrySet()){
            GameObject gameObject = gameObjectEntry.getValue();

            // Force management
            Vec2f movement = gameObject.getMovement();
            // We need to apply friction before
            movement.multiply(friction);
            // Then we will check all the forces
            Vec2f constantMovement = new Vec2f(0, 0);
            Stack<Force> forces = gameObject.getForces();
            ArrayList<Force> persistentForces = new ArrayList<Force>();
            while(!forces.empty()){
                Force force = forces.pop();
                switch (force.getMode()){
                    case Persistent:
                        constantMovement.add(force.getVector());
                        persistentForces.add(force);
                        break;
                    case Velocity:
                        constantMovement.add(force.getVector());
                        break;
                    case Impulse:
                        movement.add(force.getVector());
                        break;
                }
            }
            // Then, we can move
            constantMovement.add(movement);
            moveGameObject(gameObject, constantMovement);
            // And set the current movement vector to the force vector containing all the impulse forces
            gameObject.setMovement(movement);
            // We put back all the persistent forces
            forces.addAll(persistentForces);
            gameObject.setForces(forces);

            gameObject.update();
        }
    }

    public static void render(){

        // Render all the tiles
        for(Tile tile : tiles){
            tile.render();
        }

        // Go through all the entities and render them
        for(Map.Entry<Long, GameObject> gameObject : gameObjects.entrySet()){
            gameObject.getValue().render();
        }
    }

    /**
     * It moves an object by taking in account collisions.
     * @param go game object,
     * @param vec movement vector.
     */
    private static void moveGameObject(GameObject go, Vec2f vec){
        // We need to check if the game object as a rigid body, if it doesn't, we don't need to handle collision
        if(go.getRigidBody() == null){
            go.move(vec);
        }

        // Now we know that we need to handle collisions
        // todo
        else{
            go.move(vec);
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
     * It adds a tile to the world.
     * @param tile the tile to add.
     */
    public static void addTile(Tile tile){
        tiles.offer(tile);
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
