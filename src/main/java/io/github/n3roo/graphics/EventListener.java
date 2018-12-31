package io.github.n3roo.graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import io.github.n3roo.resources.ImageResource;
import io.github.n3roo.world.World;

public class EventListener implements GLEventListener {

    public static GL2 gl = null;
    public static ImageResource image = null;

    // When it starts
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        // r g b a -> color we want to use to clear the screen : glClear(..)
        gl.glClearColor(0, 0, 0, 1);

        gl.glEnable(GL2.GL_TEXTURE_2D);
    }

    // Every time
    public void display(GLAutoDrawable drawable) {
        gl = drawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        gl.glTranslatef(- Renderer.cameraX, - Renderer.cameraY, 0);
        World.render();
        gl.glTranslatef(Renderer.cameraX, Renderer.cameraY, 0);
    }

    // When the window size changes
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        float units_tall = height / (width / Renderer.UNITS_WIDE);

        gl.glOrtho(- Renderer.UNITS_WIDE / 2, Renderer.UNITS_WIDE / 2, - units_tall / 2f, units_tall / 2f, -1, 1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    // When it closes
    public void dispose(GLAutoDrawable drawable) {

    }
}
