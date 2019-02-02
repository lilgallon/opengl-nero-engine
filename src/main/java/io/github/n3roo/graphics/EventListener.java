package io.github.n3roo.graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import io.github.n3roo.engine.NeroEngine;
import io.github.n3roo.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EventListener implements GLEventListener {

    private static final Logger LOGGER = LogManager.getLogger(NeroEngine.class.getName());

    public static GL2 gl = null;

    // When it starts
    public void init(GLAutoDrawable drawable) {
        LOGGER.debug("Setting up OpenGL settings");
        GL2 gl = drawable.getGL().getGL2();

        // r g b a -> color we want to use to clear the screen : glClear(..)
        gl.glClearColor(0, 0, 0, 1);

        gl.glEnable(GL2.GL_TEXTURE_2D);

        // This is used to display transparent images :
        gl.glEnable(GL2.GL_BLEND);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
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
        LOGGER.debug("Setting up OpenGL settings with the new window size");
        GL2 gl = drawable.getGL().getGL2();

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        Renderer.unitsTall = height / (width / Renderer.unitsWide);

        gl.glOrtho(- Renderer.unitsWide / 2, Renderer.unitsWide / 2, - Renderer.unitsTall / 2f, Renderer.unitsTall / 2f, -1, 1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    // When it closes
    public void dispose(GLAutoDrawable drawable) {

    }
}
