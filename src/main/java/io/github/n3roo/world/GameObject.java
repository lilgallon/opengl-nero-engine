package io.github.n3roo.world;

import io.github.n3roo.graphics.Animation;
import io.github.n3roo.graphics.Graphics;
import io.github.n3roo.math.Force;
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
    private Stack<Force> forces = new Stack<>();
    private Vec2f movement = new Vec2f(0, 0);

    /**
     * Update values in this function.
     */
    public void update(){

    }

    /**
     * Render anything in this function.
     */
    public void render(){
        // We play the animation if there is any.
        if(animations != null) {
            animations[currentAnimation].play();

            Graphics.setRotation(rotation + graphicsRotation);
            Graphics.setColor(1, 1, 1, 1);
            Graphics.drawImage(animations[currentAnimation].getImage(), position.x, position.y, width, height);
            Graphics.setRotation(0);
        }

        // Draw the rigid body if we want to.
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

    /**
     * It changes the forces applied to this game object.
     * @param forces new forces.
     */
    public void setForces(Stack<Force> forces){
        this.forces = forces;
    }

    /**
     * @return the forces applied to this game object.
     */
    public Stack<Force> getForces(){
        return forces;
    }

    /**
     * It clears the forces applied to this game object.
     */
    public void clearForces(){
        forces = new Stack<>();
    }

    /**
     * It removes a persistent force.
     * @param force the persistent force to remove.
     */
    public void removePersistentForce(Force force){
        if(force.getMode() == Force.Mode.Persistent){
            forces.remove(force);
        }else{
            throw new IllegalArgumentException(
                    "Asked to remove a persistent force, but the force given is not persistent"
            );
        }
    }

    /**
     * @return movement vector.
     */
    public Vec2f getMovement() {
        return movement;
    }

    /**
     * It changes the game object movement vector.
     * @param movement new movement vector.
     */
    public void setMovement(Vec2f movement){
        this.movement = movement;
    }

    /**
     * It moves the game object (you have to be sure to what you are doing,it does not take in account collisions or
     * anything).
     * @param delta movement.
     */
    public void move(Vec2f delta){
        position.add(delta);
    }

    /**
     * It moves the game object (you have to be sure to what you are doing,it does not take in account collisions or
     * anything).
     * @param dx x movement,
     * @param dy y movement.
     */
    public void move(float dx, float dy){
        move(new Vec2f(dx, dy));
    }

    /**
     * It changes the position.
     * @param pos new position.
     */
    public void setPosition(Vec2f pos){
        this.position = pos;
    }

    /**
     * It changes the position.
     * @param x new x position,
     * @param y new y position.
     */
    public void setPosition(float x, float y){
        this.position.x = x;
        this.position.y = y;
    }

    /**
     * It changes the current animation (example: walking, and running).
     * @param currentAnimation new animation index.
     */
    public void setCurrentAnimation(int currentAnimation){
        this.currentAnimation = currentAnimation;
    }

    /**
     * It changes the rotation of this game object.
     * @param rotation new rotation in degrees.
     */
    public void setRotation(float rotation){
        this.rotation = rotation;
    }

    /**
     * It changes the width of this game object.
     * @param width new width.
     */
    public void setWidth(float width){
        this.width = width;
    }

    /**
     * It changes the height of this game object.
     * @param height new height.
     */
    public void setHeight(float height){
        this.height = height;
    }

    /**
     * It changes the animations of this object.
     * @param animations new animations.
     */
    public void setAnimations(Animation[] animations){
        this.animations = animations;
    }

    /**
     * @return the current position of this object.
     */
    public Vec2f getPosition(){
        return this.position;
    }

    /**
     * @return the current rotation of this object.
     */
    public float getRotation(){
        return this.rotation;
    }

    /**
     * The rigid body is a square. It is used to handle collision between objects.
     * @return the rigid body of this game object, or null, if this object does not have any rigid body.
     */
    public RigidBody getRigidBody(){
        return rigidBody;
    }

    /**
     * @return the current width of this object.
     */
    public float getWidth(){
        return this.width;
    }

    /**
     * @return the current height of this object.
     */
    public float getHeight(){
        return this.height;
    }
}
