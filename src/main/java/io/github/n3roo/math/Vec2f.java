package io.github.n3roo.math;

public class Vec2f {

    public float x;
    public float y;

    public Vec2f(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vec2f(Vec2f vec){
        this.x = vec.x;
        this.y = vec.y;
    }

    /**
     * It adds the two vectors this way :
     * > vec1.x += vec2.x
     * > vec1.y += vec2.y
     *
     * @param vec vector to add.
     */
    public void add(Vec2f vec){
        this.x += vec.x;
        this.y += vec.y;
    }

    /**
     * It subtracts the two vectors this way :
     * > vec1.x -= vec2.x
     * > vec1.y -= vec2.y
     *
     * @param vec vector to subtract with.
     */
    public void subtract(Vec2f vec){
        this.x -= vec.x;
        this.y -= vec.y;
    }

    /**
     * It multiplies the vector with a float value this way :
     * > vec1.x *= float value
     * > vec1.y *= float value
     *
     * @param val to multiply the vector with.
     */
    public void multiply(float val){
        this.x *= val;
        this.y *= val;
    }

    @Override
    public String toString(){
        return "[" + x + "; " + y + "]";
    }
}
