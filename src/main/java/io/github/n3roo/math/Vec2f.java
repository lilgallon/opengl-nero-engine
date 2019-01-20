package io.github.n3roo.math;

public class Vec2f {

    public float x;
    public float y;

    public Vec2f(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * It modifies the object, and return its value. So it can be used in two manners :
     * > Vec2f vec3 = vec1.add(vec2)
     * > vec1.add(vec2)
     *
     * Here, in both cases, vec1 was updated. So vec3 == vec2.
     *
     * It simply adds the two vectors this way :
     * > vec1.x += vec2.x
     * > vec1.y += vec2.y
     *
     * @param vec vector to add.
     * @return the current vector.
     */
    public Vec2f add(Vec2f vec){
        this.x += vec.x;
        this.y += vec.y;
        return this;
    }

    /**
     * It modifies the object, and return its value. So it can be used in two manners :
     * > Vec2f vec2 = vec1.multiply(1.5f)
     * > vec1.multiply(1.5f)
     *
     * Here, in both cases, vec1 was updated.
     *
     * It simply multiplies the vector with a float value this way :
     * > vec1.x *= float value
     * > vec1.y *= float value
     *
     * @param val to multiply the vector with.
     * @return the current vector.
     */
    public Vec2f multiply(float val){
        this.x *= val;
        this.y *= val;
        return this;
    }

    @Override
    public String toString(){
        return "[" + x + "; " + y + "]";
    }
}
