package io.github.n3roo.world;

import io.github.n3roo.graphics.Animation;
import io.github.n3roo.graphics.Graphics;
import io.github.n3roo.math.Force;
import io.github.n3roo.math.Polygon;
import io.github.n3roo.world.components.RigidBody;
import io.github.n3roo.math.Vec2f;

import java.util.Stack;

public class GameObject {

    // The position of the entity
    protected Vec2f position = new Vec2f(0, 0);

    // The size of the entity
    protected float width = 1;
    protected float height = 1;

    // The rotation (in degrees)
    protected float rotation = 0;

    // Rotation offset of the renderer
    protected float graphicsRotation = 0;

    // Animations
    protected Animation[] animations;
    protected int currentAnimation = 0;

    // Rigid body (used for collision)
    protected RigidBody rigidBody = null;
    protected boolean drawRigidBody = false;

    // Force handling
    protected Stack<Force> forces = new Stack<Force>();
    protected Vec2f movement = new Vec2f(0, 0);

    public void update(){

    }

    public void render(){
        if(animations != null) {
            animations[currentAnimation].play();

            Graphics.setRotation(rotation + graphicsRotation);
            Graphics.setColor(1, 1, 1, 1);
            Graphics.drawImage(animations[currentAnimation].getImage(), position.x, position.y, width, height);
            Graphics.setRotation(0);
        }

        if(getRigidBody() != null && drawRigidBody) {
            Graphics.setColor(1, 0, 0, 1f);
            Graphics.setRotation(rotation);
            Graphics.setTranslation(this.position.x, this.position.y);
            int size = getRigidBody().getPolygon().points.size();
            for(int i = 0; i < size; i ++){
                Graphics.fillLine(getRigidBody().getPolygon().points.get(i).x,
                        getRigidBody().getPolygon().points.get(i).y,
                        getRigidBody().getPolygon().points.get((i + 1) % size).x,
                        getRigidBody().getPolygon().points.get((i + 1) % size).y);
            }
            Graphics.setRotation(0);
            Graphics.setTranslation(0, 0);
        }
    }

    /**
     * It adds a force to the game object.
     * @param force the force to apply.
     */
    public void addForce(Force force){
        forces.push(force);
    }

    /**
     * It adds a force to the game object.
     * @param vector the force vector,
     * @param mode the force mode.
     */
    public void addForce(Vec2f vector, Force.Mode mode){
        addForce(new Force(vector, mode));
    }

    public Stack<Force> getForces(){
        return forces;
    }

    public void setForces(Stack<Force> forces){
        this.forces = forces;
    }

    public void clearForces(){
        forces = new Stack<Force>();
    }

    public void removePersistentForce(Force force){
        if(force.getMode() == Force.Mode.Persistent){
            forces.remove(force);
        }else{
            throw new IllegalArgumentException("Asked to remove a persistent force, but the force given is not persistent");
        }
    }

    public Vec2f getMovement() {
        return movement;
    }

    public void setMovement(Vec2f movement){
        this.movement = movement;
    }

    public void move(Vec2f delta){
        position.add(delta);
    }

    public void move(float dx, float dy){
        move(new Vec2f(dx, dy));
    }

    public void setPosition(float x, float y){
        this.position.x = x;
        this.position.y = y;
    }

    public void setCurrentAnimation(int currentAnimation){
        this.currentAnimation = currentAnimation;
    }

    public void setRotation(float rotation){
        this.rotation = rotation;
    }

    public void setWidth(float width){
        this.width = width;
    }

    public void setHeight(float height){
        this.height = height;
    }

    public void setAnimations(Animation[] animations){
        this.animations = animations;
    }

    public void setPosition(Vec2f pos){
        this.position = pos;
    }

    public Vec2f getPosition(){
        return this.position;
    }

    /**
     * The rigid body is a square. It is used to handle collision between objects.
     * @return the rigid body of this game object, or null, if this object does not have any rigid body.
     */
    public RigidBody getRigidBody(){
        return rigidBody;
    }

    public float getWidth(){
        return this.width;
    }

    public float getHeight(){
        return this.height;
    }

    public float getRotation(){
        return this.rotation;
    }
}
