package io.github.n3roo.graphics;

import com.jogamp.opengl.GL2;

public class Graphics {

    // Color values
    private static float red = 1;
    private static float green = 1;
    private static float blue = 1;
    private static float alpha = 1;

    /**
     * It fills a rectangle (width ; height) at the defined position (x ; y).
     * The position is the top left corner.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public static void fillRect(float x, float y, float width, float height){
        GL2 gl = EventListener.gl;

        gl.glColor4f(red, green, blue, alpha);
        gl.glBegin(GL2.GL_QUADS);
            gl.glVertex2f(x, y);
            gl.glVertex2f(x + width, y);
            gl.glVertex2f(x + width, y + height);
            gl.glVertex2f(x, y + height);
        gl.glEnd();
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

}
