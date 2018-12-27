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
            gl.glVertex2f(-0.5f, -0.5f);
            gl.glVertex2f(0.5f, -0.5f);
            gl.glVertex2f(0.5f, 0.5f);
            gl.glVertex2f(-0.5f, 0.5f);
        gl.glEnd();
    }

    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {
        // When the window size changes

    }
}
