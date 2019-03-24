package io.github.n3roo.math;

public class Force {

    /**
     * The values of this force.
     */
    private Vec2f vector;

    /**
     * The force mode.
     */
    private Mode mode;

    /**
     * Force mode :
     * Persistent : the force is not affected by friction, and is applied all the time (samples: gravity),
     * Velocity : the force is not affected by friction, but needs to be applied all the time (samples: input movement),
     * Impulse : the force is affected by friction (samples: punch).
     *
     * To remove a persistent force, you need to use removePersistentForce(Force persistentForce).
     */
    public enum Mode{
        Persistent,
        Velocity,
        Impulse
    }

    /**
     * A force is represented by a 2D vector (x, y), and its mode (Velocity or Impulse).
     * @param vector the force vector,
     * @param mode the force mode.
     */
    public Force(Vec2f vector, Mode mode){
        this.vector = vector;
        this.mode = mode;
    }

    /**
     * @return the values of this force.
     */
    public Vec2f getVector() {
        return vector;
    }

    /**
     * @return the mode of this force.
     */
    public Mode getMode() {
        return mode;
    }
}
