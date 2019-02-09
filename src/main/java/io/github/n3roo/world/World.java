package io.github.n3roo.world;

import io.github.n3roo.graphics.Graphics;
import io.github.n3roo.math.Force;
import io.github.n3roo.math.Polygon;
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
     * @param go1 game object,
     * @param vec movement vector.
     */
    private static void moveGameObject(GameObject go1, Vec2f vec){
        // We need to check if the game object as a rigid body, if it doesn't, we don't need to handle collision
        if(go1.getRigidBody() == null){
            go1.move(vec);
        }else{ // Now we know that we need to handle collisions

            // TODO: There is still an optimization where we only check the collision with objects that weren't compared:
            //  m stands for the number of objects that can collide
            //  for 0..m
            //      for n+1..m
            //          check collision
            //  At the moment we do this this way :
            //  for i:0..m
            //      for j:0..m (without i=j)
            //          check collision

            for(Map.Entry<Long, GameObject> gameObjectEntry : gameObjects.entrySet()){
                GameObject go2 = gameObjectEntry.getValue();
                if(go1 == go2) continue;

                Vec2f overlapVec = polygonOverlapSat(go1, go2);

                if(overlapVec != null){
                    go1.position.add(overlapVec);
                }
            }
            go1.move(vec);
        }
    }

    /**
     * Collision detection using Separated Axis Theorem (SAT).
     * @return a vector containing the values of overlapping (null means no overlapping)
     */
    private static Vec2f polygonOverlapSat(GameObject go1, GameObject go2) {

        // 2D Rotation and 2D translation
        Polygon poly1 = new Polygon(go1.getRigidBody().getPolygon().getWorldPoints(go1.getPosition(), go1.getRotation()));
        Polygon poly2 = new Polygon(go2.getRigidBody().getPolygon().getWorldPoints(go2.getPosition(), go2.getRotation()));

        Polygon p1 = poly1;
        Polygon p2 = poly2;

        float overlap = Float.MAX_VALUE;

        for(int poly = 0; poly < 2; poly ++){
            if(poly == 1){
                p1 = new Polygon(poly2);
                p2 = new Polygon(poly1);
            }

            // a -> edge 1
            // b -> edge 2
            for(int a = 0; a < p1.points.size(); a ++){
                int b = (a + 1) % p1.points.size();
                Vec2f axisProj = new Vec2f( -(p1.points.get(b).y - p1.points.get(a).y), p1.points.get(b).x - p1.points.get(a).x);
                float d = (float) Math.sqrt(axisProj.x * axisProj.x + axisProj.y * axisProj.y);
                axisProj = new Vec2f( axisProj.x / d, axisProj.y / d);

                // Work out min and max 1D points for p1
                float min_p1 = Float.MAX_VALUE;
                float max_p1 = - Float.MAX_VALUE;

                for(int p = 0; p < p1.points.size(); p++){
                    float q = (p1.points.get(p).x * axisProj.x + p1.points.get(p).y * axisProj.y);
                    min_p1 = Math.min(min_p1, q);
                    max_p1 = Math.max(max_p1, q);
                }

                // Work out min and max 1D points for p2
                float min_p2 = Float.MAX_VALUE;
                float max_p2 = - Float.MAX_VALUE;

                for(int p = 0; p < p2.points.size(); p++){
                    float q = (p2.points.get(p).x * axisProj.x + p2.points.get(p).y * axisProj.y);
                    min_p2 = Math.min(min_p2, q);
                    max_p2 = Math.max(max_p2, q);
                }

                // Calculate actual overlap along projected axis, and store the minimum
                overlap = Math.min(Math.min(max_p1, max_p2) - Math.max(min_p1, min_p2), overlap);

                if(!(max_p2 >= min_p1 && max_p1 >= min_p2))
                    return null;
            }
        }

        // If we got here, the objects have collided, we will displace p1 by overlap along the vector between the two
        // objects centers.
        Vec2f d = new Vec2f(go2.getPosition().x - go1.getPosition().x, go2.getPosition().y - go1.getPosition().y);
        float s = (float) Math.sqrt(d.x * d.x + d.y * d.y);

        return new Vec2f(- overlap * d.x / s, - overlap * d.y / s);
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
