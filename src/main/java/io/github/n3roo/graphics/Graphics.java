package io.github.n3roo.graphics;

import com.jogamp.opengl.GL2;

public class Graphics {

    // Color values
    private static float red = 1;
    private static float green = 1;
    private static float blue = 1;
    private static float alpha = 1;

    // Rotation in degrees (clockwise)
    private static float rotation = 0;

    /**
     * It draws a rectangle (width ; height).
     * The position is the middle of the rectangle.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public static void fillRect(float x, float y, float width, float height){
        GL2 gl = EventListener.gl;

        // Rotate the openGL context
        gl.glTranslatef(x, y, 0);
        gl.glRotatef(- rotation, 0, 0, 1);

        // Draw the rectangle
        gl.glColor4f(red, green, blue, alpha);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(- width / 2, - height / 2);
        gl.glVertex2f(  width / 2, - height / 2);
        gl.glVertex2f(  width / 2,   height / 2);
        gl.glVertex2f(- width / 2,   height / 2);
        gl.glEnd();

        // Restore the openGL context
        gl.glRotatef(rotation, 0, 0, 1);
        gl.glTranslatef(- x, - y, 0);
    }

    /**
     * It changes the color.
     * Values need to be in [0; 1].
     * @param r red value,
     * @param g green value,
     * @param b blue value,
     * @param a alpha value.
     */
    public static void setColor(float r, float g, float b, float a){
        red = Math.max(0, Math.min(1, r));
        green = Math.max(0, Math.min(1, g));
        blue = Math.max(0, Math.min(1, b));
        alpha = Math.max(0, Math.min(1, a));
    }

    /**
     * Rotates the shapes (clockwise).
     * @param r rotation in degrees [0; 360[.
     */
    public static void setRotation(float r){
        rotation = r;
    }

}
