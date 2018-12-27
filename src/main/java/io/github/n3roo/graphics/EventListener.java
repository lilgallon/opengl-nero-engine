package io.github.n3roo.graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class EventListener implements GLEventListener {

    // When it starts
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        // r g b a -> color we want to use to clear the screen : glClear(..)
        gl.glClearColor(0, 0, 0, 1);
    }

    // When it closes
    public void dispose(GLAutoDrawable drawable) {


    }

    // Every time
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        gl.glColor3f(0, 0, 1);
        gl.glBegin(GL2.GL_QUADS);
            gl.glVertex2f(-1, -1);
            gl.glVertex2f(1, -1);
            gl.glVertex2f(1, 1);
            gl.glVertex2f(-1, 1);
        gl.glEnd();
    }

    // When the window size changes
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        float units_tall = Renderer.getWindowHeight() / (Renderer.getWindowWidth() / Renderer.UNITS_WIDE);

        gl.glOrtho(- Renderer.UNITS_WIDE / 2, Renderer.UNITS_WIDE / 2, - units_tall / 2f, units_tall / 2f, -1, 1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }
}
